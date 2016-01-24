package com.leonardoz.estoque.cliente;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.leonardoz.estoque.pessoa.PessoaFisica;

@Entity
@Table(name = "cliente_fisico")
public class ClienteFisico extends PessoaFisica implements Serializable {

    private static final long serialVersionUID = 1L;

    public ClienteFisico() {
	super();
    }

}
