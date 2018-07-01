package com.h2.demo.utilities;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.UUID;

public class RandomObjectFiller {

	private static Random random = new Random();

	public static <T> T createObject(Class<T> clazz) throws Exception {
		T instance = clazz.newInstance();
		for (Field field : clazz.getDeclaredFields()) {
			if (field.getName().equals("serialVersionUID")) {
				continue;
			}
			field.setAccessible(true);
			Object value = getRandomValueForField(field);
			field.set(instance, value);
		}
		return instance;
	}

	private static Object getRandomValueForField(Field field) throws Exception {
		Class<?> type = field.getType();

		if (type.isEnum()) {
			Object[] enumValues = type.getEnumConstants();
			return enumValues[random.nextInt(enumValues.length)];
		} else if (type.equals(Integer.TYPE) || type.equals(Integer.class)) {
			return random.nextInt();
		} else if (type.equals(Long.TYPE) || type.equals(Long.class)) {
			return Math.abs(random.nextLong());
		} else if (type.equals(Double.TYPE) || type.equals(Double.class)) {
			return random.nextDouble();
		} else if (type.equals(Float.TYPE) || type.equals(Float.class)) {
			return random.nextFloat();
		} else if (type.equals(String.class)) {
			return UUID.randomUUID().toString().substring(0, 10);
		} else if (type.equals(BigInteger.class)) {
			return BigInteger.valueOf(random.nextInt());
		} else if (type.equals(Date.class)) {
			return randomDate();
		}
		return createObject(type);
	}

	private static Date randomDate() {
		GregorianCalendar gc = new GregorianCalendar();

		int year = randBetween(1900, 2010);

		gc.set(Calendar.YEAR, year);

		int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));

		gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
		return gc.getTime();
	}

	private static int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}
}
