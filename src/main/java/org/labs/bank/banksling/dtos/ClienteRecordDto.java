package org.labs.bank.banksling.dtos;

import java.time.LocalDate;

import org.labs.bank.banksling.models.Endereco;
import org.labs.bank.banksling.models.enums.Sexo;

import jakarta.validation.constraints.NotBlank;

public record ClienteRecordDto(
		@NotBlank String nomCliente, 
		@NotBlank String cpf, 
		Sexo sexo, 
		LocalDate datNascido, 
		String celular, 
		@NotBlank String email,
		Endereco endereco
) {}
