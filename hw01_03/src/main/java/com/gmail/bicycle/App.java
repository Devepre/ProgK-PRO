package com.gmail.bicycle;

import com.gmail.bicycle.api.SerializationWorker;

/**
 * Example of using library
 *
 */
public class App {
	public static void main(String[] args) {
		if (args.length > 0 && args[0].getClass().equals(String.class)) {
			String fileName = args[0];
			SerializationWorker worker = new SerializationWorker(fileName);
			DataClass dataClass = new DataClass("Twist", Math.E, -18, new StringBuilder("sb"));
			worker.saveAnnotated(dataClass);
			dataClass = new DataClass();
			worker.loadAnnotated(dataClass);
			System.out.printf("Loaded class is: %s%n", dataClass);
			System.out.println("Done.");
		} else {
			System.out.println("Usage: App <file name>");
		}
	}

}
