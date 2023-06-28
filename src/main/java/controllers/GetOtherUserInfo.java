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
import managers.ManageUsers;
import models.User;

/**
 * Servlet implementation class GetUserInfo
 */
@WebServlet("/GetOtherUserInfo")
public class GetOtherUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOtherUserInfo() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User target_user = new User();
		User otherUser = new User();
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		ManageUsers userManager = new ManageUsers();
		try {
			if (session != null || user != null) {
				BeanUtils.populate(target_user, request.getParameterMap());
				otherUser = userManager.getUser(target_user.getId());	
				userManager.finalize();
			}
			
		} 
		catch (IllegalAccessException | InvocationTargetException e) {
		e.printStackTrace();
		}
		
		request.setAttribute("user",user);
		request.setAttribute("otherUser",otherUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewOtherUserInfo.jsp"); 
		dispatcher.include(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
