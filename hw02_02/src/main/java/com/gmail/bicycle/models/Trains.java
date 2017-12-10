package com.gmail.bicycle.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "trains")
public class Trains {
	private List<Train> listOfTrain = new ArrayList<>();

	public Trains() {
		super();
	}

	public Trains(List<Train> listOfTrain) {
		super();
		this.listOfTrain = listOfTrain;
	}

	public void addTrain(Train train) {
		if (train != null) {
			this.listOfTrain.add(train);			
		} else {
			throw new IllegalArgumentException("Can't be null");
		}
		
	}

	@XmlElement(name = "train")
	public List<Train> getListOfTrain() {
		return listOfTrain;
	}

	public void setListOfTrain(List<Train> listOfTrain) {
		this.listOfTrain = listOfTrain;
	}

	@Override
	public String toString() {
		return "Trains [listOfTrain=" + listOfTrain + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listOfTrain == null) ? 0 : listOfTrain.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trains other = (Trains) obj;
		if (listOfTrain == null) {
			if (other.listOfTrain != null)
				return false;
		} else if (!listOfTrain.equals(other.listOfTrain))
			return false;
		return true;
	}
	
}
