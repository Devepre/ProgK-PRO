package com.gmail.bicycle.api;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.gmail.bicycle.annotation.SaveMethod;

public class SaverWrapper {
	static final Class<? extends Annotation> CLASS_SAVE_METHOD = SaveMethod.class;

	public SaverWrapper() {
		super();
	}

	public void run(TextContainer textContainer) {
		new Thread(() -> {
			Saver saver = new Saver();
			invokeSaves(saver, getMethods(saver), textContainer);
		}).start();

	}

	protected void invokeSaves(Object obj, List<Method> methods, TextContainer textContainer) {
		for (Method method : methods) {
			try {
				method.invoke(obj, textContainer);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	protected List<Method> getMethods(Saver saver) {
		List<Method> result = new ArrayList<>();
		Method[] methods = saver.getClass().getMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(CLASS_SAVE_METHOD)) {
				result.add(method);
			}
		}
		return result;
	}

}
