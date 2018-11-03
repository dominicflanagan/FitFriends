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


@WebServlet("/runprogresshome")
public class RunProgressHome extends HttpServlet {
	
	protected RunProgressDao runProgressDao;
	
	@Override
	public void init() throws ServletException {
		runProgressDao = RunProgressDao.getInstance();
	}
	
@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<RunProgress> runProgress = new ArrayList<RunProgress>();
        
        // Retrieve and validate the parameters retrieved from the URL query string.
        String userName = req.getParameter("username");
         if (userName == null || userName.trim().isEmpty() ) {
            messages.put("success", "Please enter  a valid UserName.");
        } else {
        	
        	// Retrieve data, and store as a message.
        	try {
        		runProgress = runProgressDao.getRunProgressByUserName(userName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + userName);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering PersonsHome.jsp.
        	messages.put("previousMemberId", userName);
         }
 
        req.setAttribute("runtimeprogress", runProgress);
        
        req.getRequestDispatcher("/RunProgressHome.jsp").forward(req, resp);
	}


@Override
public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    List<RunProgress> runProgress = new ArrayList<RunProgress>();
    
    // Retrieve and validate the parameters retrieved from the URL query string.
    String userName = req.getParameter("username");
     if (userName == null || userName.trim().isEmpty() ) {
        messages.put("success", "Please enter  a valid UserName.");
    } else {
   	
    	// Retrieve data, and store as a message.
    	try {
    		runProgress = runProgressDao.getRunProgressByUserName(userName);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
    	messages.put("success", "Displaying results for " + userName);
    	// Save the previous search term, so it can be used as the default
    	// in the input box when rendering PersonsHome.jsp.
    	messages.put("previousUserName", userName);
     }

    req.setAttribute("runtimeprogress", runProgress);
    
    req.getRequestDispatcher("/RunProgressHome.jsp").forward(req, resp);
}
}

