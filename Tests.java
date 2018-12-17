package kata;

public class Tests {

	public static void main(String[] args) {
		System.out.println("Beginning tests for word search kata...\n");



		//test that input included on command line
		System.out.println("Checking for input file on command line...");
		try {
			if (args[0] != null) {
				System.out.println("\tPASSED...Input file on command line\n");

				//test that input was loaded into WordSearch object
				System.out.println("Checking that input was properly loaded");
				WordSearch example = new WordSearch(args[0]);
				if (example.getWordSearchContents() != null) {
					System.out.println("\tPASSED...word search contents loaded\n");
				}
				else
					System.out.println("\tFAILED...word search has no contents\n");
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("\tFAILED...No input file on command line\n");
		}

		//test that input is properly formatted
		System.out.println("Checking that input is in acceptable format...");

		//check for output
		System.out.println("Checking that output is present...");

		//check formatting of output
		System.out.println("Checking format of output...");

		//check output results with expected results
		System.out.println("Comparing output with expected results...");

	}

}
