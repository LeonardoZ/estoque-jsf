package com.leonardoz.estoque.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.leonardoz.estoque.model.values.Cep;

//@Named
//@FacesConverter(forClass = Cep.class)
public class CepConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cep cep = null;
		if (value != null && !"".equals(value)) {
			cep = new Cep(value);
		}
		return cep;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Cep cep = ((Cep) value);
			return cep != null ? cep.getValor() : "";
		}
		return null;
	}

}
