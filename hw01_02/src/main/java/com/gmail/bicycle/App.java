package com.gmail.bicycle;

import com.gmail.bicycle.api.SaverWrapper;
import com.gmail.bicycle.api.TextContainer;

public class App {
	public static void main(String[] args) {
		if (args.length > 0 && args[0].getClass().equals(String.class)) {
			String info = args[0];
			TextContainer textContainer = new TextContainer(info);
			SaverWrapper worker = new SaverWrapper();
			worker.run(textContainer);
			System.out.println("Done.");
		} else {
			System.out.println("Usage: App <\"string information\">");
		}
	}

}
