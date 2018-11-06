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


@WebServlet("/workout")
public class Workout extends HttpServlet {
	protected WorkoutsDao workoutsDao;
	
	public void init() throws ServletException {
		workoutsDao = WorkoutsDao.getInstance();
	}
	

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Workouts> workouts = new ArrayList<Workouts>();
        
        // Retrieve and validate the intensity that is retrieved from the URL query string.
        String intensity = req.getParameter("intensity");
        if (intensity == null || intensity.trim().isEmpty()) {
        	try {
            	workouts = workoutsDao.getAllWorkouts();
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying all of the workouts.");
        } else {
        	//Retrieve all workouts, and store as a message.
        	try {
            	workouts = workoutsDao.getAllWorkoutsByIntensity(intensity);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying all of the workouts.");
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        
        }
 
        req.setAttribute("workouts", workouts);
        
        req.getRequestDispatcher("/Workout.jsp").forward(req, resp);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		List<Workouts> workouts = new ArrayList<Workouts>();
    
		// Retrieve and validate the user last name that is retrieved from the form POST submission. By default, it
		// is populated by the URL query string (in FindUsers.jsp).
		String intensity = req.getParameter("intensity");
		if (intensity == null || intensity.trim().isEmpty()) {
			messages.put("success", "Please enter a valid intensity value.");
		} else {
			// Retrieve workouts, and store as a message.
			try {
				workouts = workoutsDao.getAllWorkoutsByIntensity(intensity);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for workouts with the intensity: " + intensity);
		}
		req.setAttribute("workouts", workouts);
    
		req.getRequestDispatcher("/Workout.jsp").forward(req, resp);
	}

}
