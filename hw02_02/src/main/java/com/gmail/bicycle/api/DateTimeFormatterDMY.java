package com.gmail.bicycle.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateTimeFormatterDMY extends XmlAdapter<String, LocalDate> {
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	public DateTimeFormatterDMY() {
		super();
	}

	@Override
	public LocalDate unmarshal(String v) throws Exception {
		return LocalDate.parse(v, formatter);
	}

	@Override
	public String marshal(LocalDate v) throws Exception {
		return v.format(formatter);
	}

	public DateTimeFormatter getFormatter() {
		return formatter;
	}

	public void setFormatter(DateTimeFormatter formatter) {
		this.formatter = formatter;
	}

}
