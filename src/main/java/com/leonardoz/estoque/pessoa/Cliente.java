package com.leonardoz.estoque.pessoa;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente extends PessoaFisica {

	private static final long serialVersionUID = 1L;
	

}
