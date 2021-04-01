package com.project1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.project1.model.User;
import com.project1.util.ConnectionUtil;

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
		List<User> l = new ArrayList<User>();
		
		try (Connection conn = ConnectionUtil.getConnection()) {

			String qSql = "SELECT * FROM ers_users";
			PreparedStatement s = conn.prepareStatement(qSql);
			//Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery();
			
			while(rs.next()) {
				l.add(objectConstructor(rs));
			}
			LOGGER.debug("A list of users was retrieved from the database.");
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("An attempt to get all users from the database failed.");
		}
		return l;
	}

	@Override
	public User getById(int id) {
		User u = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			String qSql = "SELECT * FROM ers_users WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(qSql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				u = objectConstructor(rs);
			
			LOGGER.debug("Information about user ID " + id + " was retrieved from the database.");
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("An attempt to get info about user ID " + id + " from the database failed.");
		}
		return u;
	}
	
	@Override
	public List<User> getByUserId(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public User getByUsername(String username) {
		User u = null;
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			String qSql = "SELECT * FROM ers_users WHERE username = ?";
			PreparedStatement ps = conn.prepareStatement(qSql);
			ps.setString(1, username.toLowerCase());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				//System.out.println("User object was created!");
				u = objectConstructor(rs);
			}
			
			LOGGER.debug("Information about username " + username + " was retrieved from the database.");
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("An attempt to get info about username " + username + " from the database failed.");
		}
		return u;
	}

	@Override
	public User insert(User t) {
		// TODO Auto-generated method stub
		//return null;
		String sql = "insert into ers_users values (?, ?, ?, ?, ?, ?, ?)";
		try(Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getUser_id());
			pstmt.setString(2, t.getUsername());
			pstmt.setString(3, t.getPassword());
			pstmt.setString(4, t.getFirstname());
			pstmt.setString(5, t.getLastname());
			pstmt.setString(6, t.getEmail());
			pstmt.setInt(7, t.getRole_id());

			if (pstmt.executeUpdate() != 1) {
				throw new SQLException("Inserting Employee failed, no rows were affected");
			}

			//return t;
			//connection.commit();


		}catch(SQLException e){
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public boolean delete(User t) {
		// TODO Auto-generated method stub
		String sql = "delete from ers_users where username = ?";
		try(Connection connection = ConnectionUtil.getConnection()){
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, t.getUsername());

			if(statement.executeUpdate()< 1){
				throw new SQLException("Deleting Failed, no row affected");
			}

		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
