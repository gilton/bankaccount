package org.labs.bank.banksling.models.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Sexo {

	MASCULINO("M", "Masculino"),
	FEMININO("F", "Feminino");
	
	@JsonValue
	private String chave;
	@JsonValue
	private String valor;
	
	Sexo(String chave, String valor) { 
		this.chave = chave;
		this.valor = valor; 
	}
	
}
