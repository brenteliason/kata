package kata;

public class Tests {

	public static void main(String[] args) {
		System.out.println("Beginning tests for word search kata...\n");

		WordSearch example = new WordSearch();

		//test that input included on command line
		System.out.println("Checking for input file on command line...");
		try {
			if (args[0] != null) {
				System.out.println("\tPASSED...Input file on command line\n");

				//test that input was loaded into WordSearch object
				System.out.println("Checking that input was properly loaded");
				example.loadWordSearch(args[0]);
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
		//System.out.println("Checking that input is in acceptable format...");

		//test that first line of input file has been converted to array of Strings
		System.out.println("Checking for list of words to search for...");
		if (example.getSearchWords() == null)
			System.out.println("\tFAILED...no search words\n");
		else
			System.out.println("\tPASSED...search words detected\n");

		System.out.println("Checking for word search grid...");
		if (example.getWordSearchGrid() == null)
			System.out.println("\tFAILED...no word search grid\n");
		else
			System.out.println("\tPASSED...word search grid found\n");

		//check for output
		System.out.println("Checking that output is present...");

		//check formatting of output
		System.out.println("Checking format of output...");

		//check output results with expected results
		System.out.println("Comparing output with expected results...");

	}

}
