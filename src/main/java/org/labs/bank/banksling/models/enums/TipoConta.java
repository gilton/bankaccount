package org.labs.bank.banksling.models.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoConta {
	
	CORRENTE("C", "Conta Corrente"), 
	POUPANCA("P", "Conta Poupança");
	
	private String chave;
	
	@JsonValue
	private String valor;
	
	TipoConta(String chave, String valor){
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
