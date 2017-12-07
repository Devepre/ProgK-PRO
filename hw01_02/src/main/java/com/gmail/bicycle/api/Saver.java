package com.gmail.bicycle.api;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

import com.gmail.bicycle.annotation.SaveMethod;
import com.gmail.bicycle.annotation.SaveTo;

public class Saver {
	static final Class<? extends Annotation> CLASS_SAVE_TO = SaveTo.class;
	static final String ATTRIBUTE_NAME = "path";
	Class<?> containerClass;

	public Saver() {
		super();
	}

	@SaveMethod
	public void saveToFile(TextContainer textContainer) {
		this.containerClass = textContainer.getClass();

		String pathName = getFileName();
		if (pathName != null) {
			try (PrintWriter out = new PrintWriter(pathName)) {
				out.println(textContainer.getData());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException("Can't save");
		}

	}
	
	private String getFileName() {
		String fileName = null;

		if (containerClass.isAnnotationPresent(CLASS_SAVE_TO)) {
			Annotation classAnnotation = containerClass.getAnnotation(CLASS_SAVE_TO);
			try {
				fileName = (String) classAnnotation.annotationType().getMethod(ATTRIBUTE_NAME).invoke(classAnnotation);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}

		return fileName;
	}

	public String getClassAnnotationValue(String attributeName) {
		String fileName = null;

		Annotation annotation = containerClass.getAnnotation(CLASS_SAVE_TO);
		if (annotation != null) {
			try {
				fileName = (String) annotation.annotationType().getMethod(attributeName).invoke(annotation);
			} catch (Exception ex) {
			}
		}

		return fileName;
	}

}
