package com.gys.ripley.ms.dto;

public class ManifiestoDTO extends ResponseBaseDTO{
	private Double pSucOrigId;
	private Double pSucDestId;
	private Double pTranspId;
	private String pUserDespacho;
	private String pTipoDestino;
	private String pRutDestino;
	private Double pManifiestoId;
	
	public ManifiestoDTO() {
		super();
	}
	
	public Double getpSucOrigId() {
		return pSucOrigId;
	}
	public void setpSucOrigId(Double pSucOrigId) {
		this.pSucOrigId = pSucOrigId;
	}
	public Double getpSucDestId() {
		return pSucDestId;
	}
	public void setpSucDestId(Double pSucDestId) {
		this.pSucDestId = pSucDestId;
	}
	public Double getpTranspId() {
		return pTranspId;
	}
	public void setpTranspId(Double pTranspId) {
		this.pTranspId = pTranspId;
	}
	public String getpUserDespacho() {
		return pUserDespacho;
	}
	public void setpUserDespacho(String pUserDespacho) {
		this.pUserDespacho = pUserDespacho;
	}
	public String getpTipoDestino() {
		return pTipoDestino;
	}
	public void setpTipoDestino(String pTipoDestino) {
		this.pTipoDestino = pTipoDestino;
	}
	public String getpRutDestino() {
		return pRutDestino;
	}
	public void setpRutDestino(String pRutDestino) {
		this.pRutDestino = pRutDestino;
	}
	public Double getpManifiestoId() {
		return pManifiestoId;
	}
	public void setpManifiestoId(Double pManifiestoId) {
		this.pManifiestoId = pManifiestoId;
	}
}
