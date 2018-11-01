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


@WebServlet("/nutritionplanhome")
public class NutritionPlanHome extends HttpServlet {
	
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
        
        req.getRequestDispatcher("/NutritionPlanHome.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<NutritionPlan> nutritionPlans = new ArrayList<NutritionPlan>();
        
        // Retrieve and validate name.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("title", "Invalid UserName");
            messages.put("disableSubmit", "true");
        } else {
        	// get the user's nutrition plan.
        	try {
	        	nutritionPlans = nutritionPlanDao.getNutritionPlanForUser(userName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        	messages.put("success", "Displaying nutrition plan for user " + userName);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering PersonsHome.jsp.
        	messages.put("previousUserName", userName);
        }
        req.setAttribute("nutritionplans", nutritionPlans);       
        req.getRequestDispatcher("/NutritionPlanHome.jsp").forward(req, resp);
    }
}
