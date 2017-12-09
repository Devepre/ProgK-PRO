package com.gmail.bicycle;

import java.io.IOException;

import com.gmail.bicycle.api.GsonHandler;
import com.gmail.bicycle.models.Lector;

/**
 * Example of using library
 *
 */
public class App {
	public static void main(String[] args) {
		if (args.length > 0 && args[0].getClass().equals(String.class)) {
			String filePath = args[0];
			
			GsonHandler gsonHandler = new GsonHandler();
			try {
				Lector lector = (Lector) gsonHandler.get(filePath, Lector.class);
				System.out.println(lector);
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("Done.");
		} else {
			System.out.println("Usage: App <file name>");
		}
	}

}
