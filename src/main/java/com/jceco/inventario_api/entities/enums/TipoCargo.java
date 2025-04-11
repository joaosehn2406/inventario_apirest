package com.jceco.inventario_api.entities.enums;

public enum TipoCargo {
	
	OPERADOR(1),
	CAIXA(2),
	PPCP(3),
	ADDM(4);
	
	private int code;
	
	
	
	public int getCode() {
		return code;
	}



	private TipoCargo (int code) {
		this.code = code;
	}



	public static TipoCargo valueOf(int code) {
		for(TipoCargo value : TipoCargo.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid TipoCargo code");
	}
}
