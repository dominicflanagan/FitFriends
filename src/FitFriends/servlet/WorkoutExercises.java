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

import FitFriends.dal.WorkoutExersisesDao;
import FitFriends.dal.WorkoutsDao;
import FitFriends.model.Exercise;
import FitFriends.model.Workouts;

@WebServlet("/workoutexercises")
public class WorkoutExercises extends HttpServlet {
	protected WorkoutExersisesDao workoutExercisesDao;
	
	public void init() throws ServletException {
		workoutExercisesDao = WorkoutExersisesDao.getInstance();
	}
	

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Exercise> workoutExercises = new ArrayList<Exercise>();
        
        // Retrieve and validate the intensity that is retrieved from the URL query string.
        String workoutId = req.getParameter("workoutid");
        if (workoutId == null || workoutId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid workout id.");
        } else {
        	//Retrieve all exercises for the workout, and store as a message.
        	try {
            	workoutExercises = workoutExercisesDao.getAllExercisesByWorkoutId(Integer.parseInt(workoutId));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying all of the workouts.");
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        
        }
 
        req.setAttribute("workoutexercises", workoutExercises);
        
        req.getRequestDispatcher("/WorkoutExercises.jsp").forward(req, resp);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		 List<Exercise> workoutExercises = new ArrayList<Exercise>();
    
		// Retrieve and validate the user last name that is retrieved from the form POST submission. By default, it
		// is populated by the URL query string (in FindUsers.jsp).
		 String workoutId = req.getParameter("workoutid");
	        if (workoutId == null || workoutId.trim().isEmpty()) {
	            messages.put("success", "Please enter a valid workout id.");
	        } else {
	        	//Retrieve all exercises for the workout, and store as a message.
	        	try {
	            	workoutExercises = workoutExercisesDao.getAllExercisesByWorkoutId(Integer.parseInt(workoutId));
	            } catch (SQLException e) {
	    			e.printStackTrace();
	    			throw new IOException(e);
	            }
	        	messages.put("success", "Displaying all of the workouts.");
	        	// Save the previous search term, so it can be used as the default
	        	// in the input box when rendering FindUsers.jsp.
	        
	        }
	 
	        req.setAttribute("workoutexercises", workoutExercises);
	        
	        req.getRequestDispatcher("/WorkoutExercises.jsp").forward(req, resp);
		}
}
