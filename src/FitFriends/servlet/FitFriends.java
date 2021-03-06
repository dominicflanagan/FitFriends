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
 * FitFriends is the primary entry point into the application.
 */
@WebServlet("/fitfriends")
public class FitFriends extends HttpServlet {
	
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
        
        // Retrieve and validate the username that is retrieved from the URL query string.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve Users, and store as a message.
        	try {
            	Users user = usersDao.getUserFromUserName(userName);
            	users.add(user);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + userName);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FitFriends.jsp.
        	messages.put("previousUsertName", userName);
        }
 
        req.setAttribute("users", users);
        
        req.getRequestDispatcher("/FitFriends.jsp").forward(req, resp);
	}

@Override
public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    List<Users> users = new ArrayList<Users>();
    
    // Retrieve and validate the username that is retrieved from the form POST submission. By default, it
    // is populated by the URL query string (in FitFriends.jsp).
    String userName = req.getParameter("username");
    if (userName == null || userName.trim().isEmpty()) {
        messages.put("success", "Please enter a valid username.");
    } else {
    	// Retrieve Users, and store as a message.
    	try {
        	Users user = usersDao.getUserFromUserName(userName);
        	users.add(user);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
    	messages.put("success", "Displaying results for " + userName);
    }
    req.setAttribute("users", users);
    
    req.getRequestDispatcher("/FitFriends.jsp").forward(req, resp);
}
}

