package com.leonardoz.estoque.produto.categoria;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
			categoria = new Categoria();
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

	public void novaCategoria() {
		this.categoria = new Categoria();
	}

	public void removerCategoria() {
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
