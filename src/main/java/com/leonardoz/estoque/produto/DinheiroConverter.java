package com.leonardoz.estoque.produto;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named
@FacesConverter(forClass = Dinheiro.class)
public class DinheiroConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	Dinheiro deValorFormatado = null;
	if (value != null && !value.isEmpty()) {
	    deValorFormatado = Dinheiro.deValorFormatado(value);
	}
	return deValorFormatado;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	return ((Dinheiro) value).valorFormatadoSimples();
    }

}
