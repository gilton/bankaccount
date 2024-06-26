package org.labs.bank.banksling.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.labs.bank.banksling.dtos.ContaRecordDto;
import org.labs.bank.banksling.models.Banco;
import org.labs.bank.banksling.models.Cliente;
import org.labs.bank.banksling.models.Conta;
import org.labs.bank.banksling.repositories.BancoRepository;
import org.labs.bank.banksling.repositories.ClienteRepository;
import org.labs.bank.banksling.repositories.ContaRepository;
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

@Tag(name = "contas", description = "Endpoint do domínio de Contas do Banksling")
@RestController
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	private ContaRepository repository;
	
	@Autowired
	private BancoRepository bankRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public ResponseEntity<List<Conta>> getAllContas(){
		List<Conta> contaList = repository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(contaList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneConta(@PathVariable(value="id") UUID id){
		Optional<Conta> contaOpt = repository.findById(id);
		if( contaOpt.isEmpty() ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrado.\nTente novamente.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(contaOpt.get());
	}
	
	@PostMapping
	public ResponseEntity<Object> saveConta(@RequestBody @Valid ContaRecordDto contaRecordDto) {
		var conta = new Conta();
		BeanUtils.copyProperties(contaRecordDto, conta);
		
		Optional<Banco> bancoOpt = bankRepository.findByCodigo(conta.getBanco().getCodigoBanco());
		if(bancoOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Banco não encontrado para editar.");
		}
		conta.setBanco( bancoOpt.get() );
		
		Optional<Cliente> clienteOpt = clienteRepository.findByCPF(conta.getCliente().getCpf());
		if(clienteOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
		}
		conta.setCliente( clienteOpt.get() );
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(conta));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateConta(@PathVariable(value="id") UUID id,
												@RequestBody @Valid ContaRecordDto contaRecordDto) {
		
		Optional<Conta> bankOpt = repository.findById(id);
		if(bankOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrado para editar.");
		}
		BeanUtils.copyProperties(contaRecordDto, bankOpt.get());
		return ResponseEntity.status(HttpStatus.OK).body(repository.save( bankOpt.get() ));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteConta(@PathVariable(value="id") UUID id) {
		Optional<Conta> bankOpt = repository.findById(id);
		if(bankOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrado para excluir.");
		}
		repository.delete(bankOpt.get());
		return ResponseEntity.status(HttpStatus.OK).body("Conta deleted com successo.");
	}
}
