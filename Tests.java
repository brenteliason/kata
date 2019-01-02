package kata;

public class Tests {

	public static void main(String[] args) {
		System.out.println("Beginning tests for word search kata...\n");

		String wordSearchFile = null;
		WordSearch example = new WordSearch();

		//test that input included on command line
		System.out.println("Checking for input file on command line...");
		try {
			if (args[0] != null) {
				System.out.println("\tPASSED...Input file on command line\n");
				wordSearchFile = args[0];
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
		example.findWordsInGrid();
		if (example.hasAnswers()) {
			System.out.println("\tPASSED...all words found in word search\n");
		}
		else
			System.out.println("\tFAILED...not all words were found in word search\n");

		//check output results with expected results
		System.out.println("Checking answers with expected results...");

		String [] wordPositions = example.getSolutions();
		String [] expected = new String[wordPositions.length];

		if (wordSearchFile.equals("kata/input1.txt")) {
			expected[0] = "(0,1),(1,1),(2,1)";
			expected[1] = "(0,0),(1,0),(2,0)";
			expected[2] = "(0,2),(1,2),(2,2)";
		}
		if (wordSearchFile.equals("kata/input2.txt")) {
			expected[0] = "(0,6),(0,7),(0,8),(0,9),(0,10)";
			expected[1] = "(5,9),(5,8),(5,7),(5,6)";
			expected[2] = "(4,7),(3,7),(2,7),(1,7)";
			expected[3] = "(0,5),(1,5),(2,5),(3,5),(4,5),(5,5)";
			expected[4] = "(2,1),(3,2),(4,3),(5,4),(6,5)";
			expected[5] = "(3,3),(2,2),(1,1),(0,0)";
			expected[6] = "(4,0),(3,1),(2,2),(1,3),(0,4)";
		}

		boolean wordsFoundAsExpected = true;
		for (int i = 0; i < wordPositions.length; i++) {
			if (expected[i].equals(wordPositions[i]) != true) {
				System.out.println("\tFAILED...word " + i + " not found in correct position\n");
				wordsFoundAsExpected = false;
			}
		}
		if (wordsFoundAsExpected == true)
			System.out.println("\tPASSED...all words found in correct position\n");

		//TESTING OVER - FINAL OUTPUT OF PROGRAM
		System.out.println("ALL TESTS COMPLETE...now printing final output");
		System.out.println("************************************************\n");
		String answers = example.getAnswers();
		System.out.println(answers);

	}

}
