package org.labs.bank.banksling.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.labs.bank.banksling.models.Banco;

public interface BancoService {

	List<Banco> findAll();

	Optional<Banco> findById(UUID id);

	Optional<Banco> findByCodigo(Integer codigoBanco);

	Object save(Banco banco);

	void delete(Banco banco);

}
