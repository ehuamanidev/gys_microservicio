package com.gys.ripley.ms.dto;

import static com.gys.ripley.commons.Constant.*;

public class ResponseBaseDTO {
	private Integer pErrCode;
	private String pErrMsg;
	
	public ResponseBaseDTO() {
		errorException( NO_ERROR , "" );
	}
	
	public ResponseBaseDTO(Integer pErrCode,  String pErrMsg) {
		errorException( pErrCode,  pErrMsg );
	}
	
	public void errorException( Integer pErrCode,  String pErrMsg ) {
		this.pErrCode = pErrCode;
		this.pErrMsg = pErrMsg;
	}
	
	public String getpErrMsg() {
		return pErrMsg;
	}
	public void setpErrMsg(String pErrMsg) {
		this.pErrMsg = pErrMsg;
	}
	public Integer getpErrCode() {
		return pErrCode;
	}
	public void setpErrCode(Integer pErrCode) {
		this.pErrCode = pErrCode;
	}
}
