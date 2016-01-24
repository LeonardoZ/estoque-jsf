package com.leonardoz.estoque.cliente;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = TipoPessoa.class)
public class TipoPessoaConverter implements Converter {

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
