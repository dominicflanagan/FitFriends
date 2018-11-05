// FitFriends. Nov 5, 2018.

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


@WebServlet("/liftprogressdelete")
public class LiftProgressDelete extends HttpServlet {
	
	protected LiftProgressDao liftProgressDao;
	
	@Override
	public void init() throws ServletException {
		liftProgressDao = LiftProgressDao.getInstance();
	}
	

	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate user name
        String userName = req.getParameter("username");
         
        List<LiftProgress> liftProgress = new ArrayList<LiftProgress>();
        int memberId = 0;
        Date created;
        int weightPounds = 0;
       
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("title", "Invalid UserName");
            messages.put("disableSubmit", "true");
        } else {
        	messages.put("title", "Delete Lift Progress Item for " + userName);
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
				weightPounds  = Integer.parseInt(req.getParameter("weight"));
		        
		            LiftProgress liftProgress1 = new LiftProgress(memberId, created, weightPounds);
		            LiftProgressDao liftProgressDao = LiftProgressDao.getInstance();
		            liftProgress1 = liftProgressDao.getNumRepsForUserByLiftProgress(liftProgress1);
		            liftProgress.add(liftProgress1);

			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 
        }
        req.setAttribute("liftprogress", liftProgress);   
        req.getRequestDispatcher("/LiftProgressDelete.jsp").forward(req, resp);
    }


	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate user name
        String userName = req.getParameter("username");
         
        List<LiftProgress> liftProgress = new ArrayList<LiftProgress>();
        int memberId = 0;
        Date created;
        int weightPounds = 0;
       
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("title", "Invalid UserName");
            messages.put("disableSubmit", "true");
        } else {
        	messages.put("title", "Delete Run Progress Item for " + userName);
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
				weightPounds  = Integer.parseInt(req.getParameter("weight"));
	
	            LiftProgress liftProgress1 = new LiftProgress(memberId, created, weightPounds);
	            LiftProgressDao liftProgressDao = LiftProgressDao.getInstance();
	            liftProgress1 = liftProgressDao.getNumRepsForUserByLiftProgress(liftProgress1);
	            liftProgress1 = liftProgressDao.delete(liftProgress1);
			        if (liftProgress1 == null) {
			            messages.put("title", "Successfully deleted the " + weightPounds + " lift for " + userName);
			            messages.put("disableSubmit", "true");
			        } else {
			        	messages.put("title", "Failed to delete the " + weightPounds + " lift for " + userName);
			        	messages.put("disableSubmit", "false");
			        }

			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 
        }
        req.setAttribute("liftprogress", liftProgress);   
        req.getRequestDispatcher("/LiftProgressDelete.jsp").forward(req, resp);
    }
}
