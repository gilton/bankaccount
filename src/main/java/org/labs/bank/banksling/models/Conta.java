package org.labs.bank.banksling.models;

import java.io.Serializable;
import java.util.UUID;

import org.labs.bank.banksling.models.enums.TipoConta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "TB_CONTA")
public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idConta;
	
	@Column(name = "numero_conta")
	private Long numeroConta;
	
	@Column(name = "tipo_conta")
	private TipoConta tipoConta;
	
	private Double saldo;
	
	@OneToOne
	private Banco banco;
	
	
//  GETTs and SETTs
	public UUID getIdConta() {
		return idConta;
	}

	public void setIdConta(UUID idConta) {
		this.idConta = idConta;
	}

	public Long getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Long numeroConta) {
		this.numeroConta = numeroConta;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	
	
}
