package com.dkitec.elastic.test;

import java.util.function.Predicate;

public class PredicateTest {
	public static void main(String arg[]) {
		
		Predicate<Integer> bCount = new Predicate<Integer>() {
			@Override
			public boolean test(Integer t) {
				return t.equals(50);
			}
		};
		
		Predicate<Integer> bCount2 = (t) -> t.equals(50);
	}
}


 interface MyTest<T>{
	void test();
}