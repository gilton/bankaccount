package org.labs.bank.banksling.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.labs.bank.banksling.models.Conta;

public interface ContaService {

	List<Conta> findAll();

	Optional<Conta> findById(UUID id);

	Optional<Conta> findByCPF(String cpf);

	Object save(Conta conta);

	void delete(Conta conta);
}
