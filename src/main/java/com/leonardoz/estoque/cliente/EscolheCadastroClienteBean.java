package com.leonardoz.estoque.cliente;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class EscolheCadastroClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private TipoPessoa tipo;
    private boolean cadastroClienteJuridicoSelecionado;
    private boolean cadastroClienteFisicoSelecionado;

    public void trocaPainelSelecionado() {
	cadastroClienteFisicoSelecionado = tipo == TipoPessoa.FISICA;
	cadastroClienteJuridicoSelecionado = tipo == TipoPessoa.JURIDICA;
    }

    public boolean isCadastroClienteJuridicoSelecionado() {
	return cadastroClienteJuridicoSelecionado;
    }

    public void setCadastroClienteJuridicoSelecionado(boolean cadastroClienteJuridicoSelecionado) {
	this.cadastroClienteJuridicoSelecionado = cadastroClienteJuridicoSelecionado;
    }

    public boolean isCadastroClienteFisicoSelecionado() {
	return cadastroClienteFisicoSelecionado;
    }

    public void setCadastroClienteFisicoSelecionado(boolean cadastroClienteFisicoSelecionado) {
	this.cadastroClienteFisicoSelecionado = cadastroClienteFisicoSelecionado;
    }

    public TipoPessoa getTipo() {
	return tipo;
    }

    public void setTipo(TipoPessoa tipo) {
	this.tipo = tipo;
    }

    public List<TipoPessoa> listarTipos() {
	return TipoPessoa.listar();
    }

}
