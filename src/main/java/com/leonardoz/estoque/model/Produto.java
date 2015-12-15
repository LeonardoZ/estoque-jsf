package com.leonardoz.estoque.model;

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

import com.leonardoz.estoque.model.values.Dinheiro;
import com.leonardoz.estoque.model.values.Quantidade;
import com.leonardoz.estoque.model.values.QuantidadeFracionada;

@Entity
@Table(name = "produto")
public class Produto extends Entidade {

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
	@AttributeOverrides({ @AttributeOverride(name = "valor", column = @Column(name = "markup", nullable = false) ) })
	private QuantidadeFracionada markup;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "valor", column = @Column(name = "estoque_atual", nullable = false) ) })
	private Quantidade estoqueAtual;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "valor", column = @Column(name = "quantidade_estoque", nullable = false) ) })
	private Quantidade quantidadeEmEstoque;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "valor", column = @Column(name = "limite_minimo_estoque", nullable = false) ) })
	private Quantidade limiteMinimoDeEstoque;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "valor", column = @Column(name = "limite_maximo_estoque", nullable = false) ) })
	private Quantidade limiteMaximoDeEstoque;

	@Column(name = "observacao")
	private String observacao;

	public Produto() {

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
		this.precoDeVenda = precoDeVenda;
	}

	public Dinheiro getPrecoDeCusto() {
		return precoDeCusto;
	}

	public void setPrecoDeCusto(Dinheiro precoCusto) {
		this.precoDeCusto = precoCusto;
	}

	public QuantidadeFracionada getMarkup() {
		return markup;
	}

	public void setMarkup(QuantidadeFracionada markup) {
		this.markup = markup;
	}

	public Quantidade getEstoqueAtual() {
		return estoqueAtual;
	}

	public void setEstoqueAtual(Quantidade estoqueAtual) {
		this.estoqueAtual = estoqueAtual;
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

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Produto)) {
			return false;
		}
		Produto castOther = (Produto) other;
		return new EqualsBuilder().append(codigo, castOther.codigo).append(descricao, castOther.descricao)
				.append(categoria, castOther.categoria).append(precoDeVenda, castOther.precoDeVenda)
				.append(precoDeCusto, castOther.precoDeCusto).append(estoqueAtual, castOther.estoqueAtual)
				.append(unidadeMedida, castOther.unidadeMedida)
				.append(quantidadeEmEstoque, castOther.quantidadeEmEstoque).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(codigo).append(descricao).append(categoria).append(precoDeVenda)
				.append(precoDeCusto).append(estoqueAtual).append(unidadeMedida).append(quantidadeEmEstoque)
				.toHashCode();
	}

}
