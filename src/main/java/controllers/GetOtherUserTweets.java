package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import managers.ManageTweets;
import models.Tweet;
import models.User;

/**
 * Servlet implementation class dTcontroller
 */
@WebServlet("/GetOtherUserTweets")
public class GetOtherUserTweets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOtherUserTweets() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	User target_user = new User();
		HttpSession session = request.getSession(false);
		List<Tweet> tweets = new ArrayList<>();
		User user = (User) session.getAttribute("user");
		try {
			if (session != null || user != null) {
				BeanUtils.populate(target_user, request.getParameterMap());
				ManageTweets tweetManager = new ManageTweets();
				tweets = tweetManager.getUserTweets(target_user.getId(),0,4);
				tweetManager.finalize();
			}
		}
		catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}		

		request.setAttribute("tweets",tweets);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewTweets.jsp"); 
		dispatcher.forward(request,response);
		
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

