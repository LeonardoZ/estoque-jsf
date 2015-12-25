package com.leonardoz.estoque.pessoa.fornecedor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@FacesConverter(forClass = Fornecedor.class)
public class FornecedorConverter implements Converter {

	@Inject
	private Fornecedores fornecedores;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Fornecedor retorno = null;
		if (value != null && !"".equals(value)) {
			retorno = fornecedores.recuperaFornecedor(Long.valueOf(value))
					.orElseThrow(IllegalArgumentException::new);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Fornecedor fornecedor = ((Fornecedor) value);
			return fornecedor.getId() == null ? null : fornecedor.getId().toString();
		}
		return null;
	}

}
