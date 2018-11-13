package com.gys.ripley.ms.dao;

import com.gys.ripley.ms.dto.ManifiestoDTO;
import com.gys.ripley.ms.dto.ManifiestoInRO;
import com.gys.ripley.ms.dto.ManifiestoListOutRO;
import com.gys.ripley.ms.exception.DataBaseException;

public interface ManifiestoDAO {

	public ManifiestoDTO crearManifiesto(ManifiestoDTO dto) throws DataBaseException;
	
	public ManifiestoListOutRO manifiestoSel(ManifiestoInRO manifiestoIn) throws DataBaseException;
	
}
