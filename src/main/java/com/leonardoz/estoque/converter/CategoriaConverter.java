package com.leonardoz.estoque.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.leonardoz.estoque.model.Categoria;
import com.leonardoz.estoque.repository.Categorias;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

	@Inject
	private Categorias categorias;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Categoria retorno = null;
		if (value != null && !"".equals(value)) {
			retorno = categorias.recuperaCategoria(Long.valueOf(value))
					.orElseThrow(IllegalArgumentException::new);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Categoria categoria = ((Categoria) value);
			return categoria.getId() == null ? null : categoria.getId().toString();
		}
		return null;
	}
}
