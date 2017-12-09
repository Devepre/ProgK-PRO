package com.gmail.bicycle.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

public class GsonHandler {

	public GsonHandler() {
		super();
	}

	public Object get(String filePath, Class<?> inputClass) throws IOException {
		String jsonData = readFromFile(filePath);
		Gson gson = new Gson();
		Object result = gson.fromJson(jsonData, inputClass);

		return result;
	}

	private String readFromFile(String filePath) throws IOException {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append(System.lineSeparator());
			}
		}

		return sb.toString().trim();
	}

}
