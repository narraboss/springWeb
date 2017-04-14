package com.NarraBossWebsite.DAOfiles;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.NarraBossWebsite.EMFService;
import com.NarraBossWebsite.ItemFiles.User;


public enum UserDao {
	  INSTANCE;

	@SuppressWarnings("unchecked")
	public List<User> listCustomers() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select U from User U");
		List<User> customers = q.getResultList();
		return customers;
	}

	public void add(String customerId,String password,String sq, String ans) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			User customer = new User(customerId,password,sq,ans);
			em.persist(customer);
			em.close();
		}
	}
	
	public void updateSQ(Long id, String Sq, String ans ){
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			User user = em.find(User.class, id);
			user.setQuestion(Sq);
			user.setAnswer(ans);
			em.persist(user);
			em.close();
		}
	}
	
	
	public void changePassword(Long id,String password) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			User user = em.find(User.class, id);
			user.setPassword(password);
			em.persist(user);
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getCustomer(String customerId) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from User t where t.customerId = :customerId");
		q.setParameter("customerId", customerId);
		List<User> customer = q.getResultList();
		return customer;
	}

	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			User customer = em.find(User.class, id);
			
			
			em.remove(customer);
		} finally {
			em.close();
		}
	}

	
}


