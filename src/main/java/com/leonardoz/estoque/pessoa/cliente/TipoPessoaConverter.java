package com.leonardoz.estoque.pessoa.cliente;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

public class TipoPessoaConverter extends EnumConverter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TipoPessoa pessoa = null;
		if (value != null && !value.isEmpty()) {
			pessoa = TipoPessoa.recuperaPorValor(value);
		}
		return pessoa;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			TipoPessoa pessoa = ((TipoPessoa) value);
			return pessoa != null ? pessoa.getPessoa() : "";
		}
		return null;
	}

}
