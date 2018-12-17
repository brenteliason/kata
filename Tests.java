package kata;

public class Tests {

	public static void main(String[] args) {
		System.out.println("Beginning tests for word search kata");



		//test that input included on command line
		System.out.println("Checking for input file on command line...");
		try {
			if (args[0] != null)
				System.out.println("\tPAsSED...Input file on command line");
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("\tFAILED...No input file on command line");
		}


		//test that input was loaded into WordSearch object
		System.out.println("Checking that input was properly loaded");
		WordSearch example = new WordSearch(args[0]);

		//test that input is properly formatted
		System.out.println("Checking that input is in acceptable format");

		//check for output
		System.out.println("Checking that output is present");

		//check formatting of output
		System.out.println("Checking format of output");

		//check output results with expected results
		System.out.println("Comparing output with expected results");

	}

}
