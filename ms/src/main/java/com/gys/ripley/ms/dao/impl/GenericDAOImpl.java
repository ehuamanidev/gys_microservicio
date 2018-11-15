package com.gys.ripley.ms.dao.impl;

import static com.gys.ripley.commons.FunctionsUtil.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;

import com.gys.ripley.ms.commons.MsConfig;
import com.gys.ripley.ms.commons.ProcedureParams;
import com.gys.ripley.ms.commons.ProcedureUtil;
import com.gys.ripley.ms.dao.GenericDAO;
import com.gys.ripley.ms.exception.DataBaseException;

import oracle.jdbc.OracleTypes;

public abstract class GenericDAOImpl implements GenericDAO {

	@Autowired
	public SessionFactory sessionFactory;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void ejecutarProcedimiento(ProcedureUtil procedureUtil) throws DataBaseException {

		try {

			StoredProcedureQuery query = entityManager
					.createStoredProcedureQuery(join(MsConfig.SCHEMA_BD.getValue(), procedureUtil.getProcedureName()));
			processParams(query, procedureUtil);
			query.execute();
			processOutParams(query, procedureUtil);

		} catch (Exception e) {
			throw new DataBaseException(1, e.getMessage());
		}
	}

	public void ejecutarProcedimientoConCursor(ProcedureUtil procedureUtil) throws DataBaseException {
		
		Connection cnx = null;
		
		try {

			String procedure = "CALL " + join(MsConfig.SCHEMA_BD.getValue(), procedureUtil.getProcedureName());

			StringBuffer params = new StringBuffer("?");
			for (int i = 1; i < procedureUtil.paramsCount(); i++) {
				params.append(",?");
			}
			procedure = join(procedure, "(", params, ")");
			
			cnx = sessionFactory.getSessionFactoryOptions().getServiceRegistry()
					.getService(ConnectionProvider.class).getConnection();
	        
			procedureUtil.setCnx(cnx);
			
			CallableStatement cst = cnx.prepareCall(procedure);
			setParametersToSP( cst, procedureUtil);
			cst.execute();
			processOutParams( cst, procedureUtil );
			
		} catch (Exception e) {
			throw new DataBaseException(1, e.getMessage());
		} 
	}

	@Override
	public List<Map<String, Object>> ejecutarNativeQueryMapList(String sql, Map<String, Object> params)
			throws DataBaseException {

		try {
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			if (params != null) {
				for (String key : params.keySet()) {
					query.setParameter(key, params.get(key));
				}
			}
			return query.list();

		} catch (Exception e) {
			throw new DataBaseException(1, e.getMessage());
		}
	}

	@Override
	public void ejecutarNativeQueryVoid(String sql, Map<String, Object> params) throws DataBaseException {
		try {
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
			if (params != null) {
				for (String key : params.keySet()) {
					query.setParameter(key, params.get(key));
				}
			}
			query.executeUpdate();
		} catch (Exception e) {
			throw new DataBaseException(1, e.getMessage());
		}
	}

	private void processParams(StoredProcedureQuery query, ProcedureUtil procedureUtil) {
		if (!procedureUtil.hasParams()) {
			return;
		}

		setParametersToSP(query, procedureUtil.getProcedureParamsIn(), procedureUtil.getProcedureParamsOut());

		if (procedureUtil.hasCursor()) {
			ProcedureParams pp = procedureUtil.getProcedureParamCursor();

			if (isEmpty(pp.getParamName())) {
				query.registerStoredProcedureParameter(pp.getParameterOrder(), pp.getClazz(), ParameterMode.REF_CURSOR);
			} else {
				query.registerStoredProcedureParameter(pp.getParamName(), pp.getClazz(), ParameterMode.REF_CURSOR);
			}

		}
	}

	private void processOutParams(StoredProcedureQuery query, ProcedureUtil procedureUtil) {
		if (!procedureUtil.hasParamsOut()) {
			return;
		}

		for (ProcedureParams pp : procedureUtil.getProcedureParamsOut()) {

			if (isEmpty(pp.getParamName())) {

				pp.setValue(query.getOutputParameterValue(pp.getParameterOrder()));
				continue;
			}

			pp.setValue(query.getOutputParameterValue(pp.getParamName()));

		}
	}
	
