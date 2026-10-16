/* 
CS 2210 – Assignment 2
Name: Fardin Abbassi 
Student Number: 251446579 
Email: fabbassi@uwo.ca
Created: October 11, 2025 
*/

/**
An object of this class stores in two instance variables a board layout and its associated integer score, as explained above. 
 
 Each board layout will be represented as a String as follows. 
 A blue human’s tile is represented with the character ’B’, a red computer’s tile with ’R’, and a green empty space with ’E’. 
 
 To form a String for a given board layout we concatenate the characters corresponding to the tiles and empty positions currently on the board
 starting at the upper left position and moving top to bottom and left to right.
 */
public class Layout {
    // Instance Variables
    private String boardLayout;
    private int score;

    // Constructor
    public Layout(String boardLayout, int score) {
        this.boardLayout = boardLayout;
        this.score = score;
    }

    // Getters
    public String getBoardLayout() {return this.boardLayout;}
    public int getScore() {return this.score;}
}
