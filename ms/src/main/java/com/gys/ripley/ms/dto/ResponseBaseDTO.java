package com.gys.ripley.ms.dto;

public class ResponseBaseDTO {
	private Integer pErrCode;
	private String pErrMsg;
	
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