	private void processOutParams( CallableStatement cst, ProcedureUtil procedureUtil ) throws SQLException {
		if (!procedureUtil.hasParamsOut()) {
			return;
		}

		for (ProcedureParams pp : procedureUtil.getProcedureParamsOut()) {
			processOutParams(cst, pp);
		}
		
		
		if( procedureUtil.hasCursor() ) {
			processOutParams(cst, procedureUtil.getProcedureParamCursor());
		}
		
	}
	
	
	private void processOutParams( CallableStatement cst, ProcedureParams pp ) throws SQLException {
		
		if( isEmpty(pp.getParamName()) ) {
			pp.setValue( cst.getObject(pp.getParameterOrder()) );
			return;
		}
		
		pp.setValue( cst.getObject( pp.getParamName() ) );
		
	}
	

	private void setParametersToSP(StoredProcedureQuery q, List<ProcedureParams> paramsIn,
			List<ProcedureParams> paramsOut) {

		for (ProcedureParams pp : paramsIn) {

			if (isEmpty(pp.getParamName())) {
				q.registerStoredProcedureParameter(pp.getParameterOrder(), pp.getClazz(), ParameterMode.IN);
				q.setParameter(pp.getParameterOrder(), pp.getValue());
				continue;
			}

			q.registerStoredProcedureParameter(pp.getParamName(), pp.getClazz(), ParameterMode.IN);
			q.setParameter(pp.getParamName(), pp.getValue());

		}

		for (ProcedureParams pp : paramsOut) {

			if (isEmpty(pp.getParamName())) {
				q.registerStoredProcedureParameter(pp.getParameterOrder(), pp.getClazz(), ParameterMode.OUT);
				continue;
			}

			q.registerStoredProcedureParameter(pp.getParamName(), pp.getClazz(), ParameterMode.OUT);
		}

	}

	private void setParametersToSP(CallableStatement cst, ProcedureUtil pp) throws SQLException {

		if (isEmpty(pp.getProcedureParamCursor().getParamName())) {
			setParametersOrderToSP(cst, pp.getProcedureParamsIn(), pp.getProcedureParamsOut(),
					pp.getProcedureParamCursor());
			return;
		}

		setParametersNameToSP(cst, pp.getProcedureParamsIn(), pp.getProcedureParamsOut(), pp.getProcedureParamCursor());

	}

	private void setParametersNameToSP(CallableStatement cst, List<ProcedureParams> paramsIn,
			List<ProcedureParams> paramsOut, ProcedureParams cursor) throws SQLException {

		for (ProcedureParams pp : paramsIn) {

			if (pp.getValue() instanceof String) {
				cst.setString(pp.getParamName(), toStr(pp.getValue()));
			}

			if (pp.getValue() instanceof Integer) {
				cst.setInt(pp.getParamName(), toInteger(pp.getValue()));
			}

			if (pp.getValue() instanceof Long) {
				cst.setLong(pp.getParamName(), toLong(pp.getValue()));
			}

		}

		for (ProcedureParams pp : paramsOut) {

			if (pp.getValue() instanceof String) {
				cst.registerOutParameter(pp.getParamName(), OracleTypes.VARCHAR);
			}

			if (pp.getValue() instanceof Integer) {
				cst.registerOutParameter(pp.getParamName(), OracleTypes.INTEGER);
			}

			if (pp.getValue() instanceof Long) {
				cst.registerOutParameter(pp.getParamName(), OracleTypes.INTEGER);
			}
		}

		if (!isEmpty(cursor)) {
			cst.registerOutParameter(cursor.getParamName(), OracleTypes.CURSOR);
		}

	}

	private void setParametersOrderToSP(CallableStatement cst, List<ProcedureParams> paramsIn,
			List<ProcedureParams> paramsOut, ProcedureParams cursor) throws SQLException {

		for (ProcedureParams pp : paramsIn) {

			if (pp.getValue() instanceof String) {
				cst.setString(pp.getParameterOrder(), toStr(pp.getValue()));
			}

			if (pp.getValue() instanceof Integer) {
				cst.setInt(pp.getParameterOrder(), toInteger(pp.getValue()));
			}

			if (pp.getValue() instanceof Long) {
				cst.setLong(pp.getParameterOrder(), toLong(pp.getValue()));
			}

		}

		for (ProcedureParams pp : paramsOut) {

			if (pp.getValue() instanceof String) {
				cst.registerOutParameter(pp.getParameterOrder(), OracleTypes.VARCHAR);
			}

			if (pp.getValue() instanceof Integer) {
				cst.registerOutParameter(pp.getParameterOrder(), OracleTypes.INTEGER);
			}

			if (pp.getValue() instanceof Long) {
				cst.registerOutParameter(pp.getParameterOrder(), OracleTypes.INTEGER);
			}
		}

		if (!isEmpty(cursor)) {
			cst.registerOutParameter(cursor.getParameterOrder(), OracleTypes.CURSOR);
		}

	}
}
