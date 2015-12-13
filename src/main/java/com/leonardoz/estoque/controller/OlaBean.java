package com.leonardoz.estoque.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class OlaBean {

	private String meuNome = "Zapparoli";
	
	@PostConstruct
	public void configure() {
		meuNome = "Leonardo";
	}

	public String getMeuNome() {
		return meuNome;
	}

	public void setMeuNome(String meuNome) {
		this.meuNome = meuNome;
	}

}
