package com.leonardoz.estoque.infraestrutura;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class ProdutorUnidadeDeTrabalho {

	private EntityManagerFactory factory;

	public ProdutorUnidadeDeTrabalho() {
		this.factory = Persistence.createEntityManagerFactory("EstoquePU");
	}

	@Produces
	@RequestScoped
	public EntityManager createHibernateSession() {
		System.out.println("Creating");
		return factory.createEntityManager();
	}

	public void closeSession(@Disposes EntityManager manager) {
		System.out.println("Clossing");
		try {
			manager.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
