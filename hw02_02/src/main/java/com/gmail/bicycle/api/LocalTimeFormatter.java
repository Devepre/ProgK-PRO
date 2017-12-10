package com.gmail.bicycle.api;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalTimeFormatter extends XmlAdapter<String, LocalTime> {
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

	public LocalTimeFormatter() {
		super();
	}

	@Override
	public LocalTime unmarshal(String v) throws Exception {
		return LocalTime.parse(v, formatter);
	}

	@Override
	public String marshal(LocalTime v) throws Exception {
		return v.format(formatter);
	}

	public DateTimeFormatter getFormatter() {
		return formatter;
	}

	public void setFormatter(DateTimeFormatter formatter) {
		this.formatter = formatter;
	}

}
