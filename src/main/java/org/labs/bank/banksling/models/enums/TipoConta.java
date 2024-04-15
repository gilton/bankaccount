package org.labs.bank.banksling.models.enums;

public enum TipoConta {
	
	CORRENTE("C", "Conta Corrente"), 
	POUPANCA("P", "Conta Poupança");
	
	private final String chave;
	private final String valor;
	
	TipoConta(String chave, String valor){
		this.chave = chave;
		this.valor = valor;
	}

	public String getChave() {
		return chave;
	}

	public String getValor() {
		return valor;
	}

	
	
}
