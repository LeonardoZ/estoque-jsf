package com.leonardoz.estoque.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CriaTabela {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstoquePU");
		EntityManager em = emf.createEntityManager();
		em.close();
		emf.close();
	}

}
