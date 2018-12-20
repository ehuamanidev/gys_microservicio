package com.gys.ripley.ms.commons;

public enum MsConfig {

	SCHEMA_BD("RFTDA_ADM."),
	PRC_ES_MANIFIESTO("ES_MANIFIESTO.Crear_manifiesto"),
	PRC_MANIFIESTO_SEL("NRFTDA_PK_MANIFIESTO.PRC_MANIFIESTO_SEL"),
	PRC_VALIDAR_TERMINAR_SESION("NRFTDA_PK_DESPACHO.PRC_VALIDAR_TERMINAR_SESION"),
	PRC_SOLICITUD_SUCDTN_SEL("NRFTDA_PK_DESPACHO.PRC_SOLICITUD_SUC_DTN_SEL");
	private String value;
	
	private MsConfig( String value ) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
