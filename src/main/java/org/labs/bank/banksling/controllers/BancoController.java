package org.labs.bank.banksling.controllers;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.labs.bank.banksling.dtos.BancoRecordDto;
import org.labs.bank.banksling.models.Banco;
import org.labs.bank.banksling.repositories.BancoRepository;
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

@Tag(name = "bancos", description = "Endpoint do domínio dos Bancos do Banksling")
@RestController
@RequestMapping("/bancos")
public class BancoController {

	@Autowired
	private BancoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Banco>> getAllBancos(){
		List<Banco> bancoList = repository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(bancoList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneBanco(@PathVariable(value="id") UUID id){
		Optional<Banco> bancoOpt = repository.findById(id);
		if( bancoOpt.isEmpty() ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Banco não encontrado.\nTente novamente.");
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(bancoOpt.get());
	}
	
	@PostMapping
	public ResponseEntity<Object> saveBanco(@RequestBody @Valid BancoRecordDto bancoRecordDto) {
		var bank = new Banco();
		BeanUtils.copyProperties(bancoRecordDto, bank);

		Optional<Banco> bancoOpt = repository.findByCodigo(bank.getCodigoBanco());
		if( bancoOpt.isPresent() && Objects.nonNull(bancoOpt.get().getIdBanco()) ) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Banco já registrado.\nFavor informa um novo Banco.");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(bank));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateBanco(@PathVariable(value="id") UUID id,
												@RequestBody @Valid BancoRecordDto bancoRecordDto) {
		
		Optional<Banco> bankOpt = repository.findById(id);
		if(bankOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Banco não encontrado para editar.");
		}
		BeanUtils.copyProperties(bancoRecordDto, bankOpt.get());
		return ResponseEntity.status(HttpStatus.OK).body(repository.save( bankOpt.get() ));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteBanco(@PathVariable(value="id") UUID id) {
		Optional<Banco> bankOpt = repository.findById(id);
		if(bankOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Banco não encontrado para excluir.");
		}
		repository.delete(bankOpt.get());
		return ResponseEntity.status(HttpStatus.OK).body("Banco deleted com successo.");
	}
}
