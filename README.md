Word Search Kata
================
This program completes a [word search](https://en.wikipedia.org/wiki/Word_search) problem.

Given a text file consisting of a list of words, and a series of rows of single-character lists representing the word search grid, this program should search for the words in the grid and return a set of x,y coordinates for each word found.

The program comes with two drivers, Tests.java and Driver.java. Tests takes in a file on the command line, runs a series of tests, and then prints the locations of the words found in the word search. Driver takes in a file on the command line, skips the tests, and just prints out the locations of the words found in the word search. You can rule either with the input files provided or with another one of your marking provided it complies with the requirements outlined below.

## Quick Start (assumes you have Java installed) ##

1) Download kata repository.
2) Run "javac *.java" inside kata folder.
3) Run "java kata/[Driver OR Tests] [kata/input0.txt OR kata/input1.txt]" inside parent directory above kata folder.
4) Enjoy!

## Input/Output ##

The program accepts a file that contains a list of words and then a grid of letters.

The program assumes that all the words to be searched for will be located on the first line of the input file, capitalized, separated by commas, and at least two letters long. Following the first line, each line should contain a list of letters separated by commas making up the word search. The program assumes that the word search has an equal number of columns and rows, and that all the words located on the first line will be found in the word search horizontally, vertically, or diagonally either forwards or backwards.

The output of the program is a list of the words found in the word search with the positions of the words described with (x,y) coordinates. (0,0) is the top-left letter of the grid. The x-coordinate represents the column number from 0 to the total number of columns minus 1, while the y-coordinate represents the row number from 0 to the total number of rows minus 1. Each line of the output is formatted as follows: "WORD: (x,y),(x,y)..."

## EXAMPLE ##

The following input:

<pre>
BONES,KHAN,KIRK,SCOTTY,SPOCK,SULU,UHURA
U,M,K,H,U,L,K,I,N,V,J,O,C,W,E
L,L,S,H,K,Z,Z,W,Z,C,G,J,U,Y,G
H,S,U,P,J,P,R,J,D,H,S,B,X,T,G
B,R,J,S,O,E,Q,E,T,I,K,K,G,L,E
A,Y,O,A,G,C,I,R,D,Q,H,R,T,C,D
S,C,O,T,T,Y,K,Z,R,E,P,P,X,P,F
B,L,Q,S,L,N,E,E,E,V,U,L,F,M,Z
O,K,R,I,K,A,M,M,R,M,F,B,A,P,P
N,U,I,I,Y,H,Q,M,E,M,Q,R,Y,F,S
E,Y,Z,Y,G,K,Q,J,P,C,Q,W,Y,A,K
S,J,F,Z,M,Q,I,B,D,B,E,M,K,W,D
T,G,L,B,H,C,B,E,C,H,T,O,Y,I,K
O,J,Y,E,U,L,N,C,C,L,Y,B,Z,U,H
W,Z,M,I,S,U,K,U,R,B,I,D,U,X,S
K,Y,L,B,Q,Q,P,M,D,F,C,K,E,A,B
</pre>

produces the following output:

<pre>
BONES: (0,6),(0,7),(0,8),(0,9),(0,10)
KHAN: (5,9),(5,8),(5,7),(5,6)
KIRK: (4,7),(3,7),(2,7),(1,7)
SCOTTY: (0,5),(1,5),(2,5),(3,5),(4,5),(5,5)
SPOCK: (2,1),(3,2),(4,3),(5,4),(6,5)
SULU: (3,3),(2,2),(1,1),(0,0)
UHURA: (4,0),(3,1),(2,2),(1,3),(0,4)
</pre>

## Included Info Files ##
The test driver Tests.java can run with any file, but it can compare the output made by input0.txt and input1.txt with the correct answers.

input0.txt is a simple 3 x 3 word search with 3 words present
input1.txt is a bigger word search with several words provided in the kata instructions as an example

The test case files demonstrate different examples of bad formatting or unusual scenarios. Numbers 1 to 6 should all make the test driver fail for various reasons. test_case7.txt contains a non-fatal error of a search word not being present. test_case8.txt contains repeat words to demonstrate that the program can detect duplicates.

## Contributions ##
I completed this project as part of a job application process. Feel free to use or modify as you wish. If you see a problem with my code, please let me know.
