package org.labs.bank.banksling.repositories;

import java.util.Optional;
import java.util.UUID;

import org.labs.bank.banksling.models.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, UUID> {

	@Query(value = """ 
			FROM Conta c
			INNER JOIN Cliente cl ON co.idCliente = cl.idCliente
			AND cl.cpf = ?1
			""", nativeQuery = true)
	Optional<Conta> findByCPF();
}
