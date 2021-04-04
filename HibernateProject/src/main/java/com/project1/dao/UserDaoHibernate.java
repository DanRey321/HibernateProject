package com.project1.dao;

import java.io.Serializable;
import java.util.List;

import com.project1.util.HibernateUtility;
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
		try(Session session = HibernateUtility.INSTANCE.getSessionFactoryInstance().openSession()){
			List<User> result = session.createQuery("from User",User.class).getResultList();
			if(result.isEmpty()){
				LOGGER.error("An attempt to get all users from the database was made, but it came empty.");
				return null;
			}else{
				LOGGER.debug("A list of users was retrieved from the database.");
				return result;
			}
		}

	}

	@Override
	public User getById(int id) {
		try(Session session = HibernateUtility.INSTANCE.getSessionFactoryInstance().openSession()){
			User result = session.get(User.class,id);
			if(result==null){
				LOGGER.error("An attempt to get info about user ID " + id + " from the database was made, but it came empty.");
				return null;
			}else{
				LOGGER.debug("Information about user ID " + id + " was retrieved from the database.");
				return result;
			}
		}
	}
	
	@Override
	public List<User> getByUserId(int id) {
		try(Session session = HibernateUtility.INSTANCE.getSessionFactoryInstance().openSession()){
			List<User> result = session.createQuery("FROM User WHERE userid=:userid",User.class).setParameter("userid",id).getResultList();

			if(result.isEmpty()){
				LOGGER.error("An attempt to get info about user ID " + id + " from the database was made, but it came empty.");
				return null;
			}else{
				LOGGER.debug("Information about user ID " + id + " was retrieved from the database.");
				return result;
			}
		}
	}
	
	@Override
	public User getByUsername(String username) {
		try(Session session = HibernateUtility.INSTANCE.getSessionFactoryInstance().openSession()){
			List<User> result = session.createQuery("FROM User WHERE username=:username", User.class).setParameter("username", username).getResultList();
			if(result.isEmpty()){
				LOGGER.error("An attempt to get info about username " + username + " from the database was made, but it came empty.");
				return null;
			}else{
				LOGGER.debug("Information about username " + username + " was retrieved from the database.");
				return result.get(0);
			}
		}
	}

	@Override
	public Serializable insert(User t) {
		try(Session session = HibernateUtility.INSTANCE.getSessionFactoryInstance().openSession()){
			session.beginTransaction();
			Serializable result = session.save(t);
			session.flush();
			session.getTransaction().commit();
			if(result!=null) {
				LOGGER.debug("A new user was successfully added to the database.");
				return result;
			}else{
				LOGGER.error("An attempt to insert a user to the database failed.");
				return null;
			}
		}
	}

	@Override
	public void delete(User t) {
		try(Session session = HibernateUtility.INSTANCE.getSessionFactoryInstance().openSession()){
			session.beginTransaction();
			session.delete(t);
			session.getTransaction().commit();
			LOGGER.error("An attempt to delete a user from the database was made.");
		}
	}
}
