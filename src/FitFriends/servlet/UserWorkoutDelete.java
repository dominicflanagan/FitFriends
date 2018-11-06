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

import FitFriends.dal.UserWorkoutsDao;
import FitFriends.dal.UsersDao;
import FitFriends.dal.WorkoutsDao;
import FitFriends.model.Users;
import FitFriends.model.Workouts;

@WebServlet("/userworkoutdelete")
public class UserWorkoutDelete extends HttpServlet {
	protected UserWorkoutsDao userWorkoutsDao;
	protected WorkoutsDao workoutsDao;
	
	@Override
	public void init() throws ServletException {
		userWorkoutsDao = UserWorkoutsDao.getInstance();
		workoutsDao = WorkoutsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete User");        
        req.getRequestDispatcher("/UserWorkoutDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String workoutId = req.getParameter("workoutid");
        if (workoutId == null || workoutId.trim().isEmpty()) {
            messages.put("title", "Invalid workoutId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the User.
	        Workouts workout;
	        try {
	        	workout = workoutsDao.delete(Integer.parseInt(workoutId));
	        	// Update the message.
		        if (workout == null) {
		            messages.put("title", "Successfully deleted. ");
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete ");
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserWorkoutDelete.jsp").forward(req, resp);
    }
}
