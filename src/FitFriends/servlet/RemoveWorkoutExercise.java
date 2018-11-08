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

import FitFriends.dal.PersonsDao;
import FitFriends.dal.WorkoutExersisesDao;
import FitFriends.model.Persons;
import FitFriends.model.WorkoutExercise;

@WebServlet("/removeworkoutexercise")
public class RemoveWorkoutExercise extends HttpServlet {
	private WorkoutExercise workoutExercise;
	protected WorkoutExersisesDao workoutExercisesDao;
	
	@Override
	public void init() throws ServletException {
		workoutExercisesDao = WorkoutExersisesDao.getInstance();
		workoutExercise = new WorkoutExercise();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        String workoutId = req.getParameter("workoutid");
 		String exerciseId = req.getParameter("exerciseid");
 		if (workoutId == null || workoutId.trim().isEmpty() || exerciseId == null || exerciseId.trim().isEmpty()) {
 			messages.put("title", "Invalid workout exercise");
 			messages.put("disableSubmit", "true");
 		} else {
 			try {
				workoutExercise = workoutExercisesDao.getWorkoutExerciseByIds(Integer.parseInt(workoutId), Integer.parseInt(exerciseId));
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 			messages.put("success", "Delete the workout excercise");
 			// Save the previous search term, so it can be used as the default
 			// in the input box when rendering FindUsers.jsp.
 		}
 		
 		req.getRequestDispatcher("/WorkoutExerciseRemove.jsp").forward(req, resp);
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
	        	workoutExercise = workoutExercisesDao.delete(workoutExercise);
	        	// Update the message.
		        if (workoutExercise == null) {
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
        
        req.getRequestDispatcher("/WorkoutExerciseRemove.jsp").forward(req, resp);
    }
}
