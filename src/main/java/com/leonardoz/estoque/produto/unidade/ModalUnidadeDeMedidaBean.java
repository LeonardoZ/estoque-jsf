package com.leonardoz.estoque.produto.unidade;

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
public class ModalUnidadeDeMedidaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private UnidadeDeMedida unidade;

	@Inject
	private UnidadesDeMedida unidades;

	@PostConstruct
	public void iniciar() {
		if (unidade == null)
			unidade = new UnidadeDeMedida();
	}

	public void cadastrarEmModal() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("resizable", false);
		options.put("draggable", false);
		options.put("modal", true);
		RequestContext.getCurrentInstance().openDialog("ModalUnidadeDeMedida", options, null);
	}

	public void concluirModal() {
		System.out.println("Cadastrando Unidade");
		this.unidades.guardarUnidadeDeMedida(unidade);
		fecharModal();
	}

	public void fecharModal() {
		RequestContext.getCurrentInstance().closeDialog(null);
	}

	public UnidadeDeMedida getUnidade() {
		return unidade;
	}

	public void setUnidade(UnidadeDeMedida unidade) {
		this.unidade = unidade;
	}
}
