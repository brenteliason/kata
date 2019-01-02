package kata;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
/*ASSUMPTIONS - based on kata instructions: all search words are at least 2 letters long,
all search words will be present (only once?), # of rows = # of columns*/
public class WordSearch {
	String inputFileContents = null;//stores contents of whole input file in single string for testing purposes
	//ArrayList<String> wordSearchLines = new ArrayList<String>();//temporarily stores word search grid as list of lines
	String [] searchWords = null;//stores words to be searched for found on first line of properly formatted file
	String [][] wordSearchGrid = null;//stores letters in word search grid as strings for ease of comparison with search words stored as Strings
	String [] solutions = null;//stores locations of search words when found
	int rows = 0;//starting from top left equals number of rows
	int columns = 0;//starting from top left equals number of columns


	WordSearch() {

	}

	//alternative constructor that takes input file directly
	WordSearch(String file) {
		boolean loaded = loadWordSearch(file);
		if (loaded == true)
			findWordsInGrid();
	}

	/*takes input file and if properly formatted creates:
	1. list of words to search for (first line of file) and 2. grid of letters (all remaining lines);
	RETURNS true if 1 and 2 are created, RETURNS false is the file is not properly formatted preventing this*/
	public boolean loadWordSearch(String file) {
		try {
			Scanner scan = new Scanner(new File(file));
			int lineCount = 0;
			inputFileContents = "";
			while (scan.hasNextLine() == true) {
				String temp = "";
				temp = scan.nextLine();//stores the raw text from the next line of the input file
				inputFileContents += "\n" + temp;

				if (lineCount == 0) {//FIRST LINE is the words to search for, needs different treatment than other lines that form the puzzle itself
					searchWords = temp.split(",");
					for (int i = 0; i < searchWords.length; i++)//ENSURES all words are at least 2 letters long
					{
							if (searchWords[i].length() < 2) {
								System.out.println("IMPROPERLY FORMATTED FILE, not all search words are at least two letters");
								return false;
							}
					}

					if (searchWords.length > 0)
						solutions = new String[searchWords.length];//sets solutions array to be same length as searchWords array

					for (int i = 0; i < solutions.length; i++)
						solutions[i] = "";
				}
				else {//NOT FIRST LINE - process line of letters before adding to word search grid

					//wordSearchLines.add(temp);
					String [] lineToLetters = temp.split(",");
					if (lineCount == 1) {//FIRST LINE OF GRID - if properly formatted, # of letters will be same for all rows, and # of columns will = # of rows
						columns = lineToLetters.length;
						rows = columns;
						wordSearchGrid = new String[columns][rows];
					}

					if (lineToLetters.length != columns) {
						System.out.println("IMPROPERLY FORMATTED FILE, word search rows don't have same number of letters");
						return false;
					}

					if (lineCount > columns) {
						System.out.println("IMPROPERLY FORMATTED FILE, more rows than columns");
						return false;
					}

					for (int c = 0; c < columns; c++) {//adds letters from new line to corresponding part of word search grid
						wordSearchGrid[c][lineCount - 1] = lineToLetters[c];
					}

				}//END OF ELSE statement that handles lines with letters (i.e. NOT FIRST LINE)
				lineCount++;
			}//END OF WHILE LOOP that runs as long as there's another line in file

			if (columns != (lineCount - 1)) {
				System.out.println("IMPROPERLY FORMATTED FILE, number of word search rows does not equal number of columns");
				return false;
			}

			return true;
		}
		catch (FileNotFoundException e) {
			System.out.println("***FILE NOT FOUND***");
			return false;
		}
	}

	/*LOOPS through each letter in grid and checks if a search word starts with the letter, if so, then it calls searchForFullWord method
	RETURNS true if all words are found in grid, RETURNS false if one ore more words are not found*/
	public boolean findWordsInGrid() {
		for (int c = 0; c < columns; c++) {
			for (int r = 0; r < rows; r++) {
				for (int w = 0; w < searchWords.length; w++) {
					if (searchWords[w].startsWith(wordSearchGrid[c][r])) {//IF letter matches first letter of search word, call searchForFullWord
						searchForFullWord(c,r,w);
					}
				}
			}
		}
		for (int i = 0; i < solutions.length; i++) {
			if (solutions[i] == "")
				return false;//one of the words was not found in the search grid
		}
		return true;//all of the words were found in the grid
	}

