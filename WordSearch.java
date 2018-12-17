package kata;

import java.util.Scanner;
import java.io.*;

public class WordSearch {
	String inputFileContents = null;
	String [] searchWords = null;
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
			while (scan.hasNextLine() == true) {
				String temp = "";
				temp = scan.nextLine();
				inputFileContents += "\n" + temp;
				if (lineCount == 0) {//FIRST LINE is the words to search for, needs different treatment than other lines that form the puzzle itself
					searchWords = temp.split(",");
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

	public String getWordSearchContents() {
		System.out.println("\tgetWordSearchContents method called, contents are as follows:\n" + inputFileContents);
		return inputFileContents;
	}

	public String [] getSearchWords() {
		System.out.println("\tInside getSearchWords method, printing searchWords array:\n\t "+ String.join(",",searchWords));
		return searchWords;
	}

}
