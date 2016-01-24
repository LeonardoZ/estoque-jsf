package com.leonardoz.estoque.produto;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named
@FacesConverter(forClass = QuantidadeFracionada.class)
public class QuantidadeFracionadaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	QuantidadeFracionada deValorFormatado = null;
	if (value != null && !value.isEmpty()) {
	    deValorFormatado = QuantidadeFracionada.deValorFormatado(value);
	}
	return deValorFormatado;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	String string = ((QuantidadeFracionada) value).toString();
	return string;
    }

}
