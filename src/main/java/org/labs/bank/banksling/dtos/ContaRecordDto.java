package org.labs.bank.banksling.dtos;

import org.labs.bank.banksling.models.Banco;
import org.labs.bank.banksling.models.Cliente;
import org.labs.bank.banksling.models.enums.TipoConta;

public record ContaRecordDto(
		Long numeroConta,
		Integer agencia,
		TipoConta tipoConta,
		Double saldo,
		Banco banco,
		Cliente cliente
		) { }
