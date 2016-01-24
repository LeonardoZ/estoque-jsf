package com.leonardoz.estoque.compra;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.leonardoz.estoque.fornecedor.Fornecedor;
import com.leonardoz.estoque.modelo.entidade.Entidade;
import com.leonardoz.estoque.pagamento.Pagamento;
import com.leonardoz.estoque.produto.Dinheiro;

@Entity
@Table(name = "compra")
public class Compra extends Entidade {

    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data", nullable = false)
    private Date data;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Fornecedor fornecedor;

    @Column(length = 30)
    private String numeroNotaFiscal;

    @Embedded
    @AttributeOverrides(@AttributeOverride(name = "montanteBruto", column = @Column(name = "valor_frete", nullable = false, precision = 10, scale = 4) ))
    private Dinheiro frete;

    @Embedded
    private ProdutosComprados produtosComprados;

    @OneToOne(orphanRemoval = true, cascade = { CascadeType.ALL })
    @JoinColumn(name = "pagamento_id", nullable = true)
    private Pagamento pagamento;

    public Compra() {
	produtosComprados = new ProdutosComprados();
	frete = Dinheiro.ZERO;
	pagamento = new Pagamento();
    }

    public Date getData() {
	return data;
    }

    public void setData(Date data) {
	this.data = data;
    }

    public Fornecedor getFornecedor() {
	return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
	this.fornecedor = fornecedor;
    }

    public String getNumeroNotaFiscal() {
	return numeroNotaFiscal;
    }

    public void setNumeroNotaFiscal(String numeroNotaFiscal) {
	this.numeroNotaFiscal = numeroNotaFiscal;
    }

    public Dinheiro getFrete() {
	return frete;
    }

    public void setFrete(Dinheiro frete) {
	this.frete = frete;
	this.pagamento.setValorPagamento(valorTotal());
    }

    public ProdutosComprados getProdutosComprados() {
	return produtosComprados;
    }

    public void adicionarProduto(ProdutoDeCompra produto) {
	produto.setCompra(this);
	produtosComprados.adicionarProduto(produto);
    }

    public void removerProduto(ProdutoDeCompra produto) {
	produtosComprados.removerProduto(produto);
    }

    public Dinheiro precoTotal() {
	return produtosComprados.precoTotal();
    }

    public Dinheiro valorTotal() {
	Dinheiro precoTotal = valorDosProdutos();
	if (frete != null) {
	    precoTotal = precoTotal.somar(frete);
	}
	return precoTotal;
    }

    public Dinheiro valorDosProdutos() {
	Dinheiro precoTotal = produtosComprados.precoTotal();
	return precoTotal;
    }

    public Pagamento getPagamento() {
	return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
	this.pagamento = pagamento;
    }

    public void configuraPagamento() {
	pagamento.setValorPagamento(valorTotal());
	pagamento.gerarParcelas();
    }

    @Override
    public boolean equals(final Object other) {
	if (!(other instanceof Compra)) {
	    return false;
	}
	Compra castOther = (Compra) other;
	return new EqualsBuilder().append(data, castOther.data).append(fornecedor, castOther.fornecedor)
		.append(numeroNotaFiscal, castOther.numeroNotaFiscal).append(frete, castOther.frete)
		.append(produtosComprados, castOther.produtosComprados).isEquals();
    }

    @Override
    public int hashCode() {
	return new HashCodeBuilder().append(data).append(fornecedor).append(numeroNotaFiscal).append(frete)
		.append(produtosComprados).toHashCode();
    }

}
