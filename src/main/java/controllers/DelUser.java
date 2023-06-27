package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import managers.ManageTweets;
import managers.ManageUsers;
import models.Tweet;
import models.User;

/**
 * Servlet implementation class DelTweet
 */
@WebServlet("/DelUser")
public class DelUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		User target_user = new User(); // user to be deleted
		ManageUsers userManager = new ManageUsers();
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		
		
		try {
			
			if (session != null || user != null) {
				BeanUtils.populate(target_user, request.getParameterMap());
				System.out.println("target user id "+target_user.getId());
				
				userManager.deleteUser(target_user.getId());
				userManager.finalize();
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		if ((int)target_user.getId() == (int) user.getId()) {
			if (session!=null) {
				session.invalidate();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("ViewGoodbye.jsp");
			if (dispatcher != null) dispatcher.forward(request, response);	
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("ViewOwnTimeline.jsp");
			dispatcher.forward(request, response);
		}



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}