package com.leonardoz.estoque.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.leonardoz.estoque.model.Cidade;
import com.leonardoz.estoque.model.Estado;
import com.leonardoz.estoque.model.Fornecedor;
import com.leonardoz.estoque.model.values.DiaDaSemana;
import com.leonardoz.estoque.repository.Fornecedores;
import com.leonardoz.estoque.repository.Localizacoes;

@Named
@ViewScoped
public class CadastroFornecedorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Fornecedor fornecedor = new Fornecedor();

	@Inject
	private Fornecedores fornecedores;

	@Inject
	private Localizacoes localizacoes;

	private Estado estadoSelecionado;

	public CadastroFornecedorBean() {

	}

	@PostConstruct
	public void iniciar() {
		if (fornecedor == null) {
			fornecedor = new Fornecedor();
		}
	}

	public List<Fornecedor> listarFornecedores() {
		List<Fornecedor> fornecedoresEncontrados = fornecedores.recuperarFornecedores();
		return fornecedoresEncontrados;
	}

	public void salvarFornecedor() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.fornecedores.guardarFornecedor(fornecedor);
			this.fornecedor = new Fornecedor();
			context.addMessage(null, new FacesMessage("Fornecedor salva com sucesso!"));
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesMessage mensagem = new FacesMessage("Falha ao salvar a fornecedor.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public void novoFornecedor() {
		this.fornecedor = new Fornecedor();
	}

	public void removerFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.fornecedores.removerFornecedor(Long.valueOf(this.fornecedor.getId()));
			this.fornecedor = new Fornecedor();
			context.addMessage(null, new FacesMessage("Fornecedor removida com sucesso!"));
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesMessage mensagem = new FacesMessage("Falha ao remover o fornecedor.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public void aoSelecionarFornecedor(Fornecedor param) {
		this.fornecedor = param;
		Optional.ofNullable(this.fornecedor.getEndereco().getCidade()).ifPresent(c -> configuraEstadoSelecionado(c.getEstado()));
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fornecedor selecionado"));
	}
	
	public void configuraEstadoSelecionado(Estado estado){
		this.estadoSelecionado = estado;
	}

	public List<DiaDaSemana> listaDiasDaSemana() {
		return DiaDaSemana.listar();
	}

	public Set<Estado> listaEstados() {
		return localizacoes.selecionarTodosEstados();
	}

	public List<Cidade> listarCidadesPorEstado() {
		return localizacoes.buscaCidadesPorEstado(estadoSelecionado);
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

}
