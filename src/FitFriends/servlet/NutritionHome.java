// FitFriends.November 02, 2018.

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


@WebServlet("/nutritionhome")
public class NutritionHome extends HttpServlet {
	
	protected NutritionDao nutritionDao;
	
	@Override
	public void init() throws ServletException {
		nutritionDao = NutritionDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        req.getRequestDispatcher("/NutritionHome.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Nutrition> nutritions = new ArrayList<Nutrition>();
        
        // Retrieve and validate the food type string
        String foodType = req.getParameter("foodtype");
        if (foodType == null || foodType.trim().isEmpty()) {
            messages.put("title", "Invalid Food Type");
            messages.put("disableSubmit", "true");
        } else {
        	// get the user's nutrition plan.
        	try {
	        	nutritions = nutritionDao.getNutritionItemsByFoodType(foodType);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        	messages.put("success", "Displaying nutrition item that contains the text: " + foodType);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering NutritionHome.jsp.
        	messages.put("previousFoodType", foodType);
        }
        req.setAttribute("nutritions", nutritions);       
        req.getRequestDispatcher("/NutritionHome.jsp").forward(req, resp);
    }
}
