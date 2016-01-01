package com.leonardoz.estoque.infraestrutura.jpa;

import java.util.function.BiFunction;

import org.hibernate.Criteria;

import com.leonardoz.estoque.modelo.entidade.Filtro;

/**
 * Tempera a criteria recebida com os filtros específicos de cada tipo. Deve
 * devolver a criteria recebida com os novos filtros.
 * 
 **/
public interface EspecificaCriteria<T extends Filtro> {

	BiFunction<T, Criteria, Criteria> especificadorDeCriteria();
}
