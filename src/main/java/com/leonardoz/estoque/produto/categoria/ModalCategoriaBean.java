package com.leonardoz.estoque.produto.categoria;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class ModalCategoriaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Categoria categoria;

    @Inject
    private Categorias categorias;

    @PostConstruct
    public void iniciar() {
	if (categoria == null)
	    categoria = new Categoria();
    }

    public void cadastrarEmModal() {
	Map<String, Object> options = new HashMap<String, Object>();
	options.put("resizable", false);
	options.put("draggable", true);
	options.put("modal", true);
	RequestContext.getCurrentInstance().openDialog("ModalCategoria", options, null);
    }

    public void concluirModal() {
	this.categorias.guardarCategoria(categoria);
	fecharModal();
    }

    public void fecharModal() {
	RequestContext.getCurrentInstance().closeDialog(null);
    }

    public Categoria getCategoria() {
	return categoria;
    }

    public void setUnidade(Categoria categoria) {
	this.categoria = categoria;
    }
}
