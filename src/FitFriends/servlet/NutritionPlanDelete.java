// FitFriends. October 30, 2018.

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


@WebServlet("/nutritionplandelete")
public class NutritionPlanDelete extends HttpServlet {
	
	protected NutritionPlanDao nutritionPlanDao;
	
	@Override
	public void init() throws ServletException {
		nutritionPlanDao = NutritionPlanDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete an Item from Nutrition Plan");        
        req.getRequestDispatcher("/NutritionPlanDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate user name
        int nutritionId = Integer.parseInt(req.getParameter("nutritionid"));
        int memberId = Integer.parseInt(req.getParameter("memberid"));
        NutritionPlan nutritionPlan;
        if (nutritionId < 1 || memberId < 1) {
            messages.put("title", "Invalid Nutrition or Member Id");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the nutrition plan item
	        try {
	        	nutritionPlan = nutritionPlanDao.getNutritionPlanByMemberIdAndNutrition(memberId, nutritionId);
	        	nutritionPlan = nutritionPlanDao.delete(nutritionPlan);
	        	// Update the message.
		        if (nutritionPlan == null) {
		            messages.put("title", "Successfully deleted the nutrition plan item");
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete the nutrition plan item");
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/NutritionPlanDelete.jsp").forward(req, resp);
    }
}
