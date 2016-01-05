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
import com.leonardoz.estoque.pessoa.FiltroPessoaJuridica;

@Named
@ViewScoped
public class CadastroClienteJuridicoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ClienteJuridico cliente = new ClienteJuridico();

	@Inject
	private ClientesJuridicos clientes;

	@Inject
	private Localizacoes localizacoes;
	
	private FiltroPessoaJuridica filtro;
	private LazyDataModel<ClienteJuridico> model;
	
	
	private Estado estadoSelecionado;

	public CadastroClienteJuridicoBean() {
		filtro = new FiltroPessoaJuridica();
		model = new LazyDataModel<ClienteJuridico>() {
			private static final long serialVersionUID = 1L;

			@Override
			public List<ClienteJuridico> load(int first, int pageSize, String sortField, SortOrder sortOrder,
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

	@PostConstruct
	public void iniciar() {
		if (cliente == null) {
			cliente = new ClienteJuridico();
		}
	}
	
	public LazyDataModel<ClienteJuridico> getModel() {
		return model;
	}
	
	public void setModel(LazyDataModel<ClienteJuridico> model) {
		this.model = model;
	}

	public List<ClienteJuridico> listarClientesJuridicos() {
		List<ClienteJuridico> clientesEncontrados = clientes.recuperarClientesJuridicos();
		return clientesEncontrados;
	}

	public void salvarClienteJuridico() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.clientes.guardarClienteJuridico(cliente);
			this.cliente = new ClienteJuridico();
			context.addMessage(null, new FacesMessage("Cliente juridico salvo com sucesso!"));
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesMessage mensagem = new FacesMessage("Falha ao salvar o cliente.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public void novoClienteJuridico() {
		this.cliente = new ClienteJuridico();
	}

	public void removerClienteJuridico(ClienteJuridico cliente) {
		this.cliente = cliente;
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.clientes.removerClienteJuridico(Long.valueOf(this.cliente.getId()));
			this.cliente = new ClienteJuridico();
			context.addMessage(null, new FacesMessage("Cliente Juridico removido com sucesso!"));
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesMessage mensagem = new FacesMessage("Falha ao remover o cliente.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public void aoSelecionarClienteJuridico(ClienteJuridico param) {
		this.cliente = param;
		Optional.ofNullable(this.cliente.getEndereco().getCidade()).ifPresent(c -> configuraEstadoSelecionado(c.getEstado()));
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente jurídico selecionado"));
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

	public ClienteJuridico getClienteJuridico() {
		return cliente;
	}

	public void setClienteJuridico(ClienteJuridico cliente) {
		this.cliente = cliente;
	}

	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

}
