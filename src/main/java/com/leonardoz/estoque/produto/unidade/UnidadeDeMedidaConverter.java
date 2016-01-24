package com.leonardoz.estoque.produto.unidade;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@FacesConverter(forClass = UnidadeDeMedida.class, value = "unidadeDeMedidaConverter")
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
