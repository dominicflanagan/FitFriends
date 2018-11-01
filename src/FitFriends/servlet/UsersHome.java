// FitFriends. October 30, 2018.

package FitFriends.servlet;

import FitFriends.dal.*;
import FitFriends.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * FindUsers is the primary entry point into the application.
 */
@WebServlet("/usershome")
public class UsersHome extends HttpServlet {
	
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}
	
@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Users> users = new ArrayList<Users>();
        
        // Retrieve and validate the user last name that is retrieved from the URL query string.
        String lastName = req.getParameter("lastname");
        if (lastName == null || lastName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid user last name.");
        } else {
        	// Retrieve Users, and store as a message.
        	try {
            	users = usersDao.getUsersFromLastName(lastName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for users with last name that starts with: " + lastName);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousLastName", lastName);
        }
 
        req.setAttribute("users", users);
        
        req.getRequestDispatcher("/UsersHome.jsp").forward(req, resp);
	}

@Override
public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    List<Users> users = new ArrayList<Users>();
    
    // Retrieve and validate the user last name that is retrieved from the form POST submission. By default, it
    // is populated by the URL query string (in FindUsers.jsp).
    String lastName = req.getParameter("lastname");
    if (lastName == null || lastName.trim().isEmpty()) {
        messages.put("success", "Please enter a valid user last name.");
    } else {
    	// Retrieve Users, and store as a message.
    	try {
        	users = usersDao.getUsersFromLastName(lastName);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
    	messages.put("success", "Displaying results for users with last name that starts with: " + lastName);
    }
    req.setAttribute("users", users);
    
    req.getRequestDispatcher("/UsersHome.jsp").forward(req, resp);
}
}

