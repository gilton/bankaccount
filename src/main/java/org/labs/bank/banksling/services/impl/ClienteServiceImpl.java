package org.labs.bank.banksling.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.labs.bank.banksling.models.Cliente;
import org.labs.bank.banksling.models.Endereco;
import org.labs.bank.banksling.repositories.ClienteRepository;
import org.labs.bank.banksling.repositories.EnderecoRepository;
import org.labs.bank.banksling.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	@Override
	public List<Cliente> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Cliente> findById(UUID id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Cliente> findByCPF(String cpf) {
		return repository.findByCPF(cpf);
	}

	@Override
	public Object save(Cliente cliente) {
		cliente = salvarOuAtualizarEndereco(cliente);
		return repository.save(cliente);
	}

	@Override
	public void delete(Cliente cliente) {
		repository.delete(cliente);
	}

//	AUXILIAR METHOD
	private Cliente salvarOuAtualizarEndereco(Cliente cliente) {

		Optional<Endereco> enderecoOpt = Optional.of(new Endereco());
		var enderecoId = cliente.getEndereco().getIdEndereco();
		
		if( Objects.isNull( enderecoId ) ) {
			enderecoOpt = enderecoRepository.findByRuaENumero(cliente.getEndereco().getRua(), cliente.getEndereco().getNumero());
			if (!enderecoOpt.isPresent()) {
				Endereco novoEndereco = enderecoRepository.saveAndFlush(cliente.getEndereco());
				cliente.setEndereco(novoEndereco);
				return cliente;
			}
			if( Objects.isNull( cliente.getEndereco().getIdEndereco() ) ) {
				cliente.getEndereco().setIdEndereco( enderecoOpt.get().getIdEndereco() );
			}
			return cliente;
		}
		return cliente;
	}
	
}
