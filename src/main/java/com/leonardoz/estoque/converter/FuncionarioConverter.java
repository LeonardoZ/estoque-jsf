package com.leonardoz.estoque.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import com.leonardoz.estoque.model.Funcionario;
import com.leonardoz.estoque.repository.Funcionarios;

@Named
@FacesConverter(forClass = Funcionario.class)
public class FuncionarioConverter implements Converter {

	@Inject
	private Funcionarios funcionarios;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Funcionario retorno = null;
		if (value != null && !"".equals(value)) {
			retorno = funcionarios.recuperaFuncionario(Long.valueOf(value))
					.orElseThrow(IllegalArgumentException::new);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Funcionario funcionario = ((Funcionario) value);
			return funcionario.getId() == null ? null : funcionario.getId().toString();
		}
		return null;
	}

}
