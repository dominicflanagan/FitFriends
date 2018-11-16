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

import FitFriends.dal.UserMeetUpsDao;
import FitFriends.dal.UsersDao;
import FitFriends.model.Exercise;
import FitFriends.model.UserMeetUps;
import FitFriends.model.Users;

@WebServlet("/usermeetupjoin")
public class UserMeetUpJoin  extends HttpServlet {
	private UserMeetUps userMeetUp;
	protected UserMeetUpsDao userMeetUpsDao;
	
	public void init() throws ServletException {
		userMeetUpsDao = UserMeetUpsDao.getInstance();
		userMeetUp = new UserMeetUps();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
        
        // Retrieve and validate the intensity that is retrieved from the URL query string.
		String meetUpId = req.getParameter("meetupid");
		String memberId = req.getParameter("memberid");
		String meetUpTime = req.getParameter("meetuptime");
		String username = req.getParameter("username");
		if (meetUpId == null || meetUpId.trim().isEmpty() || memberId == null || memberId.trim().isEmpty() || meetUpTime == null || meetUpTime.trim().isEmpty()) {
			messages.put("title", "Invalid User Meet Up");
			messages.put("disableSubmit", "true");
		} else {
			//Retrieve all exercises for the meet up, and store as a message.
			try {
				userMeetUp = userMeetUpsDao.getUserMeetUpByProperties(meetUpId, memberId, meetUpTime);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "User " + username + " to join MeetUp Id:" + meetUpId);
			// Save the previous search term, so it can be used as the default
			// in the input box when rendering FindUsers.jsp.
        
       }
 
        req.setAttribute("usermeetup", userMeetUp);
        
        req.getRequestDispatcher("/UserMeetUpJoin.jsp").forward(req, resp);
	}


	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        UsersDao usersDao = UsersDao.getInstance();
        Users user = new Users(0);
		try {
			user = usersDao.getUserById(userMeetUp.getMemberId());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        // Retrieve and validate the data.
        String username = req.getParameter("username");
        if (username == null || username.trim().isEmpty()) {
            messages.put("title", "Invalid username");
            messages.put("disableSubmit", "true");
        } else {
        	// update the User meet up.
	       
	        try {
	        	// get member id for the user who wants to join, add the username to the other meet-up fields
	        	// and create a new meet-up row for the user who wanted to join
	        	user = usersDao.getUserFromUserName(username);
	        	userMeetUp.setMemberId(user.getMemberId());
	        	userMeetUp = userMeetUpsDao.create(userMeetUp);	        
	        	// Update the message.
		        if (userMeetUp == null) {
		            messages.put("title", "Failed to Join.");
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", username + " successfully joined Meet-Up");
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.setAttribute("usermeetup", userMeetUp);
        
        req.getRequestDispatcher("/UserMeetUpJoin.jsp").forward(req, resp);
    }
}
