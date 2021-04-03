package com.project1.dao;

import java.io.Serializable;
import java.util.List;
import org.apache.log4j.Logger;
import com.project1.model.User;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


/*
 * Purpose of this Dao is to send/retrieve info about a reimbursement
 * to/from the database. It then returns the composed Reimbursement Object.
 */
public class UserDaoHibernate implements GenericDao <User> {
	private static final Logger LOGGER = Logger.getLogger(UserDaoHibernate.class);
	
	@Override
	public List<User> getList() {
		try(Session session = new Configuration().configure().buildSessionFactory().openSession()){
			List<User> result = session.createQuery("from User").list();
			if(result.isEmpty()){
				LOGGER.error("An attempt to get all users from the database failed.");
				//TODO:Should I be returning null?
				return null;
			}else{
				LOGGER.debug("A list of users was retrieved from the database.");
				return result;
			}
		}

	}

	@Override
	public User getById(int id) {
		try(Session session = new Configuration().configure().buildSessionFactory().openSession()){
			Query<User> query = session.createNamedQuery("getUserById", User.class).setParameter("id", id);
			List<User> result = query.getResultList();

			if(result.isEmpty()){
				LOGGER.error("An attempt to get info about user ID " + id + " from the database failed.");
				return null;
			}else{
				LOGGER.debug("Information about user ID " + id + " was retrieved from the database.");
				return result.get(0);
			}
		}


	}
	
	@Override
	public List<User> getByUserId(int id) {
		try(Session session = new Configuration().configure().buildSessionFactory().openSession()){
			Query<User> query = session.createNamedQuery("getUserById", User.class).setParameter("id", id);
			List<User> result = query.getResultList();

			if(result.isEmpty()){
				LOGGER.error("An attempt to get info about user ID " + id + " from the database failed.");
				return null;
			}else{
				LOGGER.debug("Information about user ID " + id + " was retrieved from the database.");
				return result;
			}
		}
	}
	
	@Override
	public User getByUsername(String username) {
		try(Session session = new Configuration().configure().buildSessionFactory().openSession()){
			Query<User> query = session.createNamedQuery("getByUsername", User.class).setParameter("username", username);
			List<User> result = query.getResultList();

			if(result.isEmpty()){
				LOGGER.error("An attempt to get info about username " + username + " from the database failed.");
				return null;
			}else{
				LOGGER.debug("Information about username " + username + " was retrieved from the database.");
				return result.get(0);
			}
		}
	}

	@Override
	public Serializable insert(User t) {
		try(Session session = new Configuration().configure().buildSessionFactory().openSession()){
			session.beginTransaction();
			Serializable result = session.save(t);
			session.flush();
			session.getTransaction().commit();

			return result;
		}
	}

	@Override
	public void delete(User t) {
		try(Session session = new Configuration().configure().buildSessionFactory().openSession()){
			session.beginTransaction();
			session.delete(t);
			session.getTransaction().commit();
		}
	}
}
