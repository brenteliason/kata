package kata;

public class Driver {

	public static void main(String[] args) {
		System.out.println("Loading word search file...\n");

		String wordSearchFile = null;
		WordSearch example = new WordSearch();

		try {
			if (args[0] != null) {
				wordSearchFile = args[0];

				boolean outcome = example.loadWordSearch(args[0]);
        if (outcome == false) {
					System.out.println("Word search file is not properly formatted\n");
          return;
				}

				if (example.getWordSearchContents() == null) {
					System.out.println("\tWord search was not loaded properly\n");
					return;
				}
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("\tProblem with input file\n");
			return;
		}

		if (example.getSearchWords() == null) {
			System.out.println("\tThere are no words to search for in the file\n");
			return;
		}

		if (example.getWordSearchGrid() == null) {
			System.out.println("\tThere is no word search grid in the file\n");
			return;
		}

		example.findWordsInGrid();
		if (example.hasAllAnswers() == false) {
			System.out.println("Not all words were found in the word search\n");
		}

		String answers = example.getAnswers();
		System.out.println(answers);

	}//END OF MAIN METHOD
}//END OF CLASS
