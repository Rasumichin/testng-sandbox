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

public class DynamicJsonTestDataProvider {

	@DataProvider(name = "chaining-json-test-values")
	public static Iterator<Object[]> provideChainingTestValues() {
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
	 */
	private static Map<?, ?> readTestValuesFrom(String aFileName) {
		ClassLoader classloader = DynamicJsonTestDataProvider.class.getClassLoader();
		InputStream inputStream = classloader.getResourceAsStream(aFileName);
		Reader reader = new InputStreamReader(inputStream);

		Gson gson = new Gson();
		Map<?, ?> map = gson.fromJson(reader, Map.class);
		try {
			inputStream.close();
			reader.close();
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		}

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
