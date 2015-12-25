package com.leonardoz.estoque.localizacao;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@FacesConverter(forClass = Estado.class)
public class EstadoConverter implements Converter {

	@Inject
	private Localizacoes localizacoes;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Estado retorno = null;
		if (value != null && !"".equals(value)) {
			retorno = localizacoes.recuperaEstado(Long.valueOf(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Estado estado = ((Estado) value);
			return estado.getId() == null ? null : estado.getId().toString();
		}
		return null;
	}
}
