package com.leonardoz.estoque.pessoa.cliente;

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
import com.leonardoz.estoque.pessoa.FiltroPessoaFisica;

@Named
@ViewScoped
public class CadastroClienteFisicoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ClienteFisico cliente = new ClienteFisico();

	@Inject
	private ClientesFisicos clientes;

	@Inject
	private Localizacoes localizacoes;

	private FiltroPessoaFisica filtro;
	private LazyDataModel<ClienteFisico> model;
	private Estado estadoSelecionado;

	public CadastroClienteFisicoBean() {
		filtro = new FiltroPessoaFisica();
		model = new LazyDataModel<ClienteFisico>() {
			private static final long serialVersionUID = 1L;

			@Override
			public List<ClienteFisico> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				filtro.setPrimeiroRegistro(first);
				filtro.setQuantidadeRegistros(pageSize);
				filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				filtro.setPropriedadeOrdenacao(sortField);

				setRowCount(clientes.quantosForamFiltrados(filtro));

				return clientes.filtrados(filtro);
			}
		};
	}

	public LazyDataModel<ClienteFisico> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<ClienteFisico> model) {
		this.model = model;
	}

	@PostConstruct
	public void iniciar() {
		if (cliente == null) {
			cliente = new ClienteFisico();
		}
	}

	public List<ClienteFisico> listarClientesFisicos() {
		List<ClienteFisico> clientesEncontrados = clientes.recuperarClientesFisicos();
		return clientesEncontrados;
	}

	public void salvarClienteFisico() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.clientes.guardarClienteFisico(cliente);
			this.cliente = new ClienteFisico();
			context.addMessage(null, new FacesMessage("ClienteFisico salva com sucesso!"));
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesMessage mensagem = new FacesMessage("Falha ao salvar a cliente.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public void novoClienteFisico() {
		this.cliente = new ClienteFisico();
	}

	public void removerClienteFisico(ClienteFisico cliente) {
		this.cliente = cliente;
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.clientes.removerClienteFisico(Long.valueOf(this.cliente.getId()));
			this.cliente = new ClienteFisico();
			context.addMessage(null, new FacesMessage("ClienteFisico removida com sucesso!"));
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesMessage mensagem = new FacesMessage("Falha ao remover o cliente.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public void aoSelecionarClienteFisico(ClienteFisico param) {
		this.cliente = param;
		Optional.ofNullable(this.cliente.getEndereco().getCidade())
				.ifPresent(c -> configuraEstadoSelecionado(c.getEstado()));
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ClienteFisico selecionado"));
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

	public ClienteFisico getClienteFisico() {
		return cliente;
	}

	public void setClienteFisico(ClienteFisico cliente) {
		this.cliente = cliente;
	}

	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

}
