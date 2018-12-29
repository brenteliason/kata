package kata;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
//KATA INSTRUCTIONS SAY GRID WILL ALWAYS BE SQUARE SO ROWS WILL ALWAYS EQUAL COLUMNS
public class WordSearch {
	String inputFileContents = null;
	ArrayList<String> wordSearchLines = new ArrayList<String>();
	String [] searchWords = null;
	String [][] wordSearchGrid = null;
	int maxRows = 3;
	int maxColumns = 3;
	int rows = 0;//starting from top left equals number of rows
	int columns = 0;//starting from top left equals number of columns
	//String wordSearchFile;
	//need variable to store contents of word search, 2d array?

	WordSearch() {

	}

	WordSearch(String file) {
		//System.out.println("Created WordSearch object w following input: ");
		//System.out.println(file);
		//wordSearchFile = file;
		loadWordSearch(file);
		findWordsInGrid();
	}

	public boolean loadWordSearch(String file) {
		try {
			Scanner scan = new Scanner(new File(file));
			int lineCount = 0;
			inputFileContents = "";
			//wordSearchGrid = new String[maxRows][maxColumns];
			while (scan.hasNextLine() == true) {
				String temp = "";
				temp = scan.nextLine();//stores the raw text from the next line of the input file
				inputFileContents += "\n" + temp;
				if (lineCount == 0) {//FIRST LINE is the words to search for, needs different treatment than other lines that form the puzzle itself
					searchWords = temp.split(",");
				}
				else {//add line of word search contents from file to word search grid data structure
					wordSearchLines.add(temp);
					String [] lineToLetters = temp.split(",");
					if (lineCount == 1) {
						columns = lineToLetters.length;
						rows = columns;
						wordSearchGrid = new String[rows][columns];
					}

					if (lineToLetters.length != columns) {
						System.out.println("IMPROPERLY FORMATTED FILE, word search rows don't have same number of letters");
						return false;
						//SHOULD THROW ERROR or exception to fail test
					}

						for (int c = 0; c < columns; c++) {
							wordSearchGrid[lineCount - 1][c] = lineToLetters[c];
						}
						/*for (int i = 0; i < columnCount; i++) {
							wordSearchGrid[lineCount-1][i] = lineToLetters[i];
						}*/
						//System.out.println("Line " + lineCount + " (converted to String [] and back to String): " + String.join(",",lineToLetters));


				}//END OF ELSE - NOT FIRST LINE with words
				//System.out.println(lineCount + ": " + temp);
				lineCount++;
			}//END OF WHILE LOOP

			if (lineCount != (rows - 1)) {
				System.out.println("IMPROPERLY FORMATTED FILE, number of word search rows does not equal number of columns");
				return false;
			}

			//READ THE WHOLE FILE, now populate 2d array grid
			//String[] wordSearchLinesArray = wordSearchLines.toArray();
			//wordSearchGrid = new String[rowCount][columnCount];
			/*for (int r = 0; r < rowCount; r++) {
				String temp = wordSearchLines.get(r);
				String [] lineToLetters = temp.split(",");
				for (int c = 0; c < columnCount; c++) {
					wordSearchGrid[r][c] = lineToLetters[c];
				}
			}*/
			return true;
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
			return false;
		}
	}

	public boolean findWordsInGrid() {//returns true if all words are found in grid
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				for (int w = 0; w < searchWords.length; w++) {
						//System.out.println("Does " + searchWords[w] + " start with " + wordSearchGrid[i][j]);
					if (searchWords[w].startsWith(wordSearchGrid[r][c])) {//letter in grid matches first letter of search word, start recursive search
						String query = searchWords[w].substring(0,1);
						//System.out.println("Potential find of " + searchWords[w] + " starting at " + i + "," + j);
						searchForFullWord(r,c,w,query);
					}
				}
			}
		}
		return false;
	}

	public boolean searchForFullWord(int row, int column, int w, String prefix) {
		int delta = searchWords[w].length() - 1;//
		//search for full word starting with straight up and then moving clockwise
		System.out.println("Checking for " + searchWords[w] + " starting at " + row + "," + column);
		if (row - delta >= 0) {
			//check for word going up
			//System.out.println("\t...going up");
			String compareWith = "";
			for (int c = column; c >= (column - delta); c--) {
					compareWith += wordSearchGrid[row][c];
					System.out.println("Comparison string now equals: " + compareWith);
			}
			System.out.println("Does " + searchWords[w] + " equal " + compareWith);
			if (searchWords[w] == compareWith) {

				System.out.println("\n\n\t\tMATCH FOUND!!!!\n");
			}
		}
		if (column - delta >= 0 && row + delta <= (rows - 1)) {
			//check for word up-right
			//System.out.println("\t...going up-right");
		}
		if (column + delta <= columns) {
			//check for word going right
			System.out.println("\t...going right");

			String compareWith = "";
			for (int c = column; c <= (column + delta); c++) {
					compareWith += wordSearchGrid[row][c];
					System.out.println("Comparison string now equals: " + compareWith);
			}
			System.out.println("Does \'" + searchWords[w] + "\' equal \'" + compareWith + "\'?");
			if (searchWords[w].equals(compareWith)) {
				System.out.println("\n\n\t\tMATCH FOUND!!!!\n");
				System.out.println(searchWords[w] + " is the same as " + compareWith);
			}
			else {
				System.out.println("\n\tNO MATCH!!!");
			}

		}

		if (column + delta <= (maxColumns - 1) && row + delta <= (maxRows - 1)) {
			//check for word down-right
			//System.out.println("\t...going down-right");
		}
		if (column + delta <= (maxColumns - 1)) {
			//check for word going down
			//System.out.println("\t...going down");
		}
		if (column + delta <= (maxRows - 1) && row - delta >= 0) {
			//check for word down-left
			//System.out.println("\t...going down-left");
		}
		if (row - delta >= 0) {
			//check for word left
			//System.out.println("\t...going left");
		}
		if (row - delta >= 0 && column - delta >= 0) {
			//check for word up-left
			//System.out.println("\t...going up-left");
		}



		return false;
	}

	public String getWordSearchContents() {
		//System.out.println("\tgetWordSearchContents method called, contents are as follows:\n\t" + inputFileContents);
		return inputFileContents;
	}

	public String [] getSearchWords() {
		//System.out.println("\tInside getSearchWords method, printing searchWords array:\n\t "+ String.join(",",searchWords));
		return searchWords;
	}

	public String [][] getWordSearchGrid() {
		/*System.out.println("Printing complete word search grid:");
		for (int i = 0; i < maxX; i++) {
			System.out.println(String.join(",",wordSearchGrid[i]));
		}*/
		//System.out.println(wordSearchGrid);
		return wordSearchGrid;
	}
}
