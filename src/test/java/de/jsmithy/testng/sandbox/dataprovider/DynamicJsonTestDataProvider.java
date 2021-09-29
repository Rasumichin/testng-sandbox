package de.jsmithy.testng.sandbox.dataprovider;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;

/**
 * A DataProvider class conforming to TestNG that reads the test values from a given JSON file.
 * 
 * Refer to: https://testng.org/doc/documentation-main.html#parameters-dataproviders
 * 
 * @author Erik Lotz
 * 
 */
public class DynamicJsonTestDataProvider {

	/**
	 * Answers an Iterator that provides access to a list of test values which have been read
	 * from a given file, named <code>"./testdata/chaining-testdata.json"</code>.
	 * 
	 * Refer to a snippet of this test file:</br>
	 * <code>
	 * { "concatTestValues": [
	 * 	 { "firstElement":"FCO", "secondElement":"ISI" },
	 *   { "firstElement":"Kent", "secondElement":"Beck"}
	 *   ]
	 * }</code></p>
	 * 
	 * This snippet would lead to an Iterator to a list of <code>Object[]</code> with the elements
	 * <ol>
	 * <li><code>["FCO", "ISI"]</code>
	 * <li><code>["Kent", "Beck"]</code>
	 * </ol>
	 * 
	 */
	@DataProvider(name = "chaining-json-test-values")
	public static Iterator<Object[]> provideChainingTestValues() throws IOException {
		Map<?, ?> jsonDataMap = readTestValuesFrom("./testdata/chaining-testdata.json");

		@SuppressWarnings("unchecked")
		List<Map<String, String>> jsonTestValues = (List<Map<String, String>>) jsonDataMap.get("concatTestValues");

		// @formatter:off

		List<Object[]> testValues = new ArrayList<Object[]>();
		jsonTestValues.stream()
					.map(eachMap -> collectToArray(eachMap.get("firstElement"), eachMap.get("secondElement")))
					.forEach(eachElement -> testValues.add(eachElement));

		// @formatter:on

		return testValues.iterator();
	}

	/**
	 * Reads a JSON structure from a file into a map of dynamic properties and values.
	 *  
	 * Adaption of https://attacomsian.com/blog/gson-read-json-file
	 * 
	 * @param aFileName The name of file to be read. The file has to be visible on the classpath, because
	 * 					it is loaded as a resource.
	 * 
	 * @return A map containing all the properties of the JSON object with the corresponding values. All types
	 * 			are completely dynamic, no binding.
	 * 
	 * @throws IOException In case handling the file operation would fail.
	 * 
	 */
	private static Map<?, ?> readTestValuesFrom(String aFileName) throws IOException {
		ClassLoader classloader = DynamicJsonTestDataProvider.class.getClassLoader();
		InputStream inputStream = classloader.getResourceAsStream(aFileName);
		Reader reader = new InputStreamReader(inputStream);

		Gson gson = new Gson();
		Map<?, ?> map = gson.fromJson(reader, Map.class);

		inputStream.close();
		reader.close();

		return map;
	}

	/**
	 * Takes a variable number of arguments and collects them in an array of Objects.
	 * 
	 * @return A collection of [0..n] arbitrary objects, depending on the number of arguments passed to the method.
	 * 
	 */
	private static Object[] collectToArray(Object... args) {
		List<Object> list = new ArrayList<Object>();
		for (Object eachArgument : args) {
			list.add(eachArgument);
		}

		return list.toArray();
	}
}
