package com.gys.ripley.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gys.ripley.ms.dto.ResponseBaseDTO;

@RestController
@RequestMapping("rest")
public abstract class BaseController {

	static final String ERROR = "1";

	@Autowired
	Environment env;

	public ResponseBaseDTO successResponse() {
		return new ResponseBaseDTO();
	}

}