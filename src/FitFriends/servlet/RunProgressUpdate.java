// FitFriends. October 30, 2018.

package FitFriends.servlet;

import FitFriends.dal.*;
import FitFriends.model.*;

import java.io.IOException;
import java.sql.SQLException;
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


@WebServlet("/runprogressupdate")
public class RunProgressUpdate extends HttpServlet {
	
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

        // Retrieve and validate user name
        String userName = req.getParameter("username");
         
        List<RunProgress> runProgress = new ArrayList<RunProgress>();
        int memberId = 0;
        Date created;
        int distanceMeters = 0;
       
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("title", "Invalid UserName");
            messages.put("disableSubmit", "true");
        } else {
        	messages.put("title", "Run Progress Update for " + userName);
        	UsersDao usersDao = UsersDao.getInstance();
        	Users user;
			try {
				user = usersDao.getUserFromUserName(userName);
				memberId =  user.getMemberId();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
           
            try {
				created = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("created"));
				distanceMeters  = Integer.parseInt(req.getParameter("distance"));
		        
		            RunProgress runProgress1 = new RunProgress(memberId, created, distanceMeters);
		            RunProgressDao runProgressDao = RunProgressDao.getInstance();
		            runProgress1 = runProgressDao.getRunTimesForUserByRunProgress(runProgress1);
		            runProgress.add(runProgress1);

			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 
        }
        req.setAttribute("runprogress", runProgress);   
        req.getRequestDispatcher("/RunProgressUpdate.jsp").forward(req, resp);
    }


	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate user name
        String userName = req.getParameter("username");
         
        List<RunProgress> runProgress = new ArrayList<RunProgress>();
        int memberId = 0;
        Date created;
        int distanceMeters = 0;
       
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("title", "Invalid UserName");
            messages.put("disableSubmit", "true");
        } else {
        	messages.put("title", "Run Progress Update for " + userName);
        	UsersDao usersDao = UsersDao.getInstance();
        	Users user;
			try {
				user = usersDao.getUserFromUserName(userName);
				memberId =  user.getMemberId();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
           
            try {
				created = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("created"));
				distanceMeters  = Integer.parseInt(req.getParameter("distance"));
				double newRunTimeInSeconds = Double.parseDouble(req.getParameter("runtime"));
		        
		            RunProgress runProgress1 = new RunProgress(memberId, created, distanceMeters);
		            RunProgressDao runProgressDao = RunProgressDao.getInstance();
		            runProgress1 = runProgressDao.getRunTimesForUserByRunProgress(runProgress1);
		            runProgress1 = runProgressDao.updateRunTime(runProgress1, newRunTimeInSeconds);
		            runProgress.add(runProgress1);

			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 
        }
        req.setAttribute("runprogress", runProgress);   
        req.getRequestDispatcher("/RunProgressUpdate.jsp").forward(req, resp);
    }
}
