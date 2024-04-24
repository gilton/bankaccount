package org.labs.bank.banksling.models;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.annotations.DynamicUpdate;
import org.labs.bank.banksling.models.enums.TipoConta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "TB_CONTA")
@DynamicUpdate
public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idConta;
	
	@Column(name = "numero_conta")
	private Long numeroConta;
	private Integer agencia;
	
	@Column(name = "tipo_conta")
	private TipoConta tipoConta;
	
	private Double saldo;
	
	@OneToOne
	@JoinColumn(name = "idBanco")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private Banco banco;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCliente")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private Cliente cliente;
	

//	 Methods
	public void depositar(Double valor) {
		this.setSaldo(getSaldo() + valor);
	}
	
	public void sacar(Double valor) {
		this.setSaldo(getSaldo() - valor);
	}
	
	public void transferencia(Conta beneficiario, Double valor) {
		beneficiario.setSaldo(beneficiario.getSaldo() + valor);
		this.setSaldo(getSaldo() - valor);
	}
	
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

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
