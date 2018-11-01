// FitFriends. October 30, 2018.

package FitFriends.dal;

import FitFriends.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonsDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static PersonsDao instance = null;
	protected PersonsDao() {
		connectionManager = new ConnectionManager();
	}
	public static PersonsDao getInstance() {
		if(instance == null) {
			instance = new PersonsDao();
		}
		return instance;
	}

	// Save the Persons instance by storing it in your MySQL instance.
	public Persons create(Persons person) throws SQLException {
		String insertPerson = "INSERT INTO Persons(UserName,FirstName,LastName,Email,Psw) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPerson, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, person.getUserName());
			insertStmt.setString(2, person.getFirstName());
			insertStmt.setString(3, person.getLastName());
			insertStmt.setString(4, person.getEmail());
			insertStmt.setString(5, person.getPsw());

			insertStmt.executeUpdate();

			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int memberId = -1;
			if(resultKey.next()) {
				memberId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			person.setMemberId(memberId);
			return person;
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

	// Update the LastName of the Persons instance.
	public Persons updateLastName(Persons person, String newLastName) throws SQLException {
		String updatePerson = "UPDATE Persons SET LastName=? WHERE MemberId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePerson);
			updateStmt.setString(1, newLastName);
			updateStmt.setInt(2, person.getMemberId());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			person.setLastName(newLastName);
			return person;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	
	// Delete the Persons instance.
	public Persons delete(Persons person) throws SQLException {
		String deletePerson = "DELETE FROM Persons WHERE MemberId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePerson);
			deleteStmt.setInt(1, person.getMemberId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
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

	
	// Get the Persons record by fetching it from your MySQL instance by its key value
	public Persons getPersonFromMemberId(int memberId) throws SQLException {
		String selectPerson = "SELECT MemberId, UserName,FirstName,LastName,Email,Psw FROM Persons WHERE MemberId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPerson);
			selectStmt.setInt(1, memberId);

			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultMemberId = results.getInt("MemberId");
				String userName = results.getString("UserName");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String psw = results.getString("Psw");
				Persons person = new Persons(resultMemberId, userName, firstName, lastName, email, psw);
				return person;
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
	
	// Get the Persons record by fetching it from your MySQL instance by its key value
	public Persons getPersonFromUserName(String userName) throws SQLException {
		String selectPerson = "SELECT MemberId, UserName,FirstName,LastName,Email,Psw FROM Persons WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPerson);
			selectStmt.setString(1, userName);

			results = selectStmt.executeQuery();
			if(results.next()) {
				int memberId = results.getInt("MemberId");
				String resultUserName = results.getString("UserName");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String psw = results.getString("Psw");
				Persons person = new Persons(memberId, resultUserName, firstName, lastName, email, psw);
				return person;
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
	public List<Persons> getPersonsFromLastName(String lastName) throws SQLException {
		List<Persons> persons = new ArrayList<Persons>();
		String selectPersons =
			"SELECT MemberId, UserName,FirstName,LastName,Email,Psw FROM Persons WHERE LastName LIKE ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPersons);
			lastName = lastName + '%';
			selectStmt.setString(1, lastName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int memberId = results.getInt("MemberId");
				String userName = results.getString("UserName");
				String firstName = results.getString("FirstName");
				String resultLastName = results.getString("LastName");
				String email = results.getString("Email");
				String psw = results.getString("Psw");
				Persons person = new Persons(memberId, userName, firstName, resultLastName, email, psw);
				persons.add(person);
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
		return persons;
	}

}
