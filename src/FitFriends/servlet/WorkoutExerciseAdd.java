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

import FitFriends.dal.WorkoutExersisesDao;
import FitFriends.model.WorkoutExercise;

@WebServlet("/workoutexerciseadd")
public class WorkoutExerciseAdd extends HttpServlet {
	protected WorkoutExersisesDao workoutExercisesDao;
	private String workoutId;
	private String exerciseId;
	
	@Override
	public void init() throws ServletException {
		workoutExercisesDao = WorkoutExersisesDao.getInstance();
		workoutId = "";
		exerciseId = "";
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        workoutId = req.getParameter("workoutid");
 		exerciseId = req.getParameter("exerciseid");
 		if (workoutId == null || workoutId.trim().isEmpty() || exerciseId == null || exerciseId.trim().isEmpty()) {
 			messages.put("title", "Invalid workout exercise");
 			messages.put("disableSubmit", "true");
 		} else {

 			messages.put("success", "Add the workout excercise");
 			// Save the previous search term, so it can be used as the default
 			// in the input box when rendering FindUsers.jsp.
 		}
 		
 		req.getRequestDispatcher("/WorkoutExercisesAdd.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
//        String workoutId = req.getParameter("workoutid");
//        String exerciseId = req.getParameter("exerciseid");
        if (workoutId == null || workoutId.trim().isEmpty() || exerciseId == null || exerciseId.trim().isEmpty()) {
            messages.put("title", "Invalid workout exercise");
            messages.put("disableSubmit", "true");
        } else {
        	 //add the exercise to the workout.
	        try {
	        	WorkoutExercise workoutExercise = workoutExercisesDao.create(Integer.parseInt(workoutId), Integer.parseInt(exerciseId));
	        	// Update the message.
		        //if (workoutExercise == null) {
		          //  messages.put("title", "Failed to delete.");
		            //messages.put("disableSubmit", "true");
		        //} else {
		        	messages.put("title", "Successfully deleted.");
		        	messages.put("disableSubmit", "false");
		        //}
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/WorkoutExercises.jsp").forward(req, resp);
    }
}
