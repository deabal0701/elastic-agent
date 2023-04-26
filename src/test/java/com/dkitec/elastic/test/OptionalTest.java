package com.dkitec.elastic.test;

import java.util.Optional;

public class OptionalTest {

	public static void main(String[] args) {
		Optional<String> fullName = Optional.of("John Doe");
		
		System.out.println("Full name is set: " + fullName.isPresent()); // true
		System.out.println("Full name: " + fullName.get()); // John Doe

		Optional<String> emptyName = Optional.empty();
		System.out.println("Empty name is set: " + emptyName.isPresent()); // false

		String name = (String) Optional.ofNullable(null).orElse("Default Name");
		System.out.println("Nullable name: " + name); // Default Name
	}
}
