// FitFriends. October 30, 2018.

package FitFriends.servlet;

import FitFriends.dal.*;
import FitFriends.model.*;

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

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/nutritionplanadd")
public class NutritionPlanAdd extends HttpServlet {
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/NutritionPlanAdd.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String userName = req.getParameter("username");
        int memberId = 0;
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
        	// Create the Item
        	UsersDao userDao = UsersDao.getInstance();
        	try {
				Users user = userDao.getUserFromUserName(userName);
				memberId = user.getMemberId();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	int nutritionId = Integer.parseInt(req.getParameter("nutritionid"));
        	int numberServings = Integer.parseInt(req.getParameter("numberservings"));
        	
 	        try {
 	        	NutritionPlan nutritionItem = new NutritionPlan(memberId, nutritionId, numberServings, userName);
 	        	nutritionItem = nutritionPlanDao.addItemToNutritionPlan(nutritionItem);
	        	messages.put("success", "Successfully created " + userName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/NutritionPlanAdd.jsp").forward(req, resp);
    }
}
