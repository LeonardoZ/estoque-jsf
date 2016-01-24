package com.leonardoz.estoque.fornecedor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.leonardoz.estoque.localizacao.Cidade;
import com.leonardoz.estoque.localizacao.Estado;
import com.leonardoz.estoque.localizacao.Localizacoes;
import com.leonardoz.estoque.modelo.valor.DiaDaSemana;
import com.leonardoz.estoque.pessoa.FiltroPessoaJuridica;

@Named
@ViewScoped
public class CadastroFornecedorBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Fornecedor fornecedor = new Fornecedor();

    @Inject
    private Fornecedores fornecedores;

    @Inject
    private Localizacoes localizacoes;

    private FiltroPessoaJuridica filtro;
    private LazyDataModel<Fornecedor> model;

    private Estado estadoSelecionado;

    public CadastroFornecedorBean() {
	filtro = new FiltroPessoaJuridica();
	model = new LazyDataModel<Fornecedor>() {
	    private static final long serialVersionUID = 1L;

	    @Override
	    public List<Fornecedor> load(int first, int pageSize, String sortField, SortOrder sortOrder,
		    Map<String, Object> filters) {

		filtro.setPrimeiroRegistro(first);
		filtro.setQuantidadeRegistros(pageSize);
		filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
		filtro.setPropriedadeOrdenacao(sortField);

		setRowCount(fornecedores.quantosForamFiltrados(filtro));

		return fornecedores.filtrados(filtro);
	    }
	};
    }

    @PostConstruct
    public void iniciar() {
	if (fornecedor == null) {
	    fornecedor = new Fornecedor();
	}
    }

    public LazyDataModel<Fornecedor> getModel() {
	return model;
    }

    public void setModel(LazyDataModel<Fornecedor> model) {
	this.model = model;
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
	    context.addMessage(null, new FacesMessage("Fornecedor salvo com sucesso!"));
	} catch (RuntimeException ex) {
	    ex.printStackTrace();
	    FacesMessage mensagem = new FacesMessage("Falha ao salvar o fornecedor.");
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
	    context.addMessage(null, new FacesMessage("Fornecedor removido com sucesso!"));
	} catch (RuntimeException ex) {
	    ex.printStackTrace();
	    FacesMessage mensagem = new FacesMessage("Falha ao remover o fornecedor.");
	    mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
	    context.addMessage(null, mensagem);
	}
    }

    public void aoSelecionarFornecedor(Fornecedor param) {
	this.fornecedor = param;
	Optional.ofNullable(this.fornecedor.getEndereco().getCidade())
		.ifPresent(c -> configuraEstadoSelecionado(c.getEstado()));
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fornecedor selecionado"));
    }

    public void configuraEstadoSelecionado(Estado estado) {
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
