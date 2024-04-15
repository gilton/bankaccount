package org.labs.bank.banksling.models.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Sexo {

	MASCULINO("M", "Masculino"),
	FEMININO("F", "Feminino");
	
	private String chave;
	@JsonValue
	private String valor;
	
	Sexo(String chave, String valor) { 
		this.chave = chave;
		this.valor = valor; 
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
	
}
