package com.gmail.bicycle.models;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.gmail.bicycle.api.XMLWorker;

public class TrainsHandler {

	public static Trains createDefault() {
		Trains result = new Trains();

		LocalDate today = LocalDate.now();
		LocalTime tt = LocalTime.now();
		Train trainOne = new Train(1, "Kyiv", "Dnipro", today, tt);
		Train trainTwo = new Train(2, "Lviv", "Kyiv", today, tt);
		Train trainThree = new Train(3, "Kyiv", "Lviv", today, LocalTime.parse("17:00"));

		result.addTrain(trainOne);
		result.addTrain(trainTwo);
		result.addTrain(trainThree);

		return result;

	}

	public static void addTrainToXML(File file, Train train) {
		XMLWorker worker = new XMLWorker(Trains.class);
		Trains trains = (Trains) worker.loadFromFile(file);
		TrainsHandler.addTrain(trains, train);
		worker.saveToFile(trains, file);
	}

	public static void addTrain(Trains trains, Train train) {
		List<Train> listTrains = trains.getListOfTrain();
		if (!listTrains.contains(train)) {
			trains.addTrain(train);
		} else {
			throw new IllegalArgumentException("Train is already exist");
		}

	}

	public static void displayTrains(Trains trains, LocalTime from, LocalTime to) {
		List<Train> listTrains = trains.getListOfTrain();
		for (Train train : listTrains) {
			if (train.getDeparture().compareTo(from) == 1 && train.getDeparture().compareTo(to) == -1) {
				System.out.println(train);
			}
		}
	}

}
