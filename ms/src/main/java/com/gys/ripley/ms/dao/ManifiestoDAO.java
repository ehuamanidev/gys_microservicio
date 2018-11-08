package com.gys.ripley.ms.dao;

import com.gys.ripley.ms.dto.ManifiestoDTO;
import com.gys.ripley.ms.exception.DataBaseException;

public interface ManifiestoDAO {

	public ManifiestoDTO crearManifiesto(ManifiestoDTO dto) throws DataBaseException;
	
}
