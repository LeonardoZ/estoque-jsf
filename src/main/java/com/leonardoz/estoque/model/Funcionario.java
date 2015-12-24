package com.leonardoz.estoque.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "funcionario")
public class Funcionario extends PessoaFisica {

	private static final long serialVersionUID = 1L;
	
	public Funcionario() {
		super();
	}
	
	

}
