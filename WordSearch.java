package kata;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
//KATA INSTRUCTIONS SAY GRID WILL ALWAYS BE SQUARE SO ROWS WILL ALWAYS EQUAL COLUMNS
//INSTRUCTIONS WILL ALSO SAY you can assume all words will be present in the grid (and only once?)
public class WordSearch {
	String inputFileContents = null;
	ArrayList<String> wordSearchLines = new ArrayList<String>();
	String [] searchWords = null;
	String [][] wordSearchGrid = null;
	String [] solutions = null;//stores locations of search words when found
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
			while (scan.hasNextLine() == true) {
				String temp = "";
				temp = scan.nextLine();//stores the raw text from the next line of the input file
				inputFileContents += "\n" + temp;
				if (lineCount == 0) {//FIRST LINE is the words to search for, needs different treatment than other lines that form the puzzle itself
					searchWords = temp.split(",");
					solutions = new String[searchWords.length];//sets solutions array to be same length as searchWords array
					for (int i = 0; i < solutions.length; i++)
						solutions[i] = "";
					//System.out.println("solutions array length equals: " + solutions.length);
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
						//SHOULD THROW ERROR or exception to fail test
					}

						for (int c = 0; c < columns; c++) {
							wordSearchGrid[c][lineCount - 1] = lineToLetters[c];
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
			/*for (int c = 0; c < columnCount; c++) {
					for (int r = 0; r < rowCount; r++) {
						String temp = wordSearchLines.get(r);
						String [] lineToLetters = temp.split(",");
						wordSearchGrid[c][r] = lineToLetters[c];
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
		for (int c = 0; c < columns; c++) {
			for (int r = 0; r < rows; r++) {
				for (int w = 0; w < searchWords.length; w++) {
						//System.out.println("Does " + searchWords[w] + " start with " + wordSearchGrid[i][j]);
					if (searchWords[w].startsWith(wordSearchGrid[c][r])) {//letter in grid matches first letter of search word, start recursive search
						String query = searchWords[w].substring(0,1);
						//System.out.println("Potential find of " + searchWords[w] + " starting at " + i + "," + j);
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
		//search for full word starting with straight up and then moving clockwise
		//System.out.println("Checking for " + searchWords[w] + " starting at " + column + "," + row);


		if (row - delta >= 0) {
			//check for word going up
			//System.out.println("\t...going up");

			String compareWith = "";
			for (int r = row; r >= (row - delta); r--) {
					compareWith += wordSearchGrid[column][r];
					//System.out.println("Comparison string now equals: " + compareWith);
			}
			//System.out.println("Does \'" + searchWords[w] + "\' equal \'" + compareWith + "\'?");
			if (searchWords[w].equals(compareWith)) {
				//System.out.println("\tMATCH FOUND!!!!\n");
				//System.out.println(searchWords[w] + " is the same as " + compareWith);
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
			else {
				//System.out.println("\tNO MATCH!!!");
			}
		}

		if (column + delta < columns && row - delta >= 0) {
			//check for word up-right
			//System.out.println("\t...going up-right");

			String compareWith = "";
			for (int c = column, r = row; c <= (column + delta) && r >= (row - delta); c++, r--) {
				//for (int r = row, ; r >= (row - delta); r--) {
					//System.out.println("Adding letter at: (" + c + "," + r + ")");
					compareWith += wordSearchGrid[c][r];
					//System.out.println("Comparison string now equals: " + compareWith);
				//}
			}
			//System.out.println("Does \'" + searchWords[w] + "\' equal \'" + compareWith + "\'?");
			if (searchWords[w].equals(compareWith)) {
				//System.out.println("\tMATCH FOUND!!!!\n");
				//System.out.println(searchWords[w] + " is the same as " + compareWith);
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
			else {
				//System.out.println("\tNO MATCH!!!");
			}
		}

		if (column + delta < columns) {
			//check for word going right
			//System.out.println("\t...going right");
			/*int sum = column + delta;
			System.out.println("column + delta = " + sum);
			System.out.println("columns = " + columns);*/


			String compareWith = "";
			for (int c = column; c <= (column + delta); c++) {
					compareWith += wordSearchGrid[c][row];
					//System.out.println("Comparison string now equals: " + compareWith);
			}
			//System.out.println("Does \'" + searchWords[w] + "\' equal \'" + compareWith + "\'?");
			if (searchWords[w].equals(compareWith)) {
				//System.out.println("\tMATCH FOUND!!!!\n");
				//System.out.println(searchWords[w] + " is the same as " + compareWith);
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
			else {
				//System.out.println("\tNO MATCH!!!");
			}

		}


		if (column + delta < columns && row + delta < rows) {
			//check for word down-right
			//System.out.println("\t...going down-right");

			String compareWith = "";
			for (int c = column, r = row; c <= (column + delta) && r <= (row + delta); c++, r++) {
				//for (int r = row, ; r >= (row - delta); r--) {
					//System.out.println("Adding letter at: (" + c + "," + r + ")");
					compareWith += wordSearchGrid[c][r];
					//System.out.println("Comparison string now equals: " + compareWith);
				//}
			}
			//System.out.println("Does \'" + searchWords[w] + "\' equal \'" + compareWith + "\'?");
			if (searchWords[w].equals(compareWith)) {
				//System.out.println("\tMATCH FOUND!!!!\n");
				//System.out.println(searchWords[w] + " is the same as " + compareWith);
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
			else {
				//System.out.println("\tNO MATCH!!!");
			}
		}


		if (row + delta < rows) {
			//check for word going down
			//System.out.println("\t...going down");

			String compareWith = "";
			for (int r = row; r <= (row + delta); r++) {
					compareWith += wordSearchGrid[column][r];
					//System.out.println("Comparison string now equals: " + compareWith);
			}
			//System.out.println("Does \'" + searchWords[w] + "\' equal \'" + compareWith + "\'?");
			if (searchWords[w].equals(compareWith)) {
				//System.out.println("\tMATCH FOUND!!!!\n");
				//System.out.println(searchWords[w] + " is the same as " + compareWith);
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
			else {
				//System.out.println("\tNO MATCH!!!");
			}

		}
		if (column - delta >= 0 && row + delta < rows) {
			//check for word down-left
			//System.out.println("\t...going down-left");

			String compareWith = "";
			for (int c = column, r = row; c >= (column - delta) && r <= (row + delta); c--, r++) {
				//for (int r = row, ; r >= (row - delta); r--) {
					//System.out.println("Adding letter at: (" + c + "," + r + ")");
					compareWith += wordSearchGrid[c][r];
					//System.out.println("Comparison string now equals: " + compareWith);
				//}
			}
			//System.out.println("Does \'" + searchWords[w] + "\' equal \'" + compareWith + "\'?");
			if (searchWords[w].equals(compareWith)) {
				//System.out.println("\tMATCH FOUND!!!!\n");
				//System.out.println(searchWords[w] + " is the same as " + compareWith);
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
			else {
				//System.out.println("\tNO MATCH!!!");
			}
		}


		if (column - delta >= 0) {
			//check for word left
			//System.out.println("\t...going left");
			String compareWith = "";
			for (int c = column; c >= (column - delta); c--) {
					compareWith += wordSearchGrid[c][row];
					//System.out.println("Comparison string now equals: " + compareWith);
			}
			//System.out.println("Does \'" + searchWords[w] + "\' equal \'" + compareWith + "\'?");
			if (searchWords[w].equals(compareWith)) {
				//System.out.println("\tMATCH FOUND!!!!\n");
				//System.out.println(searchWords[w] + " is the same as " + compareWith);
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
			else {
				//System.out.println("\tNO MATCH!!!");
			}
		}


		if (row - delta >= 0 && column - delta >= 0) {
			//check for word up-left
			//System.out.println("\t...going up-left");

			String compareWith = "";
			for (int c = column, r = row; c >= (column - delta) && r >= (row - delta); c--, r--) {
				//for (int r = row, ; r >= (row - delta); r--) {
					//System.out.println("Adding letter at: (" + c + "," + r + ")");
					compareWith += wordSearchGrid[c][r];
					//System.out.println("Comparison string now equals: " + compareWith);
				//}
			}
			//System.out.println("Does \'" + searchWords[w] + "\' equal \'" + compareWith + "\'?");
			if (searchWords[w].equals(compareWith)) {
				//System.out.println("\tMATCH FOUND!!!!\n");
				//System.out.println(searchWords[w] + " is the same as " + compareWith);
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
			else {
				//System.out.println("\tNO MATCH!!!");
			}
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
		//System.out.println("Printing complete word search grid:");
		//System.out.println(wordSearchGrid);
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

	public boolean hasAnswers () {
		for (int i = 0; i < solutions.length; i++) {
			if (solutions[i] == "")
				return false;
		}
		return true;
	}
}
