package de.jsmithy.testng.sandbox.dataprovider;

import org.testng.annotations.DataProvider;

public class StaticTestDataProvider {

	@DataProvider(name = "chaining-test-values")
	public static Object[][] provideChainingTestValues() {
		// @formatter:off

		return new Object[][] {
			{"CMO", "ELO"},
			{"AGA", "RBRi"}
		};

		// @formatter:on
	}
}
