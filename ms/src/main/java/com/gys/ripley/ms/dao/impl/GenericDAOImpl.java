package com.gys.ripley.ms.dao.impl;

import static com.gys.ripley.commons.FunctionsUtil.*;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gys.ripley.ms.commons.MsConfig;
import com.gys.ripley.ms.commons.ProcedureParams;
import com.gys.ripley.ms.commons.ProcedureUtil;
import com.gys.ripley.ms.dao.GenericDAO;
import com.gys.ripley.ms.exception.DataBaseException;

public abstract class GenericDAOImpl implements GenericDAO{
	
	@Autowired
	public SessionFactory sessionFactory;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void ejecutarProcedimiento( ProcedureUtil procedureUtil ) throws DataBaseException {
		
		try {

			StoredProcedureQuery query = entityManager.createStoredProcedureQuery( join(MsConfig.SCHEMA_BD.getValue(), procedureUtil.getProcedureName()) );
			
			processParams(query, procedureUtil);
			query.execute();
			processOutParams( query, procedureUtil );
			
		} catch (Exception e) {
			throw new DataBaseException(1, e.getMessage());
		}
	}
	
	@Override
	public List<Map<String, Object>> ejecutarNativeQueryMapList(String sql, Map<String, Object> params) throws DataBaseException {
		
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
	
	private void processParams( StoredProcedureQuery query, ProcedureUtil procedureUtil ) {
		if( !procedureUtil.hasParams() ) {
			return;
		}
		
		setParametersToSP(query, procedureUtil.getProcedureParamsIn(), procedureUtil.getProcedureParamsOut());
	}
	
	private void processOutParams( StoredProcedureQuery query, ProcedureUtil procedureUtil ) {
		if( !procedureUtil.hasParamsOut() ) {
			return;
		}
		
		for( ProcedureParams pp : procedureUtil.getProcedureParamsOut() ) {
			pp.setValue( query.getOutputParameterValue(pp.getParameterOrder()) );
		}
	}
	
	
	private void setParametersToSP(StoredProcedureQuery q, List<ProcedureParams> paramsIn, List<ProcedureParams> paramsOut) {
		
		for( ProcedureParams pp : paramsIn ) {
			q.registerStoredProcedureParameter(pp.getParameterOrder(), pp.getClazz(), ParameterMode.IN);
			q.setParameter( pp.getParameterOrder(), pp.getValue() );
		}
		
		for( ProcedureParams pp : paramsOut ) {
			q.registerStoredProcedureParameter(pp.getParameterOrder(), pp.getClazz(), ParameterMode.OUT);
		}
		
	}
}
