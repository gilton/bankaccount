package org.labs.bank.banksling.models;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "TB_BANCO")
public class Banco implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idBanco;
	
	@Column(name = "nom_banco")
	private String nomBanco;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Conta> contas;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Cliente> clientes;

	
//  GETTs and SETTs
	public UUID getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(UUID idBanco) {
		this.idBanco = idBanco;
	}

	public String getNomBanco() {
		return nomBanco;
	}

	public void setNomBanco(String nomBanco) {
		this.nomBanco = nomBanco;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
	
}
