package com.leonardoz.estoque.compra;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@FacesConverter(forClass = Compra.class, value = "compraConverter")
public class CompraConverter implements Converter {

	@Inject
	private Compras compras;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Compra retorno = null;
		if (value != null && !"".equals(value)) {
			retorno = compras.recuperarCompra(Long.valueOf(value)).orElseThrow(IllegalArgumentException::new);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Compra compra = ((Compra) value);
			return compra.getId() == null ? null : compra.getId().toString();
		}
		return null;
	}
}
