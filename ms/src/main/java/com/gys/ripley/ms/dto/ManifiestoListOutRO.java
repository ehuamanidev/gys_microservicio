package com.gys.ripley.ms.dto;

import java.util.ArrayList;
import java.util.List;

public class ManifiestoListOutRO extends ResponseBaseDTO{
	
	private Integer outData;
	private List<ManifiestoOutRO> manifiestos;
	
	public ManifiestoListOutRO() {
		super();
		
		manifiestos = new ArrayList<>();
	}

	public List<ManifiestoOutRO> getManifiestos() {
		return manifiestos;
	}

	public void setManifiestos(List<ManifiestoOutRO> manifiestos) {
		this.manifiestos = manifiestos;
	}

	public Integer getOutData() {
		return outData;
	}

	public void setOutData(Integer outData) {
		this.outData = outData;
	}
	
}
