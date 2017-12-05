package com.gmail.bicycle;

import java.util.List;

import com.gmail.bicycle.annotation.ParametersInvoker;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("<Demo using of current library>");

		AnnotatedClass annotedObject = new AnnotatedClass();
		ParametersInvoker parametersInvoker = new ParametersInvoker(annotedObject, ComputeValues.class);
		List<Object> results = parametersInvoker.getResults();

		printResults(results);
	}

	public static void printResults(List<Object> results) {
		if (results!= null) {
			System.out.println("Results of annotated methods are:");
			results.stream()
				.forEach(System.out::println);
		} else {
			System.out.println("Empty results!");
		}
		
	}
}
