package com.leonardoz.estoque.compra;

import java.io.Serializable;
import java.util.Date;
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

import com.leonardoz.estoque.fornecedor.Fornecedor;
import com.leonardoz.estoque.fornecedor.Fornecedores;
import com.leonardoz.estoque.produto.Dinheiro;
import com.leonardoz.estoque.produto.Produto;
import com.leonardoz.estoque.produto.Produtos;
import com.leonardoz.estoque.produto.Quantidade;

@Named
@ViewScoped
public class CadastroCompraBean implements Serializable, RealizaPagamento {

    private static final long serialVersionUID = 1L;
    private Compra compra = new Compra();
    private FiltroCompra filtroCompra;

    @Inject
    private Compras compras;

    @Inject
    private Fornecedores fornecedores;

    @Inject
    private Produtos produtos;

    private ProdutoDeCompra produtoDeCompra;

    private Produto produtoSelecionado;

    private LazyDataModel<Compra> model;

    private boolean podeCalcularParcela;

    public CadastroCompraBean() {

    }

    @PostConstruct
    public void iniciar() {
	if (compra == null) {
	    compra = new Compra();
	}
	if (produtoDeCompra == null) {
	    produtoDeCompra = new ProdutoDeCompra();
	    produtoDeCompra.setCompra(compra);
	}
	if (produtoSelecionado == null) {
	    produtoSelecionado = new Produto();
	}
	filtroCompra = new FiltroCompra();
	model = new LazyDataModel<Compra>() {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public List<Compra> load(int first, int pageSize, String sortField, SortOrder sortOrder,
		    Map<String, Object> filters) {

		filtroCompra.setPrimeiroRegistro(first);
		filtroCompra.setQuantidadeRegistros(pageSize);
		filtroCompra.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
		filtroCompra.setPropriedadeOrdenacao(sortField);

		setRowCount(compras.quantosForamFiltrados(filtroCompra));

		return compras.filtrados(filtroCompra);
	    }
	};
    }

    public void novoCompra() {
	iniciar();
    }

    public void aoSelecionarCompra(Compra param) {
	this.compra = param;
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Compra selecionado"));
    }

    public List<Compra> listarCompras() {
	return compras.recuperarCompras();
    }

    public void salvarCompra() {
	FacesContext context = FacesContext.getCurrentInstance();
	try {
	    this.compra.setData(new Date());
	    this.compra.getPagamento().gerarParcelas();
	    this.compras.guardarCompra(compra);
	    this.compra = new Compra();
	    context.addMessage(null, new FacesMessage("Compra salva com sucesso!"));
	} catch (RuntimeException ex) {
	    ex.printStackTrace();
	    FacesMessage mensagem = new FacesMessage("Falha ao salvar a compra.");
	    mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
	    context.addMessage(null, mensagem);
	}
    }

    public void calcularPagamento() {
	this.compra.getPagamento().gerarParcelas();
    }

    public void novoProdutoDeCompra() {
	this.produtoDeCompra = new ProdutoDeCompra();
    }

    public void adicionarProdutoACompra() {
	FacesContext context = FacesContext.getCurrentInstance();
	try {
	    this.compra.adicionarProduto(produtoDeCompra);
	    this.produtoDeCompra = new ProdutoDeCompra();
	} catch (RuntimeException ex) {
	    ex.printStackTrace();
	    FacesMessage mensagem = new FacesMessage("Falha ao adicionar produto.");
	    mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
	    context.addMessage(null, mensagem);
	}
    }

    public void removerProdutoDeCompra() {
	FacesContext context = FacesContext.getCurrentInstance();
	try {
	    this.compra.removerProduto(produtoDeCompra);
	} catch (RuntimeException ex) {
	    ex.printStackTrace();
	    FacesMessage mensagem = new FacesMessage("Falha ao adicionar produto.");
	    mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
	    context.addMessage(null, mensagem);
	}
    }

    public void aoSelecionarProduto() {
	Dinheiro custo = produtoSelecionado.getPrecoDeCusto();
	if (produtoDeCompra.getValorDeCompra() == null) {
	    produtoDeCompra.setProduto(produtoSelecionado);
	    produtoDeCompra.setValorDeCompra(custo);
	}
    }

    public void avaliarSePodeCalcular() {
	boolean semValor = compra.getPagamento().getValorPagamento().isZero();
	boolean temNumeroDeParcelas = compra.getPagamento().getParcelamento().getQuantasVezes()
		.equals(new Quantidade(0));
	this.podeCalcularParcela = !semValor && temNumeroDeParcelas;
	System.out.println(semValor);
	System.out.println(temNumeroDeParcelas);
    }

    public Compra getCompra() {
	return compra;
    }

    public void setCompra(Compra compra) {
	this.compra = compra;
    }

    public ProdutoDeCompra getProdutoDeCompra() {
	return produtoDeCompra;
    }
    
    public boolean isPodeCalcularParcela() {
	return podeCalcularParcela;
    }

    public void setProdutoDeCompra(ProdutoDeCompra produtoDeCompra) {
	this.produtoDeCompra = produtoDeCompra;
    }

    public LazyDataModel<Compra> getModel() {
	return model;
    }

    public void setModel(LazyDataModel<Compra> model) {
	this.model = model;
    }

    public Produto getProdutoSelecionado() {
	return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
	this.produtoSelecionado = produtoSelecionado;
    }

    public List<Fornecedor> fornecedores() {
	return fornecedores.recuperarFornecedores();
    }

    public List<Produto> produtos() {
	return produtos.recuperarProdutos();
    }

}
