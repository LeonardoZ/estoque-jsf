package com.leonardoz.estoque.pessoa.funcionario;

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

import com.leonardoz.estoque.localizacao.Cidade;
import com.leonardoz.estoque.localizacao.Estado;
import com.leonardoz.estoque.localizacao.Localizacoes;
import com.leonardoz.estoque.modelo.valor.DiaDaSemana;

@Named
@ViewScoped
public class CadastroFuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Funcionario funcionario = new Funcionario();

	@Inject
	private Funcionarios funcionarios;

	@Inject
	private Localizacoes localizacoes;

	private Estado estadoSelecionado;

	public CadastroFuncionarioBean() {

	}

	@PostConstruct
	public void iniciar() {
		if (funcionario == null) {
			funcionario = new Funcionario();
		}
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
			context.addMessage(null, new FacesMessage("Funcionario salva com sucesso!"));
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesMessage mensagem = new FacesMessage("Falha ao salvar a funcionario.");
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
			context.addMessage(null, new FacesMessage("Funcionario removida com sucesso!"));
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesMessage mensagem = new FacesMessage("Falha ao remover o funcionario.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public void aoSelecionarFuncionario(Funcionario param) {
		this.funcionario = param;
		Optional.ofNullable(this.funcionario.getEndereco().getCidade()).ifPresent(c -> configuraEstadoSelecionado(c.getEstado()));
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Funcionario selecionado"));
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

	public Funcionario getFuncionario() {
		return funcionario;
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
