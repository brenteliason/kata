package kata;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
//KATA INSTRUCTIONS SAY GRID WILL ALWAYS BE SQUARE SO ROWS WILL ALWAYS EQUAL COLUMNS
//INSTRUCTIONS ALSO SAY you can assume all words will be present in the grid (and only once?)
public class WordSearch {
	String inputFileContents = null;
	ArrayList<String> wordSearchLines = new ArrayList<String>();
	String [] searchWords = null;
	String [][] wordSearchGrid = null;
	String [] solutions = null;//stores locations of search words when found
	int rows = 0;//starting from top left equals number of rows
	int columns = 0;//starting from top left equals number of columns

	WordSearch() {

	}

	WordSearch(String file) {
		loadWordSearch(file);
		findWordsInGrid();
	}

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
					for (int i = 0; i < searchWords.length; i++)
					{
							if (searchWords[i].length() < 2) {
								System.out.println("IMPROPERLY FORMATTED FILE, not all search words are at least two letters");
								return false;
							}
					}

					if (searchWords.length > 0)//
						solutions = new String[searchWords.length];//sets solutions array to be same length as searchWords array

					for (int i = 0; i < solutions.length; i++)
						solutions[i] = "";
				}
				else {//add line of word search contents from file to word search grid data structure

					wordSearchLines.add(temp);
					String [] lineToLetters = temp.split(",");
					if (lineCount == 1) {
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

					for (int c = 0; c < columns; c++) {
						wordSearchGrid[c][lineCount - 1] = lineToLetters[c];
					}

				}//END OF ELSE - NOT FIRST LINE with words
				lineCount++;
			}//END OF WHILE LOOP

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

	public boolean findWordsInGrid() {//returns true if all words are found in grid
		for (int c = 0; c < columns; c++) {
			for (int r = 0; r < rows; r++) {
				for (int w = 0; w < searchWords.length; w++) {
					if (searchWords[w].startsWith(wordSearchGrid[c][r])) {//letter in grid matches first letter of search word, start recursive search
						String query = searchWords[w].substring(0,1);
						searchForFullWord(c,r,w,query);
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

	public boolean searchForFullWord(int column, int row, int w, String prefix) {
		int delta = searchWords[w].length() - 1;//

		if (row - delta >= 0) {
			//check for word going up
			String compareWith = "";
			for (int r = row; r >= (row - delta); r--) {
					compareWith += wordSearchGrid[column][r];
			}

			if (searchWords[w].equals(compareWith)) {
				String matchPosition = "";
				for (int i = 0; i < compareWith.length(); i++) {
					matchPosition += "(" + (column) + "," + (row-i) + ")";
					if (i != (compareWith.length() - 1))
						matchPosition += ",";
				}
				if (solutions[w] == "")
					solutions[w] = matchPosition;//set indexed solution to equal final output for printing later and comparison in tests
				else
					solutions[w] += "; " + matchPosition;
			}
		}

		if (column + delta < columns && row - delta >= 0) {
			//check for word up-right

			String compareWith = "";
			for (int c = column, r = row; c <= (column + delta) && r >= (row - delta); c++, r--) {
					compareWith += wordSearchGrid[c][r];
			}

			if (searchWords[w].equals(compareWith)) {
				String matchPosition = "";
				for (int i = 0; i < compareWith.length(); i++) {
					matchPosition += "(" + (column+i) + "," + (row-i) + ")";
					if (i != (compareWith.length() - 1))
						matchPosition += ",";
				}
				if (solutions[w] == "")
					solutions[w] = matchPosition;//set indexed solution to equal final output for printing later and comparison in tests
				else
					solutions[w] += "; " + matchPosition;
			}
		}

		if (column + delta < columns) {
			//check for word going right

			String compareWith = "";
			for (int c = column; c <= (column + delta); c++) {
					compareWith += wordSearchGrid[c][row];
			}

			if (searchWords[w].equals(compareWith)) {
				String matchPosition = "";
				for (int i = 0; i < compareWith.length(); i++) {
					matchPosition += "(" + (column+i) + "," + row + ")";
					if (i != (compareWith.length() - 1))
						matchPosition += ",";
				}
				if (solutions[w] == "")
					solutions[w] = matchPosition;//set indexed solution to equal final output for printing later and comparison in tests
				else
					solutions[w] += "; " + matchPosition;
			}
		}


		if (column + delta < columns && row + delta < rows) {
			//check for word down-right

			String compareWith = "";
			for (int c = column, r = row; c <= (column + delta) && r <= (row + delta); c++, r++) {
					compareWith += wordSearchGrid[c][r];
			}

			if (searchWords[w].equals(compareWith)) {
				String matchPosition = "";
				for (int i = 0; i < compareWith.length(); i++) {
					matchPosition += "(" + (column+i) + "," + (row+i) + ")";
					if (i != (compareWith.length() - 1))
						matchPosition += ",";
				}
				if (solutions[w] == "")
					solutions[w] = matchPosition;//set indexed solution to equal final output for printing later and comparison in tests
				else
					solutions[w] += "; " + matchPosition;
			}
		}

		if (row + delta < rows) {
			//check for word going down

			String compareWith = "";
			for (int r = row; r <= (row + delta); r++) {
					compareWith += wordSearchGrid[column][r];
			}

			if (searchWords[w].equals(compareWith)) {
				String matchPosition = "";
				for (int i = 0; i < compareWith.length(); i++) {
					matchPosition += "(" + column + "," + (row+i) + ")";
					if (i != (compareWith.length() - 1))
						matchPosition += ",";
				}
				if (solutions[w] == "")
					solutions[w] = matchPosition;//set indexed solution to equal final output for printing later and comparison in tests
				else
					solutions[w] += "; " + matchPosition;
			}
		}

		if (column - delta >= 0 && row + delta < rows) {
			//check for word down-left

			String compareWith = "";
			for (int c = column, r = row; c >= (column - delta) && r <= (row + delta); c--, r++) {
					compareWith += wordSearchGrid[c][r];
			}

			if (searchWords[w].equals(compareWith)) {
				String matchPosition = "";
				for (int i = 0; i < compareWith.length(); i++) {
					matchPosition += "(" + (column-i) + "," + (row+i) + ")";
					if (i != (compareWith.length() - 1))
						matchPosition += ",";
				}
				if (solutions[w] == "")
					solutions[w] = matchPosition;//set indexed solution to equal final output for printing later and comparison in tests
				else
					solutions[w] += "; " + matchPosition;
			}
		}


		if (column - delta >= 0) {
			//check for word left

			String compareWith = "";
			for (int c = column; c >= (column - delta); c--) {
					compareWith += wordSearchGrid[c][row];
			}

			if (searchWords[w].equals(compareWith)) {
				String matchPosition = "";
				for (int i = 0; i < compareWith.length(); i++) {
					matchPosition += "(" + (column-i) + "," + row + ")";
					if (i != (compareWith.length() - 1))
						matchPosition += ",";
				}
				if (solutions[w] == "")
					solutions[w] = matchPosition;//set indexed solution to equal final output for printing later and comparison in tests
				else
					solutions[w] += "; " + matchPosition;
			}
		}


		if (row - delta >= 0 && column - delta >= 0) {
			//check for word up-left

			String compareWith = "";
			for (int c = column, r = row; c >= (column - delta) && r >= (row - delta); c--, r--) {
					compareWith += wordSearchGrid[c][r];
			}

			if (searchWords[w].equals(compareWith)) {
				String matchPosition = "";
				for (int i = 0; i < compareWith.length(); i++) {
					matchPosition += "(" + (column-i) + "," + (row-i) + ")";
					if (i != (compareWith.length() - 1))
						matchPosition += ",";
				}
				if (solutions[w] == "")
					solutions[w] = matchPosition;//set indexed solution to equal final output for printing later and comparison in tests
				else
					solutions[w] += "; " + matchPosition;
			}
		}

		return false;
	}

	public String getWordSearchContents() {
		return inputFileContents;
	}

	public String [] getSearchWords() {
		return searchWords;
	}

	public String [][] getWordSearchGrid() {
		return wordSearchGrid;
	}

	public String [] getSolutions () {
			return solutions;
	}

	public String getAnswers () {
		String answers = "";
		for (int i = 0; i < solutions.length; i++) {
			answers += searchWords[i] + ": " + solutions[i] + "\n";
		}
		return answers;
	}

	public boolean hasAllAnswers () {
		for (int i = 0; i < solutions.length; i++) {
			if (solutions[i] == "")
				return false;
		}
		return true;
	}
}
