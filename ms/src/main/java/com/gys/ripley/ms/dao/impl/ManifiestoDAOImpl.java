package com.gys.ripley.ms.dao.impl;

import org.springframework.stereotype.Repository;

import com.gys.ripley.commons.exception.CommonsException;
import com.gys.ripley.ms.commons.MsConfig;
import com.gys.ripley.ms.commons.ProcedureUtil;
import com.gys.ripley.ms.dao.ManifiestoDAO;
import com.gys.ripley.ms.dto.ListaSesionOutRO;
import com.gys.ripley.ms.dto.ManifiestoDTO;
import com.gys.ripley.ms.dto.ManifiestoInRO;
import com.gys.ripley.ms.dto.ManifiestoListOutRO;
import com.gys.ripley.ms.dto.SesionInRO;
import com.gys.ripley.ms.dto.SolicitudSucInRO;
import com.gys.ripley.ms.exception.DataBaseException;
import com.gys.ripley.ms.facade.ManifiestoFacade;
import com.gys.ripley.ms.facade.SessionFacade;

import static com.gys.ripley.ms.commons.ProcedureParams.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Repository
public class ManifiestoDAOImpl extends GenericDAOImpl implements ManifiestoDAO {

	final Logger logger = LogManager.getLogger(ManifiestoDAOImpl.class);

	@Override
	public ManifiestoDTO crearManifiesto(ManifiestoDTO dto) throws DataBaseException {

		ManifiestoFacade manifiestoFacade = new ManifiestoFacade();

		try {

			ProcedureUtil pu = new ProcedureUtil(MsConfig.PRC_ES_MANIFIESTO.getValue());

			pu.addParamProcedureInt(dto.getpSucOrigId(), IN, Double.class, 1);
			pu.addParamProcedureInt(dto.getpSucDestId(), IN, Double.class, 2);
			pu.addParamProcedureInt(dto.getpTranspId(), IN, Double.class, 3);
			pu.addParamProcedureInt(dto.getpUserDespacho(), IN, String.class, 4);
			pu.addParamProcedureInt(dto.getpTipoDestino(), IN, String.class, 5);
			pu.addParamProcedureInt(dto.getpRutDestino(), IN, String.class, 6);
			pu.addParamProcedureOut(dto.getpManifiestoId(), OUT, Double.class, 7, "pManifiestoId");
			pu.addParamProcedureOut(dto.getpErrCode(), OUT, Integer.class, 8, "pErrCode");
			pu.addParamProcedureOut(dto.getpErrMsg(), OUT, String.class, 9, "pErrMsg");

			ejecutarProcedimiento(pu);

			manifiestoFacade.populateManifiestoDTO(dto, pu);

		} catch (CommonsException e) {
			throw new DataBaseException(e.getCod(), e.getMessage());
		}

		return dto;
	}

	@Override
	public ManifiestoListOutRO manifiestoSel(ManifiestoInRO manifiestoIn) throws DataBaseException {

		ManifiestoFacade manifiestoFacade = new ManifiestoFacade();
		ManifiestoListOutRO outRo = new ManifiestoListOutRO();

		try {

			ProcedureUtil pu = new ProcedureUtil(MsConfig.PRC_MANIFIESTO_SEL.getValue());
			pu.addParamProcedureInt(manifiestoIn.getPiTipEjecucion(), IN, String.class, "PI_TIP_EJECUCION");
			pu.addParamProcedureInt(manifiestoIn.getPiManifiesto(), IN, Long.class, "PI_MANIFIESTO");
			pu.addParamProcedureInt(manifiestoIn.getPiTransporte(), IN, Long.class, "PI_TRANSPORTE");
			pu.addParamProcedureCursor(outRo.getManifiestos(), CURSOR, Class.class, "PO_RETORNO");
			pu.addParamProcedureOut(outRo.getpErrCode(), OUT, Integer.class, "PO_COD_ERROR", "pErrCode");
			pu.addParamProcedureOut(outRo.getpErrMsg(), OUT, String.class, "PO_MSG_ERROR", "pErrMsg");

			ejecutarProcedimientoConCursor(pu);
			manifiestoFacade.populateManifiestoOutRO(outRo, pu);

		} catch (CommonsException e) {
			throw new DataBaseException(e.getCod(), e.getMessage());
		}

		return outRo;
	}

	@Override
	public ListaSesionOutRO terminarSesion(SesionInRO sessionInRO) throws DataBaseException {

		SessionFacade sessionfacade = new SessionFacade();
		ListaSesionOutRO outRo = new ListaSesionOutRO();

		try {
			ProcedureUtil pu = new ProcedureUtil(MsConfig.PRC_VALIDAR_TERMINAR_SESION.getValue());
			pu.addParamProcedureInt(sessionInRO.getPiSessionId(), IN, Integer.class, "PI_SESION_ID");
			pu.addParamProcedureOut(outRo.getPoCntError(), OUT, Integer.class, "PO_CNT_ERROR", "poCntError");
			pu.addParamProcedureCursor(outRo.getSesiones(), CURSOR, Class.class, "PO_RETORNO");
			pu.addParamProcedureOut(outRo.getpErrCode(), OUT, Integer.class, "PO_COD_ERROR", "pErrCode");
			pu.addParamProcedureOut(outRo.getpErrMsg(), OUT, String.class, "PO_MSG_ERROR", "pErrMsg");

			ejecutarProcedimientoConCursor(pu);
			sessionfacade.populateManifiestoOutRO(outRo, pu);

		} catch (CommonsException e) {
			throw new DataBaseException(e.getCod(), e.getMessage());
		}
		return outRo;
	}
	
	public ListaSesionOutRO solicitudSuc(SolicitudSucInRO manifiestoIn) throws DataBaseException{

		SessionFacade sessionfacade = new SessionFacade();
		ListaSesionOutRO outRo = new ListaSesionOutRO();

		try {
			ProcedureUtil pu = new ProcedureUtil(MsConfig.PRC_SOLICITUD_SUCDTN_SEL.getValue());
			pu.addParamProcedureInt(manifiestoIn.getPiCodSucOre(), IN, Long.class, "PI_COD_SUC_ORE");
			pu.addParamProcedureInt(manifiestoIn.getPiCodSucDtn(), IN, Long.class, "PI_COD_SUC_DTN");
			pu.addParamProcedureInt(manifiestoIn.getPiNomSucDtn(), IN, String.class, "PI_NOM_SUC_DTN");
			pu.addParamProcedureCursor(outRo.getSesiones(), CURSOR, Class.class, "PO_RETORNO");
			pu.addParamProcedureOut(outRo.getpErrCode(), OUT, Integer.class, "PO_COD_ERROR", "pErrCode");
			pu.addParamProcedureOut(outRo.getpErrMsg(), OUT, String.class, "PO_MSG_ERROR", "pErrMsg");

			ejecutarProcedimientoConCursor(pu);
			sessionfacade.populateManifiestoOutRO(outRo, pu);

		} catch (CommonsException e) {
			throw new DataBaseException(e.getCod(), e.getMessage());
		}
		return outRo;
	}

}
