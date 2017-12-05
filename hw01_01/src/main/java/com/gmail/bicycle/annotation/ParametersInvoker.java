package com.gmail.bicycle.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class {@code ParametersInvoker} is the class for Invoking methods
 * which are annotated by any {@code Annotation} and gaining results
 * of these invocations
 * @see java.lang.annotation.Annotation
 * @author Devepre
 *
 */
public class ParametersInvoker {
	private Object instance;
	private Class<?> inputClass;
	private Class<? extends Annotation> annotation;

	public ParametersInvoker() {
		super();
	}

	/**
	 * @param instance		instance of {@code Object} which special annotated methods should invoke 
	 * @param annotation	describes {@code Class<? extends Annotation>} marked methods by will invoke    
	 */
	public ParametersInvoker(Object instance, Class<? extends Annotation> annotation) {
		super();
		this.instance = instance;
		this.inputClass = instance.getClass();
		this.annotation = annotation;
	}

	/**
	 * @return	{@code List<Object>} of invoking annotated method results
	 */
	public List<Object> getResults() {
		List<Method> annotatedMethods = getAnnotatedMethods(this.inputClass, this.annotation);
		if (annotatedMethods.size() == 0) {
			throw new IllegalArgumentException("can't find methods to invoke");
		}

		List<Object> result = invokeAll(annotatedMethods);

		return result;
	}

	private List<Method> getAnnotatedMethods(Class<?> inputClass, Class<? extends Annotation> findAnnotationClass) {
		Method[] methods = inputClass.getDeclaredMethods();

		List<Method> result = Arrays.stream(methods)
				.filter(method -> method.isAnnotationPresent(findAnnotationClass))
				.collect(Collectors.toList());

		return result;
	}

	private List<Object> invokeAll(List<Method> annotatedMethods) {
		List<Object> result = new ArrayList<>();
		for (Method method : annotatedMethods) {
			Object[] args = getMethodArguments(method);
			try {
				result.add(method.invoke(this.instance, args));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new IllegalArgumentException("can't invoke method " + e.getMessage());
			}

		}
		return result;
	}

	private Object[] getMethodArguments(Method method) {
		Object[] result = null;

		Class<?> annotationClass = (Class<?>) getFieldValue(method, "type");
		if (annotationClass == this.annotation) {
			@SuppressWarnings("unchecked")
			Map<String, Object> values = (LinkedHashMap<String, Object>) getFieldValue(method, "memberValues");

			result = values.entrySet().stream()
					.map(Map.Entry::getValue)
					.toArray();
		}

		return result;
	}

	private Object getFieldValue(Method method, String fieldName) {
		Object result = null;

		Annotation[] methodAnnotations = method.getDeclaredAnnotations();
		for (Annotation annotation : methodAnnotations) {
			Class<? extends Annotation> annotationClass = annotation.annotationType();
			if (annotationClass == this.annotation) {
				InvocationHandler invHandler = Proxy.getInvocationHandler(annotation);

				try {
					Class<?> clazz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
					Field[] annotationHandlerFields = clazz.getDeclaredFields();

					for (Field field : annotationHandlerFields) {
						if (field.getName().equals(fieldName)) {
							field.setAccessible(true);
							result = field.get(invHandler);
							break;
						}
					}
				} catch (ClassNotFoundException | IllegalArgumentException | IllegalAccessException e) {
					throw new IllegalArgumentException("can't invoke method " + e.getMessage());
				}
			}
		}

		return result;
	}

}
