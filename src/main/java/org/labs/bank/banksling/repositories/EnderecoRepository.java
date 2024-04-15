package org.labs.bank.banksling.repositories;

import java.util.Optional;
import java.util.UUID;

import org.labs.bank.banksling.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
	
	@Query(value = "FROM Endereco e WHERE e.rua ILIKE %?1% AND e.numero = ?2")
	Optional<Endereco> findByRuaENumero(String rua, int numero);
}
