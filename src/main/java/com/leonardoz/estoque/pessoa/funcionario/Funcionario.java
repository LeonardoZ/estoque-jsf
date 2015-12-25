package com.leonardoz.estoque.pessoa.funcionario;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.leonardoz.estoque.pessoa.PessoaFisica;

@Entity
@Table(name = "funcionario")
public class Funcionario extends PessoaFisica {

	private static final long serialVersionUID = 1L;
	
	public Funcionario() {
		super();
	}
	
	

}
