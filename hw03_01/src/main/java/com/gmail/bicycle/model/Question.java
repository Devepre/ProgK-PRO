package com.gmail.bicycle.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Question {
	private String questionBody;
	private List<Answer> answers = new ArrayList<Answer>();
	
	public Question() {
		super();
	}
	
	public Question(String questionBody, String... answerText) {
		super();
		this.questionBody = questionBody;
		for (int i = 0; i < answerText.length; i++) {
			this.answers.add(new Answer(answerText[i], i));
		}
	}

	public void addResponse(int id) {
		
	}
	
	class Answer {
		private String text;
		private int responses;

		public Answer() {
			super();
		}

		public Answer(String text, int responses) {
			super();
			this.text = text;
			this.responses = responses;
		}
		
	}
	
	public List<Integer> getStat(){
		List<Integer> result = new ArrayList<Integer>();
		
		Set<Integer> s = answers.keySet();
		for (Integer integer : s) {
			
		}
		
		return result;
	}
	
}
