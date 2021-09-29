# testng-sandbox
A simple Java project to play with several concepts of the TestNG test framework.

Currently, the class `ChainingTest` provides these tests:
- `testChain_both_values_are_valid()` - a standard test to assert the equality of two values.
- `testChain_with_static_data_provider(String aFirstElement, String aSecondElement)` - a test which dynamically receives parameters through a dedicated class that provides static test values.  
- `testChain_with_dynamic_data_provider(String aFirstElement, String aSecondElement)` - a test which dynamically receives parameters through a dedicated class that reads test values from a JSON file.
