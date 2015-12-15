package com.leonardoz.estoque.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.leonardoz.estoque.model.Categoria;
import com.leonardoz.estoque.repository.Categorias;

@Named
@ViewScoped
public class CadastroCategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Categoria categoria = new Categoria();

	@Inject
	private Categorias categorias;

	public CadastroCategoriaBean() {

	}

	@PostConstruct
	public void iniciar() {
		if (categoria == null) {
			this.categoria = new Categoria();
		}
	}

	public List<Categoria> listarCategorias() {
		return categorias.recuperarCategorias();
	}

	public void salvarCategoria() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.categorias.guardarCategoria(categoria);
			this.categoria = new Categoria();
			context.addMessage(null, new FacesMessage("Categoria salva com sucesso!"));
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesMessage mensagem = new FacesMessage("Falha ao salvar a categoria.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public void removerCategoria() {
		System.err.println("REMOVENDO");
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.categorias.removerCategoria(Long.valueOf(this.categoria.getId()));
			this.categoria = new Categoria();
			context.addMessage(null, new FacesMessage("Categoria removida com sucesso!"));
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesMessage mensagem = new FacesMessage("Falha ao remover a categoria.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}

	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
