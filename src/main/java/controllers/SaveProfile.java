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
 * Servlet implementation class AddTweetFromUser
 */
@WebServlet("/SaveProfile")
public class SaveProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveProfile() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ManageUsers manager = new ManageUsers();
		HttpSession session = request.getSession(false);
		User user = new User();
		User oldUser = new User();
		boolean cn = false;
		
		try {
			if (session != null || user != null) {
				BeanUtils.populate(user, request.getParameterMap());
				oldUser = manager.getUser(user.getUsername());
				cn = (manager.checkUser(user.getUsername()) && !(oldUser.getUsername()).equals(user.getUsername()));
				user.setError("user", cn);

				if (manager.editComplete(user) && !cn) {
					manager.editProfile(user);
					request.setAttribute("user", user);

				} else if (cn){
					oldUser.setError("user", cn);
					request.setAttribute("user", oldUser);
				} else {
					oldUser.setError("notComplete", true);
					request.setAttribute("user", oldUser);
				}
				manager.finalize();
				RequestDispatcher dispatcher = request.getRequestDispatcher("ViewUserInfo.jsp");
				dispatcher.forward(request, response);
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}