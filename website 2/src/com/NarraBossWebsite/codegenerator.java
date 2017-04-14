package com.NarraBossWebsite;

import java.util.Random;

public class codegenerator {

	public static String randomWord() {
		Random random = new Random();
		StringBuilder word = new StringBuilder(5);
		for (int i = 0; i < 5; i++) {
			word.append((char) ('a' + random.nextInt(26)));
		}
		System.out.println(word.toString());
		return word.toString();
	}

}
