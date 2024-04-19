package org.labs.bank.banksling.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.labs.bank.banksling.models.Cliente;

public interface ClienteService {

	List<Cliente> findAll();

	Optional<Cliente> findById(UUID id);

	Optional<Cliente> findByCPF(String cpf);

	Object save(Cliente cliente);

	void delete(Cliente cliente);

}
