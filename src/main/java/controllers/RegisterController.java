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
 * Servlet implementation class FormController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	   System.out.print("RegisterController: ");
	   User user = new User();
	   ManageUsers manager = new ManageUsers();
	   boolean cn = false;
	   boolean cm = false;
	   boolean cp = false;
	
	   try {
	
		   BeanUtils.populate(user, request.getParameterMap());
		   
		   cn = manager.checkUser(user.getUsername());
		   cm = manager.checkMail(user.getMail());
		   cp = manager.checkPhone(user.getPhone());
		   
		   user.setError("username_exists", cn);
		   user.setError("mail_exists", cm);
		   user.setError("phone_exists", cp);
		   
		   
		   if (manager.isComplete(user) && !cn && !cm && !cp) {
			   
			   manager.addUser(user);
			   /* P3 CODE: líneas 61-63  + session  */
			   System.out.println(" user ok, forwarding to ViewLoginDone.");
			   HttpSession session = request.getSession();
			   User regUser = manager.getUser(user.getUsername());
			   session.setAttribute("user", regUser);
			   
			   manager.finalize();
			   RequestDispatcher dispatcher = request.getRequestDispatcher("ViewOwnTimeline.jsp");
			   dispatcher.forward(request, response);
			   
			  /*System.out.println(" user ok, forwarding to ViewLoginForm.");
			   RequestDispatcher dispatcher = request.getRequestDispatcher("ViewLoginForm.jsp");
			   dispatcher.forward(request, response);*/ 
		   
		   } 
		   
		   else  {
		
			   System.out.println(" forwarding to ViewRegisterForm.");
			   request.setAttribute("user",user);
			   RequestDispatcher dispatcher = request.getRequestDispatcher("ViewRegisterForm.jsp");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
