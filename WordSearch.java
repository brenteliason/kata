package kata;

import java.util.Scanner;
import java.io.*;

public class WordSearch {
	String inputFileContents = null;
	//String wordSearchFile;
	//need variable to store contents of word search, 2d array?

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

}
