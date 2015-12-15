package com.leonardoz.estoque.util;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.hibernate.Session;
import org.hibernate.Transaction;

@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private @Inject Session session;

	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		
		Transaction trx = session.getTransaction();
		boolean criador = false;
		try {
			// Algaworks JavaEE 7
			if (!trx.isActive()) {
				trx.begin();
				criador = true;
			}
			return context.proceed();
		} catch (Exception e) {
			if (trx != null && criador) {
				trx.rollback();
			}
			e.printStackTrace();
			throw e;
		} finally {
			if (trx != null && trx.isActive() && criador) {
				trx.commit();
			}
		}
	}

}
