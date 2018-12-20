package com.gys.ripley.ms.services.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gys.ripley.ms.dao.ManifiestoDAO;
import com.gys.ripley.ms.dto.ListaSesionOutRO;
import com.gys.ripley.ms.dto.ManifiestoDTO;
import com.gys.ripley.ms.dto.ManifiestoInRO;
import com.gys.ripley.ms.dto.ManifiestoListOutRO;
import com.gys.ripley.ms.dto.SesionInRO;
import com.gys.ripley.ms.dto.SolicitudSucInRO;
import com.gys.ripley.ms.exception.DataBaseException;
import com.gys.ripley.ms.services.ManifiestoService;

@Service
public class ManifiestoServiceImpl implements ManifiestoService{
	
	@Autowired
	private ManifiestoDAO manifiestoDao;
	
	@Transactional
	public ManifiestoDTO crearManifiesto(ManifiestoDTO dto) throws DataBaseException{
		return manifiestoDao.crearManifiesto(dto);
	} 
	
	public ManifiestoListOutRO selManifiesto(ManifiestoInRO dto) throws DataBaseException{
		return manifiestoDao.manifiestoSel(dto);
	}
	
	public ListaSesionOutRO terminarSesion(SesionInRO sessionInRO) throws DataBaseException{
		return manifiestoDao.terminarSesion( sessionInRO );
	}
	
	public ListaSesionOutRO solicitudSuc(SolicitudSucInRO manifiestoIn) throws DataBaseException{
		return manifiestoDao.solicitudSuc( manifiestoIn );
	}
}
