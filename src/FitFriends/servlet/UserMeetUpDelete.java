package FitFriends.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import FitFriends.dal.UserMeetUpsDao;
import FitFriends.dal.UsersDao;
import FitFriends.model.UserMeetUps;
import FitFriends.model.Users;

@WebServlet("/usermeetupdelete")
public class UserMeetUpDelete extends HttpServlet {
	private UserMeetUps userMeetUp;
	protected UserMeetUpsDao userMeetUpsDao;
	
	@Override
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
     			messages.put("success", "Delete the meet up");
     			// Save the previous search term, so it can be used as the default
     			// in the input box when rendering FindUsers.jsp.
     		}
     		
     		req.getRequestDispatcher("/UserMeetUpDelete.jsp").forward(req, resp);
             
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
//        String userName = req.getParameter("username");
//        if (userName == null || userName.trim().isEmpty()) {
//            messages.put("title", "Invalid UserName");
//            messages.put("disableSubmit", "true");
//        } else {
        	// Delete the User meet up.
	        try {
	        	userMeetUp = userMeetUpsDao.delete(userMeetUp);
	        	// Update the message.
		        if (userMeetUp == null) {
		            messages.put("title", "Successfully deleted.");
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete.");
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
//        }
        
        req.getRequestDispatcher("/UserMeetUpDelete.jsp").forward(req, resp);
    }

}
