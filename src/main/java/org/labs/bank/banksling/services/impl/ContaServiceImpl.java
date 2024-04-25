package org.labs.bank.banksling.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.labs.bank.banksling.models.Conta;
import org.labs.bank.banksling.repositories.ContaRepository;
import org.labs.bank.banksling.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;

public class ContaServiceImpl implements ContaService {

	@Autowired
	private ContaRepository repository;
	
	@Override
	public List<Conta> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Conta> findById(UUID id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Conta> findByCPF(String cpf) {
		return repository.findByCPF();
	}

	@Override
	public Object save(Conta conta) {
		return repository.save(conta);
	}

	@Override
	public void delete(Conta conta) {
		repository.delete(conta);
	}
	
}
