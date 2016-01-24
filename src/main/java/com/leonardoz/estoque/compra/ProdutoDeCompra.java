package com.leonardoz.estoque.compra;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.leonardoz.estoque.modelo.entidade.Entidade;
import com.leonardoz.estoque.produto.Dinheiro;
import com.leonardoz.estoque.produto.Produto;
import com.leonardoz.estoque.produto.QuantidadeFracionada;

@Entity
@Table(name = "produto_de_compra")
public class ProdutoDeCompra extends Entidade implements Comparable<ProdutoDeCompra> {

    private static final long serialVersionUID = 1L;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "compra_id", nullable = false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Embedded
    @AttributeOverrides(@AttributeOverride(name = "valor", column = @Column(name = "quantidade", nullable = false, precision = 10, scale = 4) ))
    private QuantidadeFracionada quantidade;

    @Embedded
    @AttributeOverrides(@AttributeOverride(name = "montanteBruto", column = @Column(name = "valor_de_compra", nullable = false, precision = 10, scale = 4) ))
    private Dinheiro valorDeCompra;

    public ProdutoDeCompra() {
	quantidade = new QuantidadeFracionada(0);
    }

    public ProdutoDeCompra(Compra compra, Produto produto, QuantidadeFracionada quantidade) {
	super();
	this.compra = compra;
	this.quantidade = quantidade;
	setProduto(produto);
    }

    public Compra getCompra() {
	return compra;
    }

    public void setCompra(Compra compra) {
	this.compra = compra;
    }

    public Produto getProduto() {
	return produto;
    }

    public void setProduto(Produto produto) {
	this.produto = produto;
	if (valorDeCompra == null) {
	    valorDeCompra = produto.getPrecoDeCusto();
	}
    }

    public QuantidadeFracionada getQuantidade() {
	return quantidade;
    }

    public void setQuantidade(QuantidadeFracionada quantidade) {
	this.quantidade = quantidade;
    }

    public Dinheiro getValorDeCompra() {
	return valorDeCompra;
    }

    public void setValorDeCompra(Dinheiro valorDeCompra) {
	this.valorDeCompra = valorDeCompra;
	if (valorDeCompra != null)
	    this.produto.setPrecoDeCusto(valorDeCompra);
    }

    public Dinheiro valorTotal() {
	return valorDeCompra.multiplicar(quantidade.getValor().doubleValue());
    }

    @Override
    public int compareTo(final ProdutoDeCompra other) {
	return new CompareToBuilder().append(produto, other.produto).append(quantidade, other.quantidade)
		.append(valorDeCompra, other.valorDeCompra).toComparison();
    }

    @Override
    public boolean equals(final Object other) {
	if (!(other instanceof ProdutoDeCompra)) {
	    return false;
	}
	ProdutoDeCompra castOther = (ProdutoDeCompra) other;
	return new EqualsBuilder().append(produto, castOther.produto).append(quantidade, castOther.quantidade)
		.append(valorDeCompra, castOther.valorDeCompra).isEquals();
    }

    @Override
    public int hashCode() {
	return new HashCodeBuilder().append(produto).append(quantidade).append(valorDeCompra).toHashCode();
    }

}
