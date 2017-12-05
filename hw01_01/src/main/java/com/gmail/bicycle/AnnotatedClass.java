package com.gmail.bicycle;

public class AnnotatedClass {
	@Deprecated
	@ComputeValues(paramOne = 10, paramTwo = 7)
	public int calculateRemainder(int digitA, int digitB) {
		return digitA % digitB;
	}

	@ComputeValues(paramOne = 5, paramTwo = 3)
	public Double calculateSum(int digitA, int digitB) {
		Double result = Double.valueOf(digitA);
		result += digitB;
		result += Math.PI;
		return result;
	}

	public int calculateMultiply(int digitA, int digitB) {
		return digitA * digitB;
	}
}
