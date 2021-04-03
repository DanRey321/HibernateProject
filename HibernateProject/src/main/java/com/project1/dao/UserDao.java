package com.project1.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
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
public class UserDao implements GenericDao <User> {
	private static final Logger LOGGER = Logger.getLogger(UserDao.class);

	private User objectConstructor(ResultSet rs) throws SQLException {
		return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7));
	}
	
	@Override
	public List<User> getList() {
		Session session = new Configuration().configure().buildSessionFactory().openSession();

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

	@Override
	public User getById(int id) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();

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
	
	@Override
	public List<User> getByUserId(int id) {
		// TODO Auto-generated method stub
		Session session = new Configuration().configure().buildSessionFactory().openSession();

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
	
	@Override
	public User getByUsername(String username) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();

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

	@Override
	public void insert(User t) {
		// TODO Auto-generated method stub
		Session session = new Configuration().configure().buildSessionFactory().openSession();

		//return null;
	}

	@Override
	public boolean delete(User t) {
		// TODO Auto-generated method stub
		return false;
	}
}
