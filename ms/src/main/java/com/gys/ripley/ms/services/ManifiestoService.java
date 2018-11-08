package com.gys.ripley.ms.services;

import com.gys.ripley.ms.dto.ManifiestoDTO;
import com.gys.ripley.ms.exception.DataBaseException;

public interface ManifiestoService {

	public ManifiestoDTO crearManifiesto(ManifiestoDTO dto) throws DataBaseException;
}
