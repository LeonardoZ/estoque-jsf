package com.leonardoz.estoque.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import com.leonardoz.estoque.model.UnidadeDeMedida;
import com.leonardoz.estoque.repository.UnidadesDeMedida;

@Named
@FacesConverter(forClass = UnidadeDeMedida.class)
public class UnidadeDeMedidaConverter implements Converter {

	@Inject
	private UnidadesDeMedida unidades;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		UnidadeDeMedida retorno = null;
		if (value != null && !"".equals(value)) {
			retorno = unidades.recuperaUnidadeDeMedida(Long.valueOf(value)).orElseThrow(IllegalArgumentException::new);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			UnidadeDeMedida unidade = ((UnidadeDeMedida) value);
			return unidade.getId() == null ? null : unidade.getId().toString();
		}
		return null;
	}
}
