package controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managers.ManageTweets;
import models.Tweet;
import models.User;

/**
 * Servlet implementation class dTcontroller
 */
@WebServlet("/GetAllTweets")
public class GetAllTweets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllTweets() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		List<Tweet> tweets = Collections.emptyList();
		User user = (User) session.getAttribute("user");
		
		if (session != null || user != null) {
			ManageTweets tweetManager = new ManageTweets();
			tweets = tweetManager.getAllTweets(0,4);
			tweetManager.finalize();
		}

		request.setAttribute("tweets",tweets);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewAllTweets.jsp"); 
		dispatcher.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

