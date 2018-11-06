package FitFriends.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import FitFriends.dal.UserWorkoutsDao;
import FitFriends.dal.UsersDao;
import FitFriends.model.Users;
import FitFriends.model.Workouts;
import FitFriends.model.Persons;
import FitFriends.model.UserWorkout;

@WebServlet("/userworkoutcreate")
public class UserWorkoutCreate extends HttpServlet {
	protected UserWorkoutsDao userWorkoutsDao;
	protected UsersDao usersDao;
	
	public void init() throws ServletException {
		userWorkoutsDao = UserWorkoutsDao.getInstance();
		usersDao = UsersDao.getInstance();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/UserWorkoutCreate.jsp").forward(req, resp);
	}
	
	
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Invalid workoutId ");
        } else {
        	int workoutId = Integer.parseInt(req.getParameter("workoutid"));
	        try {
	        	Persons user = usersDao.getPersonFromUserName(userName);
	        	UserWorkout userWorkout = new UserWorkout(workoutId,user.getMemberId());
	        	userWorkout = userWorkoutsDao.create(userWorkout);
	        	messages.put("success", "Successfully created.");
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserWorkoutCreate.jsp").forward(req, resp);
    }

}
