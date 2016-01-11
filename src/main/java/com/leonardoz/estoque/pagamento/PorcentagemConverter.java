package com.leonardoz.estoque.pagamento;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import com.leonardoz.estoque.produto.Porcentagem;
import com.leonardoz.estoque.produto.QuantidadeFracionada;

@Named
@FacesConverter(forClass = Porcentagem.class)
public class PorcentagemConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Porcentagem deValorFormatado = null;
		if (value != null && !value.isEmpty()) {
			deValorFormatado = Porcentagem.de(Double.valueOf(value));
		}
		return deValorFormatado;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((Porcentagem) value).getTaxaPercentual().multiplicar(new QuantidadeFracionada(100)).toString();
	}

}
