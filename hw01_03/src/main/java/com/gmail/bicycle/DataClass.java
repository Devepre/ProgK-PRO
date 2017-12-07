package com.gmail.bicycle;

import java.io.Serializable;

import com.gmail.bicycle.annotation.Save;

public class DataClass implements Serializable {
	private static final long serialVersionUID = 1L;
	@Save
	private String name;
	private double value;
	@Save
	private int digit;
	@Save
	private Object obj;
	
	public DataClass() {
		super();
	}

	public DataClass(String name, double value, int digit, Object obj) {
		super();
		this.name = name;
		this.value = value;
		this.digit = digit;
		this.obj = obj;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getDigit() {
		return digit;
	}

	public void setDigit(int digit) {
		this.digit = digit;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	@Override
	public String toString() {
		return "DataClass [name=" + name + ", value=" + value + ", digit=" + digit + ", obj=" + obj + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + digit;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((obj == null) ? 0 : obj.hashCode());
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
		DataClass other = (DataClass) obj;
		if (digit != other.digit)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (this.obj == null) {
			if (other.obj != null)
				return false;
		} else if (!this.obj.equals(other.obj))
			return false;
		return true;
	}

}
