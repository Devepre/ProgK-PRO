package com.gmail.bicycle;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;

import com.gmail.bicycle.api.XMLWorker;
import com.gmail.bicycle.models.Train;
import com.gmail.bicycle.models.Trains;
import com.gmail.bicycle.models.TrainsHandler;

/**
 * Example of using library
 *
 */
public class App {
	public static void main(String[] args) {
		if (args.length > 0 && args[0].getClass().equals(String.class)) {
			String filePath = args[0];

			XMLWorker worker = new XMLWorker(Trains.class);
			Trains trains = (Trains) worker.loadFromFile(new File(filePath));
			Train train = new Train(100, "Poltava", "Odesa", LocalDate.now(), LocalTime.now());
			TrainsHandler.displayTrains(trains, LocalTime.parse("15:00"), LocalTime.parse("19:00"));
			TrainsHandler.addTrainToXML(new File(filePath), train);

			System.out.println("Done.");
		} else {
			System.out.println("Usage: App <file name>");
		}
	}

}
