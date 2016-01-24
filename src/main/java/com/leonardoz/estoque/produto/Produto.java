package com.leonardoz.estoque.produto;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.leonardoz.estoque.modelo.entidade.Entidade;
import com.leonardoz.estoque.produto.categoria.Categoria;
import com.leonardoz.estoque.produto.unidade.UnidadeDeMedida;

@Entity
@Table(name = "produto")
public class Produto extends Entidade {

    private static final long serialVersionUID = 1L;

    @Column(name = "codigo", nullable = false, length = 25)
    private String codigo;

    @Column(name = "descricao", nullable = false, length = 200)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "unidade_de_medida_id", nullable = false)
    private UnidadeDeMedida unidadeMedida;

    @Embedded
    @AttributeOverrides({
	    @AttributeOverride(name = "montanteBruto", column = @Column(name = "preco_venda", nullable = false, scale = 2, precision = 10) ) })
    private Dinheiro precoDeVenda;

    @Embedded
    @AttributeOverrides({
	    @AttributeOverride(name = "montanteBruto", column = @Column(name = "preco_custo", nullable = false, scale = 2, precision = 10) ) })
    private Dinheiro precoDeCusto;

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "valor", column = @Column(name = "quantidade_estoque") ) })
    private Quantidade quantidadeEmEstoque;

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "valor", column = @Column(name = "limite_minimo_estoque") ) })
    private Quantidade limiteMinimoDeEstoque;

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "valor", column = @Column(name = "limite_maximo_estoque") ) })
    private Quantidade limiteMaximoDeEstoque;

    @Column(name = "observacao")
    private String observacao;

    public Produto() {
	if (categoria == null)
	    categoria = new Categoria();
	if (unidadeMedida == null)
	    unidadeMedida = new UnidadeDeMedida();
	if (quantidadeEmEstoque == null)
	    quantidadeEmEstoque = new Quantidade();
	if (limiteMaximoDeEstoque == null)
	    limiteMaximoDeEstoque = new Quantidade();
	if (limiteMinimoDeEstoque == null)
	    limiteMinimoDeEstoque = new Quantidade();
	if (precoDeCusto == null)
	    precoDeCusto = new Dinheiro();
	if (precoDeVenda == null)
	    precoDeVenda = new Dinheiro();

    }

    public String getCodigo() {
	return codigo;
    }

    public void setCodigo(String codigo) {
	this.codigo = codigo;
    }

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public Categoria getCategoria() {
	return categoria;
    }

    public void setCategoria(Categoria categoria) {
	this.categoria = categoria;
    }

    public Dinheiro getPrecoDeVenda() {
	return precoDeVenda;
    }

    public void setPrecoDeVenda(Dinheiro precoDeVenda) {
	if (precoDeCusto.isMaior(precoDeVenda)) {
	    throw new IllegalArgumentException("Preço de Venda não pode ser menor do que o Preço de Custo!.");
	}
	this.precoDeVenda = precoDeVenda;
    }

    public Dinheiro getPrecoDeCusto() {
	return precoDeCusto;
    }

    public void setPrecoDeCusto(Dinheiro precoCusto) {
	this.precoDeCusto = precoCusto;
    }

    public UnidadeDeMedida getUnidadeMedida() {
	return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeDeMedida unidadeMedida) {
	this.unidadeMedida = unidadeMedida;
    }

    public Quantidade getQuantidadeEmEstoque() {
	return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(Quantidade quantidadeEmEstoque) {
	this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public Quantidade getLimiteMinimoDeEstoque() {
	return limiteMinimoDeEstoque;
    }

    public void setLimiteMinimoDeEstoque(Quantidade limiteMinimoDeEstoque) {
	this.limiteMinimoDeEstoque = limiteMinimoDeEstoque;
    }

    public Quantidade getLimiteMaximoDeEstoque() {
	return limiteMaximoDeEstoque;
    }

    public void setLimiteMaximoDeEstoque(Quantidade limiteMaximoDeEstoque) {
	this.limiteMaximoDeEstoque = limiteMaximoDeEstoque;
    }

    public String getObservacao() {
	return observacao;
    }

    public void setObservacao(String observacao) {
	this.observacao = observacao;
    }

    public void aumentarPrecoDeCusto(Dinheiro aumento) {
	if (aumento.isMaior(Dinheiro.ZERO)) {
	    this.precoDeCusto = this.precoDeCusto.somar(aumento);
	}
    }

    public void diminuirPrecoDeCusto(Dinheiro valor) {
	if (this.precoDeCusto.isMaior(valor)) {
	    this.precoDeCusto = this.precoDeCusto.subtrair(valor);
	} else {
	    throw new IllegalArgumentException("Custo não pode ser negativo.");
	}
    }

    public void aumentarPrecoDeVenda(Dinheiro aumento) {
	if (aumento.isMaior(Dinheiro.ZERO)) {
	    this.precoDeVenda = this.precoDeVenda.somar(aumento);
	}
    }

    public void diminuirPrecoDeVenda(Dinheiro valor) {
	boolean valorSuficienteParaSubtracao = this.precoDeVenda.isMaior(valor);
	if (!valorSuficienteParaSubtracao) {
	    throw new IllegalArgumentException("Custo não pode ser negativo.");
	}
	Dinheiro novoPrecoVenda = precoDeVenda.subtrair(valor);
	if (precoDeCusto.isMenor(novoPrecoVenda)) {
	    this.precoDeVenda = novoPrecoVenda;
	} else {
	    throw new IllegalArgumentException("Preço de Venda não pode ser menor do que o Preço de Custo!.");
	}
    }

    public void aumentarLimiteMaximoDeEstoque(Quantidade valor) {
	this.limiteMaximoDeEstoque = this.limiteMaximoDeEstoque.aumentar(valor);
    }

    public void diminuirLimiteMaximoDeEstoque(Quantidade valor) {
	boolean valorSuficienteParaSubtracao = this.limiteMaximoDeEstoque.isMaiorIgual(valor);
	if (!valorSuficienteParaSubtracao) {
	    throw new IllegalArgumentException("Limite máximo não pode ser negativo.");
	}

	Quantidade novoValor = this.limiteMaximoDeEstoque.diminuir(valor);
	boolean maximoMaiorQueMinimo = novoValor.isMaiorIgual(limiteMinimoDeEstoque);
	if (maximoMaiorQueMinimo) {
	    this.limiteMaximoDeEstoque = novoValor;
	} else {
	    throw new IllegalArgumentException("Limite máximo não pode meneos que o Limite mínimo.");
	}
    }

    public void aumentarLimiteMinimoDeEstoque(Quantidade valor) {
	Quantidade novoValor = this.limiteMinimoDeEstoque.aumentar(valor);
	boolean minimoMenorQueMaximo = !novoValor.isMaior(limiteMaximoDeEstoque);
	if (minimoMenorQueMaximo) {
	    this.limiteMaximoDeEstoque = novoValor;
	} else {
	    throw new IllegalArgumentException("Limite mínimo não pode meneos que o Limite mínimo.");
	}

	this.limiteMinimoDeEstoque = this.limiteMinimoDeEstoque.aumentar(valor);
    }

    public void diminuirLimiteMinimoDeEstoque(Quantidade valor) {
	boolean valorSuficienteParaSubtracao = this.limiteMinimoDeEstoque.isMaiorIgual(valor);
	if (!valorSuficienteParaSubtracao) {
	    throw new IllegalArgumentException("Limite máximo não pode ser negativo.");
	}
	this.limiteMinimoDeEstoque = this.limiteMinimoDeEstoque.diminuir(valor);
    }

    @Override
    public boolean equals(final Object other) {
	if (!(other instanceof Produto)) {
	    return false;
	}
	Produto castOther = (Produto) other;
	return new EqualsBuilder().append(codigo, castOther.codigo).append(descricao, castOther.descricao)
		.append(categoria, castOther.categoria).append(precoDeVenda, castOther.precoDeVenda)
		.append(precoDeCusto, castOther.precoDeCusto).append(unidadeMedida, castOther.unidadeMedida)
		.append(quantidadeEmEstoque, castOther.quantidadeEmEstoque).isEquals();
    }

    @Override
    public int hashCode() {
	return new HashCodeBuilder().append(codigo).append(descricao).append(categoria).append(precoDeVenda)
		.append(precoDeCusto).append(unidadeMedida).append(quantidadeEmEstoque).toHashCode();
    }

}
