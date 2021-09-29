package de.jsmithy.testng.sandbox;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import de.jsmithy.testng.sandbox.dataprovider.StaticTestDataProvider;

public class ChainingTest {
	private Chaining sut;

	@Test
	public void testChain_both_values_are_valid() {
		sut = new Chaining();
		String expected = "Hello, CMO and ELO";

		String actual = sut.concat("CMO", "ELO");

		assertEquals(actual, expected, "Concatenated values are not correct!");
	}

	@Test(dataProvider = "chaining-test-values", dataProviderClass = StaticTestDataProvider.class)
	public void testChain_with_static_data_provider(String aFirstElement, String aSecondElement) {
		sut = new Chaining();
		String expected = "Hello, " + aFirstElement + " and " + aSecondElement;

		String actual = sut.concat(aFirstElement, aSecondElement);

		assertEquals(actual, expected, "Concatenated values are not correct!");
	}
}
