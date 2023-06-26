package controllers;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managers.ManageUsers;
import models.Login;
import models.User;

import javax.servlet.http.Cookie;
import managers.ManageUsers;
import models.User;
import models.Login;


/**
 * Servlet implementation class MainController
 */
@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		Cookie[] cookie_jar = request.getCookies();
		boolean success = false;
		User user = new User();
		ManageUsers manager = new ManageUsers();
		
		
		if (session==null || session.getAttribute("user")==null) {
			System.out.println("MainController: NO active session has been found.");
			request.setAttribute("menu","ViewMenuNotLogged.jsp");
			/* P4 comment lines
			request.setAttribute("content","ViewLoginForm.jsp");
			*/
			
			/* P3 CODE: lines  42,43 & 49*/
			request.setAttribute("terms", "Terms&Conditions.jsp");
			request.setAttribute("content","ViewRegisterForm.jsp");
			request.setAttribute("login","ViewLoginForm.jsp");
		}
		else if (cookie_jar != null && cookie_jar.length > 0){
			
			Cookie cUserName = null;
			Cookie cPassword = null;
			
			if (cookie_jar[0].getName().equals("cookuser")) {
				cUserName = cookie_jar[0];
				cPassword = cookie_jar[1];
			} else {
				cUserName = cookie_jar[1];
				cPassword = cookie_jar[0];
			}
			
			success = manager.loginUser(cUserName.getValue(), cPassword.getValue());
			
			if (success) {
				user = manager.getUser(cUserName.getValue());
				
				manager.finalize();

				session = request.getSession();
    			session.setAttribute("user", user);
    			
    			request.setAttribute("menu","ViewMenuLogged.jsp");
    			request.setAttribute("content","ViewOwnTimeline.jsp");
    			
			} else {
				request.setAttribute("menu","ViewMenuNotLogged.jsp");
				request.setAttribute("content","ViewRegisterForm.jsp");
				request.setAttribute("login","ViewLoginForm.jsp");
				
			}
			

		} else {
			System.out.println("Main Controller: active session has been found.");
			request.setAttribute("menu","ViewMenuLogged.jsp");
			request.setAttribute("content","ViewOwnTimeline.jsp");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

