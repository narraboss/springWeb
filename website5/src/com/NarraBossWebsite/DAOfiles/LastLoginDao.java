 package com.NarraBossWebsite.DAOfiles;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.NarraBossWebsite.EMFService;
import com.NarraBossWebsite.ItemFiles.LastLogin;
@SuppressWarnings("unchecked")
public enum LastLoginDao {
	INSTANCE;

	
	public List<LastLogin> lastLogin() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from LastLogin m");
		List<LastLogin> logins = q.getResultList();
		return logins;
	}

	public void add(String customerId, Date date) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			LastLogin log = new LastLogin(customerId, date);
			em.persist(log);
			em.close();
		}
	}

	public List<LastLogin> getlogins(String customerId) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from LastLogin t where t.customerId = :customerId");
		q.setParameter("customerId", customerId);
		List<LastLogin> logs = q.getResultList();
		return logs;
	}

	public void updateLogin(String customerId, Date date) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Query q = em.createQuery("select t from LastLogin t where t.customerId = :customerId");
			q.setParameter("customerId", customerId);
			List<LastLogin> logs = q.getResultList();
			for (LastLogin acc : logs) {
				if ((acc.getCustomerId()).equals(customerId)) {

					acc.setDate(date);
					;
					em.persist(acc);
					em.close();
				}
			}
		}
	}

	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			LastLogin log = em.find(LastLogin.class, id);
			em.remove(log);
		} finally {
			em.close();
		}
	}
}