	/*WHEN findWordsInGrid method finds that a letter in the grid matches the first letter in a search word, it calls this method,
	which takes the position of the letter in the grid and the index of the matching search word,
	and then checks if the full search is present in any direction starting going up and then moving clockwise
	(i.e. up-right, right, down-right, down, down-left, left, up-left)
	- In each direction, the algorithm checks if there is space for the word in that direction given the word's length, if so,
	then it checks for the full word in that direction
	- At the highest level, the method contains eight if statements that check for the word in each direction,
	IF one were to assume that a single word is only found in the word search once or that only one position needs to be found,
	then one could modify the method by replacing the if-else statement at the bottom of each of the 8 blocks (checking for previous solution)
	with a return statement*/
	public void searchForFullWord(int column, int row, int w) {
		int delta = searchWords[w].length() - 1;//number of positions to move to check for full word (first letter has already been accounted for)

		/*checks for word going up - this block has explanatory comments, but the same pattern is repeated in each block
		 with slight modifications based on the corresponding direction*/
		if (row - delta >= 0) {//checks if there is space for the word going up
			String compareWith = "";//stores number of letters going up equal to the length of the flagged words
			for (int r = row; r >= (row - delta); r--)//add appropriate number of letters going up to comparison String
					compareWith += wordSearchGrid[column][r];

			/*if the search word is the same as the comparison String, then a match has been found,
			and the position of the match needs to be recorded as a series of (x,y) coordinates in the solutions array*/
			if (searchWords[w].equals(compareWith)) {
				String matchPosition = "";
				for (int i = 0; i < compareWith.length(); i++) {
					matchPosition += "(" + (column) + "," + (row-i) + ")";
					if (i != (compareWith.length() - 1))
						matchPosition += ",";
				}

				/*IF we can assume that a word is only present once in a word search, or only needs to be found once,
				then we can replace this if-else statement with a simple return statement*/
				if (solutions[w] == "")
					solutions[w] = matchPosition;//set indexed solution to equal final output for printing later and comparison in tests
				else
					solutions[w] += "; " + matchPosition;
			}
		}

		//checks for word going up-right
		if (column + delta < columns && row - delta >= 0) {
			String compareWith = "";
			for (int c = column, r = row; c <= (column + delta) && r >= (row - delta); c++, r--)
					compareWith += wordSearchGrid[c][r];

			if (searchWords[w].equals(compareWith)) {
				String matchPosition = "";
				for (int i = 0; i < compareWith.length(); i++) {
					matchPosition += "(" + (column+i) + "," + (row-i) + ")";
					if (i != (compareWith.length() - 1))
						matchPosition += ",";
				}
				if (solutions[w] == "")
					solutions[w] = matchPosition;
				else
					solutions[w] += "; " + matchPosition;
			}
		}

		//checks for word going right
		if (column + delta < columns) {
			String compareWith = "";
			for (int c = column; c <= (column + delta); c++)
					compareWith += wordSearchGrid[c][row];

			if (searchWords[w].equals(compareWith)) {
				String matchPosition = "";
				for (int i = 0; i < compareWith.length(); i++) {
					matchPosition += "(" + (column+i) + "," + row + ")";
					if (i != (compareWith.length() - 1))
						matchPosition += ",";
				}
				if (solutions[w] == "")
					solutions[w] = matchPosition;
				else
					solutions[w] += "; " + matchPosition;
			}
		}

		//checks for word going down-right
		if (column + delta < columns && row + delta < rows) {
			String compareWith = "";
			for (int c = column, r = row; c <= (column + delta) && r <= (row + delta); c++, r++)
					compareWith += wordSearchGrid[c][r];

			if (searchWords[w].equals(compareWith)) {
				String matchPosition = "";
				for (int i = 0; i < compareWith.length(); i++) {
					matchPosition += "(" + (column+i) + "," + (row+i) + ")";
					if (i != (compareWith.length() - 1))
						matchPosition += ",";
				}
				if (solutions[w] == "")
					solutions[w] = matchPosition;
				else
					solutions[w] += "; " + matchPosition;
			}
		}

		//checks for word going down
		if (row + delta < rows) {
			String compareWith = "";
			for (int r = row; r <= (row + delta); r++)
					compareWith += wordSearchGrid[column][r];

			if (searchWords[w].equals(compareWith)) {
				String matchPosition = "";
				for (int i = 0; i < compareWith.length(); i++) {
					matchPosition += "(" + column + "," + (row+i) + ")";
					if (i != (compareWith.length() - 1))
						matchPosition += ",";
				}
				if (solutions[w] == "")
					solutions[w] = matchPosition;
				else
					solutions[w] += "; " + matchPosition;
			}
		}

		//checks for word going down-left
		if (column - delta >= 0 && row + delta < rows) {
			String compareWith = "";
			for (int c = column, r = row; c >= (column - delta) && r <= (row + delta); c--, r++)
					compareWith += wordSearchGrid[c][r];

			if (searchWords[w].equals(compareWith)) {
				String matchPosition = "";
				for (int i = 0; i < compareWith.length(); i++) {
					matchPosition += "(" + (column-i) + "," + (row+i) + ")";
					if (i != (compareWith.length() - 1))
						matchPosition += ",";
				}
				if (solutions[w] == "")
					solutions[w] = matchPosition;
				else
					solutions[w] += "; " + matchPosition;
			}
		}

		//checks for word going left
		if (column - delta >= 0) {
			String compareWith = "";
			for (int c = column; c >= (column - delta); c--)
					compareWith += wordSearchGrid[c][row];

			if (searchWords[w].equals(compareWith)) {
				String matchPosition = "";
				for (int i = 0; i < compareWith.length(); i++) {
					matchPosition += "(" + (column-i) + "," + row + ")";
					if (i != (compareWith.length() - 1))
						matchPosition += ",";
				}
				if (solutions[w] == "")
					solutions[w] = matchPosition;
				else
					solutions[w] += "; " + matchPosition;
			}
		}

		//checks for word going up-left
		if (row - delta >= 0 && column - delta >= 0) {
			String compareWith = "";
			for (int c = column, r = row; c >= (column - delta) && r >= (row - delta); c--, r--)
					compareWith += wordSearchGrid[c][r];

			if (searchWords[w].equals(compareWith)) {
				String matchPosition = "";
				for (int i = 0; i < compareWith.length(); i++) {
					matchPosition += "(" + (column-i) + "," + (row-i) + ")";
					if (i != (compareWith.length() - 1))
						matchPosition += ",";
				}
				if (solutions[w] == "")
					solutions[w] = matchPosition;
				else
					solutions[w] += "; " + matchPosition;
			}
		}
	}//END of searchForFullWord method

	//returns raw text of file, used in testing
	public String getWordSearchContents() {
		return inputFileContents;
	}

	//returns list of words to be searched for
	public String [] getSearchWords() {
		return searchWords;
	}

	//returns 2d array storing letters in word search
	public String [][] getWordSearchGrid() {
		return wordSearchGrid;
	}

	//returns array listing positions of where the search words were found in the grid
	public String [] getSolutions () {
			return solutions;
	}

	//RETURNS true if all words were found in the word search
	public boolean hasAllAnswers () {
		for (int i = 0; i < solutions.length; i++) {
			if (solutions[i] == "")
				return false;
		}
		return true;
	}

	/*returns String that lists search words along with corresponding positions
	in format specified by the kata instructions*/
	public String getAnswers () {
		String answers = "";
		for (int i = 0; i < solutions.length; i++) {
			answers += searchWords[i] + ": " + solutions[i] + "\n";
		}
		return answers;
	}


}
