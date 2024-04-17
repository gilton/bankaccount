package org.labs.bank.banksling.repositories;

import java.util.Optional;
import java.util.UUID;

import org.labs.bank.banksling.models.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BancoRepository extends JpaRepository<Banco, UUID> {

	@Query(value = "FROM Banco b WHERE b.codigoBanco = ?1")
	Optional<Banco> findByCodigo(Integer codigoBanco);
}
