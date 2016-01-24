package com.leonardoz.estoque.infraestrutura;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.leonardoz.estoque.modelo.valor.DiaDaSemana;

@FacesConverter(forClass = DiaDaSemana.class)
public class DiaDaSemanaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	DiaDaSemana dia = null;
	if (value != null && !"".equals(value)) {
	    dia = DiaDaSemana.recuperaPorValor(value);

	}
	return dia;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value != null) {
	    DiaDaSemana dia = ((DiaDaSemana) value);
	    return dia != null ? dia.getValor() : "";
	}
	return null;
    }

}
