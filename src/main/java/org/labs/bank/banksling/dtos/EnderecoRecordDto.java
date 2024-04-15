package org.labs.bank.banksling.dtos;

public record EnderecoRecordDto(
		String rua,
		int numero,
		String complemento,
		String cep,
		String cidade		
) {}
