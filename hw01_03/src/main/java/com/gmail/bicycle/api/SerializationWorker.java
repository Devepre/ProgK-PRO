package com.gmail.bicycle.api;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.gmail.bicycle.DataClass;
import com.gmail.bicycle.annotation.Save;

public class SerializationWorker {
	public static final Class<? extends Annotation> CLASS_SAVE = Save.class;
	private String fileName = "default.d";

	public SerializationWorker() {
		super();
	}

	public SerializationWorker(String fileName) {
		super();
		this.fileName = fileName;
	}

	public void saveAnnotated(DataClass dataClass) {
		deleteIfFileExist(fileName);
		List<Field> fields = getAnnotatedFields(dataClass);
		for (Field field : fields) {
			try {
				if (!field.isAccessible()) {
					field.setAccessible(true);
				}
				Object value = field.get(dataClass);
				saveObject(fileName, value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	public void loadAnnotated(DataClass dataClass) {
		List<Object> result = loadObject(fileName);
		int index = 0;
		List<Field> fields = getAnnotatedFields(dataClass);
		for (Field field : fields) {
			try {
				if (!field.isAccessible()) {
					field.setAccessible(true);
				}
				field.set(dataClass, result.get(index++));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

	}

	private List<Field> getAnnotatedFields(Object obj) {
		List<Field> result = new ArrayList<>();

		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(CLASS_SAVE)) {
				result.add(field);
			}
		}

		return result;
	}

	private void saveObject(String fileName, Object obj) {

		try (PrintWriter pw = new PrintWriter(new FileOutputStream(fileName, true), true)) {
			String str = objToString(obj);
			pw.println(str);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private List<Object> loadObject(String fileName) {
		List<Object> result = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				try {
					result.add(objFromString(line));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	private String objToString(Object obj) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
			oos.writeObject(obj);
		}

		return Base64.getEncoder().encodeToString(baos.toByteArray());
	}

	private Object objFromString(String str) throws IOException, ClassNotFoundException {
		Object obj = null;
		byte[] data = Base64.getDecoder().decode(str);
		try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
			obj = ois.readObject();
		}

		return obj;
	}

	private void deleteIfFileExist(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		}
	}
}
