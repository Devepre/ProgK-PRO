package com.gmail.bicycle.models;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.gmail.bicycle.api.DateTimeFormatterDMY;
import com.gmail.bicycle.api.LocalTimeFormatter;

@XmlRootElement(name = "train")
public class Train {
	private int id;
	private String from;
	private String to;
	private LocalDate date;
	private LocalTime departure;

	public Train() {
		super();
	}

	public Train(int id, String from, String to, LocalDate date, LocalTime departure) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.date = date;
		this.departure = departure;
	}

	@XmlAttribute
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	@XmlElement
	@XmlJavaTypeAdapter(DateTimeFormatterDMY.class)
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@XmlElement
	@XmlJavaTypeAdapter(LocalTimeFormatter.class)
	public LocalTime getDeparture() {
		return departure;
	}

	public void setDeparture(LocalTime departure) {
		this.departure = departure;
	}

	@Override
	public String toString() {
		return "Train [id=" + id + ", from=" + from + ", to=" + to + ", date=" + date + ", departure=" + departure
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Train other = (Train) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
