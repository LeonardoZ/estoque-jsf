package com.leonardoz.estoque.produto;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@FacesConverter(forClass = Produto.class, value = "produtoConverter")
public class ProdutoConverter implements Converter {

    @Inject
    private Produtos produtos;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	Produto retorno = null;
	System.out.println("O valor é " + value);
	if (value != null && !"".equals(value)) {
	    retorno = produtos.recuperarProduto(Long.valueOf(value)).orElseThrow(IllegalArgumentException::new);
	    System.out.println("Voltou " + retorno.getDescricao());
	}
	return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value != null) {
	    Produto produto = ((Produto) value);
	    return produto.getId() == null ? null : produto.getId().toString();
	}
	return null;
    }
}
