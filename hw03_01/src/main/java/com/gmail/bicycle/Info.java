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

	private final int answersCount = 5;
	private String[] questions = { "Does multiple inheritance is allowed in Java?", "What is SOLID?" };
	private String[] answerText = { "Yes", "No", "Firm and stable in shape", "Not liquid or fluid",
			"Principles of Object Oriented Design" };
	private int[] answers = new int[answersCount];

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Info() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String answerOne = request.getParameter("question1");
		String answerTwo = request.getParameter("question2");

		try {
			int valueOne = Integer.parseInt(answerOne);
			int valueTwo = Integer.parseInt(answerTwo);

			answers[valueOne - 1]++;
			answers[valueTwo - 1]++;
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			createResponse(response);
		}

	}

	private void createResponse(HttpServletResponse response) throws IOException {
		response.getWriter().append("Here are results of the current Poll:" + System.lineSeparator());
		for (int i = 0; i < answersCount; i++) {
			if (i == 0 || i == 2) {
				response.getWriter()
						.append(System.lineSeparator() + questions[i == 0 ? 0 : 1] + System.lineSeparator());
			}
			response.getWriter().append(answerText[i] + ": ")
					.append(answers[i] + " responses" + System.lineSeparator());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
