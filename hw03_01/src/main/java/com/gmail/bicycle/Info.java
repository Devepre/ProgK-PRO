package com.gmail.bicycle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Info
 */
public class Info extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private static final String TEMPLATE = "<html>" +
            "<head><title>Poll results:</title></head>" +
            "<body><h1>%s</h1></body></html>";
    private int[] answers = new int[2];
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Info() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("name");
		String userSurname = request.getParameter("surname");
		String answerOne = request.getParameter("question1");
		String answerTwo = request.getParameter("question2");
		
		int valueOne = Integer.parseInt(answerOne);
		int valueTwo = Integer.parseInt(answerTwo);
		
		
		response.getWriter().append("Your name is: ").append(userName).append(System.lineSeparator());		
		response.getWriter().append("Your surname is: ").append(userSurname).append(System.lineSeparator());
		response.getWriter().append("Answer One is: ").append(answerOne);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
