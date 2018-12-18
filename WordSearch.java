package kata;

import java.util.Scanner;
import java.io.*;

public class WordSearch {
	String inputFileContents = null;
	String [] searchWords = null;
	String [][] wordSearchGrid = null;
	int maxX = 15;//starting from top left equals number of columns
	int maxY = 15;//starting from top left equals number of rows
	//String wordSearchFile;
	//need variable to store contents of word search, 2d array?

	WordSearch() {

	}

	WordSearch(String file) {
		//System.out.println("Created WordSearch object w following input: ");
		//System.out.println(file);
		//wordSearchFile = file;
		loadWordSearch(file);
	}

	public boolean loadWordSearch(String file) {
		try {
			Scanner scan = new Scanner(new File(file));
			int lineCount = 0;
			inputFileContents = "";
			wordSearchGrid = new String[maxX][maxY];
			while (scan.hasNextLine() == true) {
				String temp = "";
				temp = scan.nextLine();
				inputFileContents += "\n" + temp;
				if (lineCount == 0) {//FIRST LINE is the words to search for, needs different treatment than other lines that form the puzzle itself
					searchWords = temp.split(",");
				}
				else {//add line of word search contents from file to word search grid data structure
					String [] lineToLetters = temp.split(",");
					for (int i = 0; i < lineToLetters.length; i++) {
						wordSearchGrid[lineCount-1][i] = lineToLetters[i];
					}
					//System.out.println("Line " + lineCount + " (converted to String [] and back to String): " + String.join(",",lineToLetters));

				}
				//System.out.println(lineCount + ": " + temp);
				lineCount++;
			}
			return true;
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
			return false;
		}
	}

	public boolean findWordsInGrid() {
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
