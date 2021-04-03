package com.project1.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.project1.dao.UserDaoHibernate;
import com.project1.model.User;

public class UserService {
	private UserDaoHibernate ud;
	//private UserDaoH uh;
	private static final Logger LOGGER = Logger.getLogger(UserService.class);
	
	public UserService() {
		ud = new UserDaoHibernate();
		//uh = new UserDaoH();
	}
	
	public List<User> fetchAllUsers() {
		return ud.getList();
	}
	//public List<User> fetchAllUsersH() {
		//return uh.getAll();
	//}
	
	public User getUserById(int id) {
		return ud.getById(id);
	}
	
	public User getUserByUsername(String username) {
		User u = ud.getByUsername(username);
		if (u != null) {
			u.setPassword(""); //Remove the hashed password for security reasons.
			LOGGER.trace("Password info removed from username " + username + ".");
			return u;
		}
		return null;
	}
	
	public User getUserByLogin(String user, String pass) {
		User u = ud.getByUsername(user);
		
		if(u != null) {
		String full = user + pass + "salt";
			try {
				//Let MessageDigest know that we want to hash using MD5
				MessageDigest m = MessageDigest.getInstance("md5");
				//Convert our full string to a byte array.
				byte[] messageDigest = m.digest(full.getBytes());
				//Convert our byte array into a signum representation of its former self.
				BigInteger n = new BigInteger(1, messageDigest);
				
				//Convert the whole array into a hexadecimal string.
				String hash = n.toString(16);
				while(hash.length() < 32) {
					hash = "0" + hash;
				}
				
				if(u.getPassword().equals(hash)) {
					//System.out.println("Hash matched!");
					return u;
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	public User insertUser(User user)throws SQLException{
		int id;
		try {
			id = Integer.parseInt(ud.insert(user).toString());
			return ud.getById(id);

		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	public boolean deleteUser(User user) throws SQLException{
		ud.delete(user);
		return ud.getById(user.getUserid()) != null;
	}
}
