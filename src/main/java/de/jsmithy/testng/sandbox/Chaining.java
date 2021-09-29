package de.jsmithy.testng.sandbox;

/**
 * A simple class to provide methods to chain something.
 * 
 * @author Erik Lotz
 * 
 */
public class Chaining {

	/**
	 * Simply concatenates the given values to result in a fixed format string:
	 * "Hello, &lt;value of <code>aFirstElement</code>&gt; and &lt;value of <code>aSecondElement</code>&gt;"
	 * 
	 */
	public String concat(String aFirstElement, String aSecondElement) {
		return "Hello, " + aFirstElement + " and " + aSecondElement;
	}

}
