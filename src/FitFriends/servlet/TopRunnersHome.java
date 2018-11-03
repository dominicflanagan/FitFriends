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


@WebServlet("/toprunnershome")
public class TopRunnersHome extends HttpServlet {
	
	protected TopRunnersDao topRunnersDao;
	
	@Override
	public void init() throws ServletException {
		topRunnersDao = TopRunnersDao.getInstance();
	}
	
@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<TopRunners> topRunners = new ArrayList<TopRunners>();
        
        // Retrieve and validate the parameters retrieved from the URL query string.
        String stringEvent = req.getParameter("event");
        String stringTopN = req.getParameter("topN");
        if (stringEvent == null || stringEvent.trim().isEmpty() || stringTopN == null || stringTopN.trim().isEmpty()) {
            messages.put("success", "Please enter  a valid event and topN number.");
        } else {
        	int event = Integer.parseInt(stringEvent);
        	int topN = Integer.parseInt(stringTopN);
        	// Retrieve data, and store as a message.
        	try {
        		topRunners = topRunnersDao.getTopRunners(event, topN);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for the top " + topN + " runners in the " + event + " event");
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering PersonsHome.jsp.
        	messages.put("previousEvent", stringEvent);
        	messages.put("previousTopN", stringTopN);
        }
 
        req.setAttribute("toprunners", topRunners);
        
        req.getRequestDispatcher("/TopRunnersHome.jsp").forward(req, resp);
	}

@Override
public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    List<TopRunners> topRunners = new ArrayList<TopRunners>();
    
    // Retrieve and validate the parameters retrieved from the URL query string.
    String stringEvent = req.getParameter("event");
    String stringTopN = req.getParameter("topN");
    if (stringEvent == null || stringEvent.trim().isEmpty() || stringTopN == null || stringTopN.trim().isEmpty()) {
        messages.put("success", "Please enter  a valid event and topN number.");
    } else {
    	int event = Integer.parseInt(stringEvent);
    	int topN = Integer.parseInt(stringTopN);
    	// Retrieve data, and store as a message.
    	try {
    		topRunners = topRunnersDao.getTopRunners(event, topN);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
    	messages.put("success", "Displaying results for the top " + topN + " runners in the " + event + " event");
    }
    req.setAttribute("toprunners", topRunners);
    
    req.getRequestDispatcher("/TopRunnersHome.jsp").forward(req, resp);
}
}

