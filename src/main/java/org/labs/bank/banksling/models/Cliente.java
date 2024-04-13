package org.labs.bank.banksling.models;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import org.labs.bank.banksling.models.enums.Sexo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_CLIENTE")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCliente;
    
    @Column(name = "nom_cliente")
    private String nomCliente;
    
    @Column(nullable = false, unique = true)
    private String cpf;
    
    private Sexo sexo;
    
    @Column(name = "dat_nascido")
    private LocalDate datNascido;
    
    private String celular;
    
    private String email;
    
    @OneToOne(cascade = CascadeType.MERGE)
    private Endereco endereco;

//    GETTs and SETTs
    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDatNascido() {
        return datNascido;
    }

    public void setDatNascido(LocalDate datNascido) {
        this.datNascido = datNascido;
    }

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

    
    
}
