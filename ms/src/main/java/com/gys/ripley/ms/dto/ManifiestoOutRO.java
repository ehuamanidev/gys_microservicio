package com.gys.ripley.ms.dto;


public class ManifiestoOutRO extends ResponseBaseDTO{
	
	private Long manifiestoId;
	private Long transpId;
	private Long estado;
	
	public ManifiestoOutRO() {
		super();
	}

	public Long getManifiestoId() {
		return manifiestoId;
	}

	public void setManifiestoId(Long manifiestoId) {
		this.manifiestoId = manifiestoId;
	}

	public Long getTranspId() {
		return transpId;
	}

	public void setTranspId(Long transpId) {
		this.transpId = transpId;
	}

	public Long getEstado() {
		return estado;
	}

	public void setEstado(Long estado) {
		this.estado = estado;
	}
	
}
