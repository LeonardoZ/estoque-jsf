package com.leonardoz.estoque.pessoa.cliente;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.leonardoz.estoque.pessoa.PessoaJuridica;

@Entity
@Table(name = "cliente_juridico")
public class ClienteJuridico extends PessoaJuridica implements Serializable {

	private static final long serialVersionUID = 1L;

}
