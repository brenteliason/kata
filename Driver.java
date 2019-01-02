package kata;

public class Driver {

	public static void main(String[] args) {
		System.out.println("Loading word search file...\n");

		String wordSearchFile = null;
		WordSearch puzzle = new WordSearch();

		try {
      /*if input file is present and properly formatted, creates word search object:
       1. lists of words to search for, 2. grid of letters to search in*/
			if (args[0] != null) {
				wordSearchFile = args[0];

				boolean outcome = puzzle.loadWordSearch(args[0]);
        if (outcome == false) {
					System.out.println("Word search file is not properly formatted\n");
          return;
				}

				if (puzzle.getWordSearchContents() == null) {
					System.out.println("\tWord search was not loaded properly\n");
					return;
				}
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("\tProblem with input file\n");
			return;
		}

		if (puzzle.getSearchWords() == null) {
			System.out.println("\tThere are no words to search for in the file\n");
			return;
		}

		if (puzzle.getWordSearchGrid() == null) {
			System.out.println("\tThere is no word search grid in the file\n");
			return;
		}

    /*if word search object has list of words to search for and a grid of letters,
    then find all words in grid */
		puzzle.findWordsInGrid();
		if (puzzle.hasAllAnswers() == false) {
			System.out.println("Not all words were found in the word search\n");
		}

    //prints positions of all words as list of (x,y) coordinates with 0,0 at top-left corner
		String answers = puzzle.getAnswers();
		System.out.println(answers);

	}//END OF MAIN METHOD
}//END OF CLASS
