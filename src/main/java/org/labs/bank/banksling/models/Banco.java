package org.labs.bank.banksling.models;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "TB_BANCO")
@DynamicUpdate
public class Banco implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idBanco;
	
	@Column(name = "codigo_banco")
	private Integer codigoBanco;
	
	@Column(name = "nom_banco")
	private String nomBanco;
	
	@OneToMany(mappedBy = "idConta", orphanRemoval = true)
	private List<Conta> contas;
	
	@OneToMany(mappedBy = "idCliente", orphanRemoval = true)
	private List<Cliente> clientes;

	
	public void addConta(Conta conta) {
		contas.add(conta);
	}
	
//  GETTs and SETTs
	public UUID getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(UUID idBanco) {
		this.idBanco = idBanco;
	}

	public Integer getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(Integer codigoBanco) {
		this.codigoBanco = codigoBanco;
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
