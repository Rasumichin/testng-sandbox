package de.jsmithy.testng.sandbox.dataprovider;

import org.testng.annotations.DataProvider;

/**
 * A simple DataProvider class conforming to TestNG.
 * 
 * Refer to: https://testng.org/doc/documentation-main.html#parameters-dataproviders
 * 
 * @author Erik Lotz
 * 
 */
public class StaticTestDataProvider {

	/**
	 * Provides a simple Object structure used for test parameterization. The static structure is
	 * <code>{ {"CMO", "ELO"}, {"AGA", "RBRi"} }</code>.
	 * 
	 */
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
