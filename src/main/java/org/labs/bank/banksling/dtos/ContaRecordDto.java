package org.labs.bank.banksling.dtos;

import org.labs.bank.banksling.models.Banco;
import org.labs.bank.banksling.models.Cliente;

public record ContaRecordDto(
		Long numeroConta,
		Integer agencia,
		Double saldo,
		Banco banco,
		Cliente cliente
		) { }
