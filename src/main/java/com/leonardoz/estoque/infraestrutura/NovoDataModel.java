package com.leonardoz.estoque.infraestrutura;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import com.leonardoz.estoque.modelo.entidade.Entidade;

public class NovoDataModel<T extends Entidade> extends LazyDataModel<T> {

	private static final long serialVersionUID = 1L;
	
	@Override
	public List<T> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
		return super.load(first, pageSize, multiSortMeta, filters);
	}

}
