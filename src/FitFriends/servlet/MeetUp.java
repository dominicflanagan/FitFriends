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
import FitFriends.dal.WorkoutsDao;
import FitFriends.model.MeetUps;
import FitFriends.model.Workouts;

@WebServlet("/meetup")
public class MeetUp extends HttpServlet {
	protected MeetUpsDao meetupsDao;
	
	public void init() throws ServletException {
		meetupsDao = MeetUpsDao.getInstance();
	}
	

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<MeetUps> meetups = new ArrayList<MeetUps>();
        
        // Retrieve and validate the intensity that is retrieved from the URL query string.
        String meetupId = req.getParameter("meetupid");
        if (meetupId == null || meetupId.trim().isEmpty()) {
        	try {
            	meetups = meetupsDao.getAllMeetUps();
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying all of the meet ups.");
        } else {
        	//Retrieve all meetups, and store as a message.
        	try {
            	meetups = meetupsDao.getAllMeetUps();
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying all of the workouts.");
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        
        }
 
        req.setAttribute("meetups", meetups);
        
        req.getRequestDispatcher("/MeetUp.jsp").forward(req, resp);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		List<MeetUps> meetups = new ArrayList<MeetUps>();
    
		// Retrieve and validate the user last name that is retrieved from the form POST submission. By default, it
		// is populated by the URL query string (in FindUsers.jsp).
		String district = req.getParameter("district");
		if (district == null || district.trim().isEmpty()) {
			messages.put("success", "Please enter a valid intensity value.");
		} else {
			// Retrieve meetups, and store as a message.
			try {
				meetups = meetupsDao.getAllMeetUpsByDistrict(district);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for meetups in the district: " + district);
		}
		req.setAttribute("meetups", meetups);
    
		req.getRequestDispatcher("/MeetUp.jsp").forward(req, resp);
	}

	

}
