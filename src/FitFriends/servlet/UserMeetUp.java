package FitFriends.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import FitFriends.dal.MeetUpsDao;
import FitFriends.dal.UserMeetUpsDao;
import FitFriends.model.MeetUps;
import FitFriends.model.UserMeetUps;
import FitFriends.model.Workouts;

@WebServlet("/usermeetup")
public class UserMeetUp extends HttpServlet {
	protected UserMeetUpsDao userMeetUpsDao;
	
	public void init() throws ServletException {
		userMeetUpsDao = UserMeetUpsDao.getInstance();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<UserMeetUps> meetUps = new ArrayList<UserMeetUps>();
        
        // Retrieve and validate the intensity that is retrieved from the URL query string.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
        	
        	messages.put("failure", "Didn't find a username" + userName);
        } else {
        	//Retrieve all meet ups for the user, and store as a message.
        	try {
            	meetUps = userMeetUpsDao.getAllUserMeetUpsByUserName(userName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying all of the meet ups for the user." + userName);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        
        }
 
        req.setAttribute("usermeetups", meetUps);
        
        req.getRequestDispatcher("/UserMeetUp.jsp").forward(req, resp);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		List<UserMeetUps> meetUps = new ArrayList<UserMeetUps>();
	    
		// Retrieve and validate the user last name that is retrieved from the form POST submission. By default, it
		// is populated by the URL query string (in FindUsers.jsp).
		String userName = req.getParameter("username");
	    if (userName == null || userName.trim().isEmpty()) {
	       	messages.put("failure", "Didn't find a username" + userName);
	    } else {
	       	//Retrieve all meet ups for the user, and store as a message.
	       	try {
	       		meetUps = userMeetUpsDao.getAllUserMeetUpsByUserName(userName);
	        } catch (SQLException e) {
	        	e.printStackTrace();
	    		throw new IOException(e);
	        }
	        messages.put("success", "Displaying all of the meet ups for the user." + userName);
	        // Save the previous search term, so it can be used as the default
	        // in the input box when rendering FindUsers.jsp.
	        
	    }
	 
	    req.setAttribute("usermeetups", meetUps);
	        
	    req.getRequestDispatcher("/UserMeetUp.jsp").forward(req, resp);
	}
	
}
