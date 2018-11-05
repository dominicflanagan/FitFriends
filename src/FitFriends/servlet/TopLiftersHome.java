// FitFriends. Nov 5, 2018.

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


@WebServlet("/topliftershome")
public class TopLiftersHome extends HttpServlet {
	
	protected TopLiftersDao topLiftersDao;
	
	@Override
	public void init() throws ServletException {
		topLiftersDao = TopLiftersDao.getInstance();
	}
	
@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<TopLifters> topLifters = new ArrayList<TopLifters>();
        
        // Retrieve and validate the parameters retrieved from the URL query string.
        String stringWeight = req.getParameter("weight");
        String stringTopN = req.getParameter("topN");
        if (stringWeight == null || stringWeight.trim().isEmpty() || stringTopN == null || stringTopN.trim().isEmpty()) {
            messages.put("success", "Please enter  a valid weight and topN number.");
        } else {
        	int weight = Integer.parseInt(stringWeight);
        	int topN = Integer.parseInt(stringTopN);
        	// Retrieve data, and store as a message.
        	try {
        		topLifters = topLiftersDao.getTopLifters(weight, topN);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for the top " + topN + " lifters for the " + weight + " pounds weight");
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering PersonsHome.jsp.
        	messages.put("previousWeight", stringWeight);
        	messages.put("previousTopN", stringTopN);
        }
 
        req.setAttribute("toplifters", topLifters);
        
        req.getRequestDispatcher("/TopLiftersHome.jsp").forward(req, resp);
	}

@Override
public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    List<TopLifters> topLifters = new ArrayList<TopLifters>();
    
    // Retrieve and validate the parameters retrieved from the URL query string.
    String stringWeight = req.getParameter("weight");
    String stringTopN = req.getParameter("topN");
    if (stringWeight == null || stringWeight.trim().isEmpty() || stringTopN == null || stringTopN.trim().isEmpty()) {
        messages.put("success", "Please enter  a valid weight and topN number.");
    } else {
    	int weight = Integer.parseInt(stringWeight);
    	int topN = Integer.parseInt(stringTopN);
    	// Retrieve data, and store as a message.
    	try {
    		topLifters = topLiftersDao.getTopLifters(weight, topN);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
    	messages.put("success", "Displaying results for the top " + topN + " lifters for the " + weight + " pounds weight");
    }
    req.setAttribute("toplifters", topLifters);
    
    req.getRequestDispatcher("/TopLiftersHome.jsp").forward(req, resp);
}
}

