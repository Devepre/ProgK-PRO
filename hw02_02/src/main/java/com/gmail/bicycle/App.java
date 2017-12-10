package com.gmail.bicycle;

/**
 * Example of using library
 *
 */
public class App {
	public static void main(String[] args) {
		if (args.length > 0 && args[0].getClass().equals(String.class)) {
			String filePath = args[0];

			System.out.println("Done.");
		} else {
			System.out.println("Usage: App <file name>");
		}
	}

}
