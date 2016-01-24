package com.leonardoz.estoque.funcionario;

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

import com.leonardoz.estoque.cliente.ContratoDeTrabalho;
import com.leonardoz.estoque.localizacao.Cidade;
import com.leonardoz.estoque.localizacao.Estado;
import com.leonardoz.estoque.localizacao.Localizacoes;
import com.leonardoz.estoque.modelo.valor.DiaDaSemana;
import com.leonardoz.estoque.pessoa.FiltroPessoaFisica;
import com.leonardoz.estoque.pessoa.Sexo;

@Named
@ViewScoped
public class CadastroFuncionarioBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Funcionario funcionario = new Funcionario();
    private ContratoDeTrabalho contrato = new ContratoDeTrabalho();

    @Inject
    private Funcionarios funcionarios;

    @Inject
    private Localizacoes localizacoes;

    private FiltroPessoaFisica filtro;

    private LazyDataModel<Funcionario> model;

    private Estado estadoSelecionado;

    public CadastroFuncionarioBean() {
	filtro = new FiltroPessoaFisica();
	model = new LazyDataModel<Funcionario>() {
	    private static final long serialVersionUID = 1L;

	    @Override
	    public List<Funcionario> load(int first, int pageSize, String sortField, SortOrder sortOrder,
		    Map<String, Object> filters) {

		filtro.setPrimeiroRegistro(first);
		filtro.setQuantidadeRegistros(pageSize);
		filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
		filtro.setPropriedadeOrdenacao(sortField);

		setRowCount(funcionarios.quantosForamFiltrados(filtro));

		return funcionarios.filtrados(filtro);
	    }
	};
    }

    @PostConstruct
    public void iniciar() {
	if (funcionario == null) {
	    funcionario = new Funcionario();
	}
    }

    public LazyDataModel<Funcionario> getModel() {
	return model;
    }

    public void setModel(LazyDataModel<Funcionario> model) {
	this.model = model;
    }

    public List<Funcionario> listarFuncionarios() {
	List<Funcionario> funcionariosEncontrados = funcionarios.recuperarFuncionarios();
	return funcionariosEncontrados;
    }

    public void salvarFuncionario() {
	FacesContext context = FacesContext.getCurrentInstance();
	try {
	    this.funcionarios.guardarFuncionario(funcionario);
	    this.funcionario = new Funcionario();
	    context.addMessage(null, new FacesMessage("Funcionario salvo com sucesso!"));
	} catch (RuntimeException ex) {
	    FacesMessage mensagem = new FacesMessage("Falha ao salvar o funcionário");
	    mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
	    context.addMessage(null, mensagem);
	}
    }

    public void novoFuncionario() {
	this.funcionario = new Funcionario();
    }

    public void removerFuncionario(Funcionario funcionario) {
	this.funcionario = funcionario;
	FacesContext context = FacesContext.getCurrentInstance();
	try {
	    this.funcionarios.removerFuncionario(Long.valueOf(this.funcionario.getId()));
	    this.funcionario = new Funcionario();
	    context.addMessage(null, new FacesMessage("Funcionario removido com sucesso!"));
	} catch (RuntimeException ex) {
	    ex.printStackTrace();
	    FacesMessage mensagem = new FacesMessage("Falha ao remover o funcionario.");
	    mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
	    context.addMessage(null, mensagem);
	}
    }

    public void aoSelecionarFuncionario(Funcionario param) {
	this.funcionario = param;
	Optional.ofNullable(this.funcionario.getEndereco().getCidade())
		.ifPresent(c -> configuraEstadoSelecionado(c.getEstado()));
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Funcionario selecionado"));
    }

    public void novoContrato() {
	this.contrato = new ContratoDeTrabalho();
    }

    public void salvarContrato() {
	boolean contratoNovo = contrato.getFuncionario() == null;
	if (contratoNovo) {
	    funcionario.adicionarContrato(contrato);
	}
	contrato = new ContratoDeTrabalho();
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contrato adicionado"));
    }

    public void aoSelecionarContrato(ContratoDeTrabalho contrato) {
	this.contrato = contrato;
    }

    public List<Sexo> sexos() {
	return Sexo.listar();
    }

    public List<Habilitacao> habilitacoes() {
	return Habilitacao.listar();
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

    public Funcionario getFuncionario() {
	return funcionario;
    }

    public ContratoDeTrabalho getContrato() {
	return contrato;
    }

    public void setContrato(ContratoDeTrabalho contrato) {
	this.contrato = contrato;
    }

    public void setFuncionario(Funcionario funcionario) {
	this.funcionario = funcionario;
    }

    public Estado getEstadoSelecionado() {
	return estadoSelecionado;
    }

    public void setEstadoSelecionado(Estado estadoSelecionado) {
	this.estadoSelecionado = estadoSelecionado;
    }

}
