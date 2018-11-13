package com.gys.ripley.ms.services.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gys.ripley.ms.dao.ManifiestoDAO;
import com.gys.ripley.ms.dto.ManifiestoDTO;
import com.gys.ripley.ms.dto.ManifiestoInRO;
import com.gys.ripley.ms.dto.ManifiestoListOutRO;
import com.gys.ripley.ms.exception.DataBaseException;
import com.gys.ripley.ms.services.ManifiestoService;

@Service
@Transactional
public class ManifiestoServiceImpl implements ManifiestoService{
	
	@Autowired
	private ManifiestoDAO manifiestoDao;
	
	public ManifiestoDTO crearManifiesto(ManifiestoDTO dto) throws DataBaseException{
		return manifiestoDao.crearManifiesto(dto);
	} 
	
	public ManifiestoListOutRO selManifiesto(ManifiestoInRO dto) throws DataBaseException{
		return manifiestoDao.manifiestoSel(dto);
	}
}
