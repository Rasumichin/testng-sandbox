package de.jsmithy.testng.sandbox;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class ChainingTest {
	private Chaining sut;

	@Test
	public void testChain_both_values_are_valid() {
		sut = new Chaining();
		String expected = "Hello, CMO and ELO";

		String actual = sut.concat("CMO", "ELO");

		assertEquals(actual, expected, "Concatenated values are not correct!");
	}
}
