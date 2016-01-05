package com.leonardoz.estoque.infraestrutura;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.leonardoz.estoque.modelo.entidade.Entidade;
import com.leonardoz.estoque.modelo.entidade.Filtro;

public abstract class NovoDataModel<T extends Entidade> extends LazyDataModel<T> {

	private static final long serialVersionUID = 1L;
	private Filtro filtro;

	public NovoDataModel(Filtro filtro) {
		this.filtro = filtro;
	}

	private void configuraFiltroBasico(int first, int pageSize, String sortField, SortOrder sortOrder) {
		filtro.setPrimeiroRegistro(first);
		filtro.setQuantidadeRegistros(pageSize);
		filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
		filtro.setPropriedadeOrdenacao(sortField);
	}

	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		configuraFiltroBasico(first, pageSize, sortField, sortOrder);
		return configuraFiltradosEContagem(filtro);
	}

	public abstract <U extends Filtro> List<T> configuraFiltradosEContagem(U filtro);

}
