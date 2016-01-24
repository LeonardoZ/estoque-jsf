package com.leonardoz.estoque.produto;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named
@FacesConverter(forClass = Quantidade.class)
public class QuantidadeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	Quantidade deValorFormatado = null;
	if (value != null && !value.isEmpty()) {
	    deValorFormatado = Quantidade.deValorFormatado(value);
	}
	return deValorFormatado;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	String string = ((Quantidade) value).toString();
	System.out.println("Qtd: " + string);
	return string;
    }

}
