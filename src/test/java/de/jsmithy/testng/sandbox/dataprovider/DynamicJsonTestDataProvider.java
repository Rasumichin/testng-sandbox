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
	 * Adaption of https://attacomsian.com/blog/gson-read-json-file
	 * 
	 */
	private static Map<?, ?> readTestValuesFrom(String aFileName) {
		Gson gson = new Gson();
		ClassLoader classloader = DynamicJsonTestDataProvider.class.getClassLoader();
		InputStream inputStream = classloader.getResourceAsStream(aFileName);

		Reader reader = new InputStreamReader(inputStream);
		Map<?, ?> map = gson.fromJson(reader, Map.class);
		try {
			inputStream.close();
			reader.close();
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		}

		return map;
	}

	private static Object[] collectToArray(String aFirstElement, String aSecondElement) {
		List<String> list = new ArrayList<String>();
		list.add(aFirstElement);
		list.add(aSecondElement);

		return list.toArray();
	}
}
