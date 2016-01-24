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
    private final static QuantidadeFracionada CEM = new QuantidadeFracionada(100);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	Porcentagem deValorFormatado = null;
	if (value != null && !value.isEmpty()) {
	    System.err.println("Vl pctg " + value);
	    deValorFormatado = Porcentagem.de(Double.valueOf(value.replace(".", "").replaceAll(",", ".")));
	    System.err.println("Vl pctg form " + deValorFormatado);
	}
	return deValorFormatado;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	Porcentagem porcentagem = (Porcentagem) value;
	System.out.println("Pctg1 " + porcentagem.getTaxaPercentual());
	System.out.println("Pctg " + porcentagem.toString());
	return porcentagem.getTaxaPercentual().multiplicar(CEM).toString();
    }

}
