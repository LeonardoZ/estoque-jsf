package com.leonardoz.estoque.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

@ApplicationScoped
public class ProdutorUnidadeDeTrabalho {

	private EntityManagerFactory factory;

	public ProdutorUnidadeDeTrabalho() {
		this.factory = Persistence.createEntityManagerFactory("EstoquePU");
	}

	@Produces
	@RequestScoped
	public Session createHibernateSession() {
		System.out.println("Creating");
		return factory.createEntityManager().unwrap(Session.class);
	}

	public void closeSession(@Disposes Session session) {
		System.out.println("Clossing");
		try {
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
