package org.labs.bank.banksling.controllers;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.labs.bank.banksling.dtos.ClienteRecordDto;
import org.labs.bank.banksling.models.Cliente;
import org.labs.bank.banksling.models.Endereco;
import org.labs.bank.banksling.repositories.ClienteRepository;
import org.labs.bank.banksling.repositories.EnderecoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "clientes", description = "Endpoint do domínio de Clientes do Banksling")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteRepository repository;

	@Autowired
	EnderecoRepository enderecoRepository;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> getAllClientes(){
		List<Cliente> clientesList = repository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(clientesList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneCliente(@PathVariable(value="id") UUID id){
		Optional<Cliente> clienteOpt = repository.findById(id);
		if(clienteOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(clienteOpt.get());
	}
	
	@PostMapping
	public ResponseEntity<Object> saveCliente(@RequestBody @Valid ClienteRecordDto clienteRecordDto) {
		
		var cliente = new Cliente();
		BeanUtils.copyProperties(clienteRecordDto, cliente);
		
		Optional<Cliente> clienteOpt = repository.findByCPF( cliente.getCpf() );
		if(clienteOpt.isPresent() && Objects.nonNull(clienteOpt.get().getIdCliente()) ) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Cliente já registrado.\nFavor informar um novo Cliente.");
		}
		
		salvarOuAtualizarEndereco(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(cliente));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCliente(@PathVariable(value="id") UUID id,
												@RequestBody @Valid ClienteRecordDto clienteRecordDto) {
		Optional<Cliente> clienteOpt = repository.findById(id);
		if(clienteOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
		}
		
		var enderecoOpt = encontrarEnderecoPorIDSenaoSalve(clienteOpt);
		var cliente = clienteOpt.get();
		BeanUtils.copyProperties(clienteRecordDto, cliente);
		
		cliente.getEndereco().setIdEndereco(enderecoOpt.get().getIdEndereco());
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(cliente));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCliente(@PathVariable(value="id") UUID id) {
		Optional<Cliente> clienteOptO = repository.findById(id);
		if(clienteOptO.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
		}
		repository.delete(clienteOptO.get());
		return ResponseEntity.status(HttpStatus.OK).body("Cliente deleted com successo.");
	}
	
	
//	Auxiliar Methods
	private void salvarOuAtualizarEndereco(Cliente cliente) {
		var enderecoOpt = enderecoRepository.findByRuaENumero(cliente.getEndereco().getRua(), cliente.getEndereco().getNumero());
		if (!enderecoOpt.isPresent()) {
			enderecoRepository.saveAndFlush(cliente.getEndereco());
			return;
		}
		cliente.getEndereco().setIdEndereco(enderecoOpt.get().getIdEndereco());
	}
	
	private Optional<Endereco> encontrarEnderecoPorIDSenaoSalve(Optional<Cliente> clienteOpt) {
		var enderecoId = clienteOpt.get().getEndereco().getIdEndereco();
		Optional<Endereco> enderecoOpt = enderecoRepository.findById(enderecoId);
		
		if( enderecoOpt.isEmpty() ) {
			enderecoRepository.saveAndFlush(clienteOpt.get().getEndereco());
			enderecoOpt = Optional.of(clienteOpt.get().getEndereco());
		}
		return enderecoOpt;
	}
}
