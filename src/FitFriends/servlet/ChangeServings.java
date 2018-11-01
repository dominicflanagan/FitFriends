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


@WebServlet("/changeservings")
public class ChangeServings extends HttpServlet {
	
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
        messages.put("title", "Change Servings");        
        req.getRequestDispatcher("/ChangeServings.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate user name
        String userName = req.getParameter("username");
        int nutritionId = Integer.parseInt(req.getParameter("nutritionid"));
        int newNumberServings = Integer.parseInt(req.getParameter("newnumberservings"));
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("title", "Invalid UserName");
            messages.put("disableSubmit", "true");
        } else {
        	// update servings
        	NutritionPlan nutritionPlan;
	        try {
	        	nutritionPlan = nutritionPlanDao.getNutritionPlanByUserAndNutrition(userName, nutritionId);
	        	nutritionPlan = nutritionPlanDao.updateNumberServings(nutritionPlan, newNumberServings );
	        	req.setAttribute("nutritionplan", nutritionPlan);
	        	// Update the message.
		        if (nutritionPlan != null) {
		            messages.put("title", "Updated plan for " + userName);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to update plan for " + userName);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ChangeServings.jsp").forward(req, resp);
    }
}
