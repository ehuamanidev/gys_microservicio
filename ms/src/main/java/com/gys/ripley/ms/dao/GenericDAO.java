package com.gys.ripley.ms.dao;

import java.util.List;
import java.util.Map;

import com.gys.ripley.ms.commons.ProcedureUtil;
import com.gys.ripley.ms.exception.DataBaseException;

public interface GenericDAO {
	
	public List<Map<String, Object>> ejecutarNativeQueryMapList(String sql, Map<String, Object> params) throws DataBaseException;
	
	public void ejecutarNativeQueryVoid(String sql, Map<String, Object> params) throws DataBaseException;
	
	public void ejecutarProcedimiento( ProcedureUtil procedureUtil ) throws DataBaseException;

}
