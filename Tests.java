package kata;

public class Tests {

	public static void main(String[] args) {
		System.out.println("Beginning tests for word search kata...\n");

		String wordSearchFile = null;
		WordSearch example = new WordSearch();

		System.out.println("Checking for input file on command line...");
		try {
			if (args[0] != null) {
				System.out.println("\tPASSED...Input file on command line\n");
				wordSearchFile = args[0];
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("\tFAILED...Problem with input file\n");
			return;
		}

		System.out.println("Checking that input is properly formatted...");
		boolean outcome = example.loadWordSearch(args[0]);
		if (outcome == true)
			System.out.println("\tPASSED...word search file is properly formatted\n");
		else {
			System.out.println("\tFAILED...word search file was not properly formatted\n");
			return;
		}

		System.out.println("Checking that input was properly loaded...");
		if (example.getWordSearchContents() != null) {
			System.out.println("\tPASSED...word search contents loaded\n");
		}
		else {
			System.out.println("\tFAILED...word search has no contents\n");
			return;
		}

		System.out.println("Checking for list of words to search for...");
		if (example.getSearchWords() == null) {
			System.out.println("\tFAILED...no search words\n");
			return;
		}
		else
			System.out.println("\tPASSED...search words detected\n");

		System.out.println("Checking for word search grid...");
		if (example.getWordSearchGrid() == null) {
			System.out.println("\tFAILED...no word search grid\n");
			return;
		}
		else
			System.out.println("\tPASSED...word search grid found\n");

		System.out.println("Checking if all words were found in word search...");
		example.findWordsInGrid();
		if (example.hasAllAnswers())
			System.out.println("\tPASSED...all words found in word search\n");
		else {
			System.out.println("\tFAILED...not all words were found in word search (non-fatal error)\n");
		}

		System.out.println("Comparing answers with expected results (only works with known files)...");
		String [] wordPositions = example.getSolutions();
		String [] expected = new String[wordPositions.length];
		boolean knownFile = false;

		if (wordSearchFile.equals("kata/input0.txt")) {
			expected[0] = "(0,1),(1,1),(2,1)";
			expected[1] = "(0,0),(1,0),(2,0)";
			expected[2] = "(0,2),(1,2),(2,2)";
			knownFile = true;
		}
		if (wordSearchFile.equals("kata/input1.txt")) {
			expected[0] = "(0,6),(0,7),(0,8),(0,9),(0,10)";
			expected[1] = "(5,9),(5,8),(5,7),(5,6)";
			expected[2] = "(4,7),(3,7),(2,7),(1,7)";
			expected[3] = "(0,5),(1,5),(2,5),(3,5),(4,5),(5,5)";
			expected[4] = "(2,1),(3,2),(4,3),(5,4),(6,5)";
			expected[5] = "(3,3),(2,2),(1,1),(0,0)";
			expected[6] = "(4,0),(3,1),(2,2),(1,3),(0,4)";
			knownFile = true;
		}

		if (knownFile == true) {
			boolean wordsFoundAsExpected = true;
			for (int i = 0; i < wordPositions.length; i++) {
				if (expected[i].equals(wordPositions[i]) != true) {
					System.out.println("\tFAILED...word " + i + " not found in correct position\n");
					wordsFoundAsExpected = false;
				}
			}
			if (wordsFoundAsExpected == true)
				System.out.println("\tPASSED...all words found in correct position\n");
			else
				return;
		}
		else {
				System.out.println("\tN/A...unknown file, no expected answers to compare with\n");
		}

		System.out.println("ALL TESTS COMPLETE...now printing final output");
		System.out.println("************************************************\n");
		String answers = example.getAnswers();
		System.out.println(answers);

	}//END OF MAIN METHOD
}//END OF CLASS
