package com.leonardoz.estoque.produto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.leonardoz.estoque.produto.categoria.Categoria;
import com.leonardoz.estoque.produto.categoria.Categorias;
import com.leonardoz.estoque.produto.unidade.UnidadeDeMedida;
import com.leonardoz.estoque.produto.unidade.UnidadesDeMedida;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Produto produto = new Produto();
	private FiltroProduto filtroProduto;

	@Inject
	private Produtos produtos;

	@Inject
	private Categorias categorias;

	@Inject
	private UnidadesDeMedida unidades;

	private LazyDataModel<Produto> model;

	public CadastroProdutoBean() {

	}

	@PostConstruct
	public void iniciar() {
		if (produto == null) {
			produto = new Produto();
		}
		filtroProduto = new FiltroProduto();
		model = new LazyDataModel<Produto>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Produto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				filtroProduto.setPrimeiroRegistro(first);
				filtroProduto.setQuantidadeRegistros(pageSize);
				filtroProduto.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				filtroProduto.setPropriedadeOrdenacao(sortField);

				setRowCount(produtos.quantosForamFiltrados(filtroProduto));

				return produtos.filtrados(filtroProduto);
			}
		};
	}

	public void novoProduto() {
		iniciar();
	}

	public void aoSelecionarProduto(Produto param) {
		this.produto = param;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto selecionado"));
	}

	public List<Produto> listarProdutos() {
		return produtos.recuperarProdutos();
	}

	public void salvarProduto() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.produtos.guardarProduto(produto);
			this.produto = new Produto();
			context.addMessage(null, new FacesMessage("Produto salvo com sucesso!"));
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesMessage mensagem = new FacesMessage("Falha ao salvar o produto.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public void removerProduto() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.produtos.removerProduto(Long.valueOf(this.produto.getId()));
			this.produto = new Produto();
			context.addMessage(null, new FacesMessage("Produto removido com sucesso!"));
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesMessage mensagem = new FacesMessage("Falha ao remover o produto.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}

	}

	public List<Categoria> recuperarCategorias() {
		return categorias.recuperarCategorias();
	}

	public List<UnidadeDeMedida> recuperarUnidadesDeMedida() {
		System.out.println("Carregando unidades");
		return unidades.recuperarUnidadeDeMedidas();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public LazyDataModel<Produto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Produto> model) {
		this.model = model;
	}

}
