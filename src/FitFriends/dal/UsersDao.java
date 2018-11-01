// FitFriends. October 30, 2018.

package FitFriends.dal;

import FitFriends.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class UsersDao extends PersonsDao {
	// Single pattern: instantiation is limited to one object.
	private static UsersDao instance = null;
	protected UsersDao() {
		super();
	}
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}

	// Save the Users instance by storing it in MySQL instance.
	public Users create(Users user) throws SQLException {
		// Insert into the superclass table first and get the new RestaurantId
		PersonsDao personsDao = PersonsDao.getInstance(); 
		Persons person1 = new Persons(user.getUserName(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPsw());
		person1 = personsDao.create(person1);
		
		// get the member id that was generated for the Person superclass
		user.setMemberId(person1.getMemberId());
		
		
		String insertUser = "INSERT INTO Users(MemberId,DoB,FitnessLevel) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			insertStmt.setInt(1, user.getMemberId());
			insertStmt.setTimestamp(2, new Timestamp(user.getDob().getTime()));
			insertStmt.setString(3, user.getFitnessLevel().name());

			insertStmt.executeUpdate();
			
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	// Delete User
	public Users delete(Users user) throws SQLException {
		String deleteUser = "DELETE FROM Users WHERE MemberId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setInt(1, user.getMemberId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for MemberId=" + user.getMemberId());
			}

			// Delete from the superclass 
			super.delete(user);

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
	
	// Get a user by the user member id
	public Users getUserById(int userId) throws SQLException {
		String selectUser =
			"SELECT Users.MemberId,UserName,FirstName,LastName,Email,Psw,DoB,FitnessLevel " +
			"FROM Users INNER JOIN Persons ON Users.MemberId = Persons.MemberId " +
			"WHERE Users.MemberId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultUserId = results.getInt("MemberId");
				String userName = results.getString("UserName");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String psw = results.getString("Psw");
				Date dob =  new Date(results.getTimestamp("DoB").getTime());
				Users.FitnessLevel fitnessLevel = Users.FitnessLevel.valueOf(results.getString("FitnessLevel"));
								
				Users user = new Users(resultUserId,userName,firstName,lastName,email,psw,dob,fitnessLevel);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	// Get the users record by fetching it from your MySQL instance by its user name, which is a unique value
	public Users getUserFromUserName(String userName) throws SQLException {
		String selectUser =
				"SELECT Users.MemberId,UserName,FirstName,LastName,Email,Psw,DoB,FitnessLevel " +
				"FROM Users INNER JOIN Persons ON Users.MemberId = Persons.MemberId " +
				"WHERE Persons.UserName=?;";		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, userName);

			results = selectStmt.executeQuery();
			if(results.next()) {
				int userId = results.getInt("MemberId");
				String resultUserName = results.getString("UserName");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String psw = results.getString("Psw");
				Date dob =  new Date(results.getTimestamp("DoB").getTime());
				Users.FitnessLevel fitnessLevel = Users.FitnessLevel.valueOf(results.getString("FitnessLevel"));
								
				Users user = new Users(userId,resultUserName,firstName,lastName,email,psw,dob,fitnessLevel);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

	// Get the matching Persons records by fetching from your MySQL instance list of Persons by LastName
	public List<Users> getUsersFromLastName(String lastName) throws SQLException {
		List<Users> users = new ArrayList<Users>();
		String selectUsers =
				"SELECT Users.MemberId,UserName,FirstName,LastName,Email,Psw,DoB,FitnessLevel " +
				"FROM Users INNER JOIN Persons ON Users.MemberId = Persons.MemberId " +
				"WHERE LastName LIKE ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUsers);
			lastName = lastName + '%';
			selectStmt.setString(1, lastName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int userId = results.getInt("MemberId");
				String userName = results.getString("UserName");
				String firstName = results.getString("FirstName");
				String resultLastName = results.getString("LastName");
				String email = results.getString("Email");
				String psw = results.getString("Psw");
				Date dob =  new Date(results.getTimestamp("DoB").getTime());
				Users.FitnessLevel fitnessLevel = Users.FitnessLevel.valueOf(results.getString("FitnessLevel"));
								
				Users user = new Users(userId,userName,firstName,resultLastName,email,psw,dob,fitnessLevel);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return users;
	}
}
