package com.bank.technical;

import java.util.Random;

public class RandomIdGenerator {
	private static Random r = new Random();

	public static int getId() {
		return getRandomNumberInRange(1000, 99999);
	}

	private static int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		return r.nextInt((max - min) + 1) + min;
	}
}
