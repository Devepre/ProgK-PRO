package com.gmail.bicycle.api;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLWorker {
	private Class<?> classToLoad;

	public XMLWorker() {
		super();
	}

	public XMLWorker(Class<?> classToLoad) {
		super();
		this.classToLoad = classToLoad;
	}

	public Object loadFromFile(File file) {
		Object result = createEmptyObject();

		try {
			JAXBContext context = JAXBContext.newInstance(classToLoad);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			result = unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Can't load XML correctly");
		}

		return result;
	}
	
	public Object saveToFile(Object obj, File file) {
		Object result = createEmptyObject();

		try {
			JAXBContext context = JAXBContext.newInstance(classToLoad);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(obj, file);
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Can't save XML correctly");
		}

		return result;
	}

	private Object createEmptyObject() {
		Object obj = null;
		try {
			Constructor<?> constructor = this.classToLoad.getConstructor((Class<?>[])null);
			obj = constructor.newInstance((Object[]) null);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
			throw new IllegalArgumentException("Can't create object of Class " + this.classToLoad);
		}
		return obj;
	}

}
