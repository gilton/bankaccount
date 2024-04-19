package org.labs.bank.banksling.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.labs.bank.banksling.models.Banco;
import org.labs.bank.banksling.repositories.BancoRepository;
import org.labs.bank.banksling.services.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BancoServiceImpl implements BancoService {

	@Autowired
	private BancoRepository repository;
	
	@Override
	public List<Banco> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Banco> findById(UUID id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Banco> findByCodigo(Integer codigoBanco) {
		return repository.findByCodigo(codigoBanco);
	}

	@Override
	public Object save(Banco banco) { return repository.save(banco); }

	@Override
	public void delete(Banco banco) { repository.delete(banco); }

}
