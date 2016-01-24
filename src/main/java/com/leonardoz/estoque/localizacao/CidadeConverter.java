package com.leonardoz.estoque.localizacao;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@FacesConverter(forClass = Cidade.class)
public class CidadeConverter implements Converter {

    @Inject
    private Localizacoes localizacoes;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	Cidade retorno = null;
	if (value != null && !"".equals(value)) {
	    retorno = localizacoes.recuperaCidade(Long.valueOf(value));
	}
	return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value != null) {
	    Cidade cidade = ((Cidade) value);
	    return cidade.getId() == null ? null : cidade.getId().toString();
	}
	return null;
    }
}
