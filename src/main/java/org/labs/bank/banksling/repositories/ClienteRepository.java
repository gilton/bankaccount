package org.labs.bank.banksling.repositories;

import java.util.Optional;
import java.util.UUID;

import org.labs.bank.banksling.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID>{

	@Query(value = "FROM Cliente c WHERE c.cpf = ?1")
	Optional<Cliente> findByCPF(String cpf);
}
