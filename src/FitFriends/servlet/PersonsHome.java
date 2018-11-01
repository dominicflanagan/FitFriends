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


@WebServlet("/personshome")
public class PersonsHome extends HttpServlet {
	
	protected PersonsDao personsDao;
	
	@Override
	public void init() throws ServletException {
		personsDao = PersonsDao.getInstance();
	}
	
@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Persons> persons = new ArrayList<Persons>();
        
        // Retrieve and validate the person last name that is retrieved from the URL query string.
        String lastName = req.getParameter("lastname");
        if (lastName == null || lastName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid person last name.");
        } else {
        	// Retrieve Persons, and store as a message.
        	try {
            	persons = personsDao.getPersonsFromLastName(lastName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for people with last name that starts with: " + lastName);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering PersonsHome.jsp.
        	messages.put("previousLastName", lastName);
        }
 
        req.setAttribute("persons", persons);
        
        req.getRequestDispatcher("/PersonsHome.jsp").forward(req, resp);
	}

@Override
public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    List<Persons> persons = new ArrayList<Persons>();
    
    // Retrieve and validate the person last name that is retrieved from the form POST submission. By default, it
    // is populated by the URL query string (in PersonsHome.jsp).
    String lastName = req.getParameter("lastname");
    if (lastName == null || lastName.trim().isEmpty()) {
        messages.put("success", "Please enter a valid person last name.");
    } else {
    	// Retrieve Persons, and store as a message.
    	try {
        	persons = personsDao.getPersonsFromLastName(lastName);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
    	messages.put("success", "Displaying results for people with last name that starts with: " + lastName);
    }
    req.setAttribute("persons", persons);
    
    req.getRequestDispatcher("/PersonsHome.jsp").forward(req, resp);
}
}

