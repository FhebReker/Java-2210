/* 
CS 2210 – Assignment 2
Name: Fardin Abbassi 
Student Number: 251446579 
Email: fabbassi@uwo.ca
Created: October 11, 2025 
*/

/**
 This class stores information about the tiles placed on the game-board and implements support
 methods needed by the algorithm that plays the game. 
 */
public class Board implements BoardADT {
    // Instance Variables
    private int boardSize;
    private int emptyPositions;
    private int maxLevels;
    private char[][] theBoard;

    // Constructor
    public Board(int boardSize, int emptyPositions, int maxLevels) {
        // Initialize instance variables
        this.boardSize = boardSize;
        this.emptyPositions = emptyPositions;
        this.maxLevels = maxLevels;
        
        // Initialize board and populate it with empty characters
        this.theBoard = new char[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                theBoard[i][j] = 'E';
            }
        }
    }

    // Make Dictionary
    @Override
    public HashDictionary makeDictionary() {
        // Assume boardSize is prime, make a hash dictionary
        HashDictionary createdDict = new HashDictionary(boardSize);
        return createdDict;
    }

    // Repeated Layout
    @Override
    public int repeatedLayout(HashDictionary table) {
        // Initialize layout string
        String layoutString = "";

        // Add top down, left to right, then look for score in hash table
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                layoutString += theBoard[i][j]; // check if this goes top-down first or left-right first and adjust accordingly
            }
        }
        int score = table.get(layoutString);

        // Return score
        return score;
    }

    // Store Layout
    @Override
    public void storeLayout(HashDictionary table, int score) {
        // Initialize layout string
        String layoutString = "";

        // Add to the layout string, top-down and left-to-right
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                layoutString += theBoard[i][j];
            }
        }

        // Make Layout object and add to table (handle DictionaryException if needed)
        Layout layout = new Layout(layoutString, score);
        try {
            table.put(layout);
        } catch (DictionaryException e) {
            System.err.println(e);
        }
    }

    // Save Tile
    @Override
    public void saveTile(int i, int j, char symbol) {
        // Store the symbol at the position on the board
        theBoard[i][j] = symbol;
    }

    // Position Is Empty
    @Override
    public boolean positionIsEmpty(int row, int column) {
        // Return if the position is empty or not
        return theBoard[row][column] == 'E';
    }

    // Is Computer Tile
    @Override
    public boolean isComputerTile(int row, int column) {
        // Return if the position is a computer tile or not
        return theBoard[row][column] == 'R';
    }

    // Is Human Tile
    @Override
    public boolean isHumanTile(int row, int column) {
        // Return if the position is a human tile or not
        return theBoard[row][column] == 'B';
    }
 
    // Winner
    @Override
    public boolean winner(char symbol) {
        // Check rows and column for consecutive symbols for win
        for (int j = 0; j < boardSize; j++) {
            boolean rowWin = true;
            boolean columnWin = true;
            for (int i = 0; i < boardSize; i++) {
                if (theBoard[j][i] != symbol) {rowWin = false;}
                if (theBoard[i][j] != symbol) {columnWin = false;}
            }
            if (rowWin || columnWin) { return true;}
        }

        // Check diagonally for consecutive symbols for win
        boolean topLeftToDownRight = true;
        boolean topRightToDownLeft = true;
        for (int i = 0; i < boardSize; i++) {
            if (theBoard[i][i] != symbol) {topLeftToDownRight = false;}
            if (theBoard[i][boardSize - 1 - i] != symbol) {topRightToDownLeft = false;}
        }
        if (topLeftToDownRight || topRightToDownLeft) { return true;}

        // If no win condition is met, return false
        return false;
    }

    // Is Draw
    @Override
    public boolean isDraw(char symbol, int emptyPositions) {
        // Not a draw if someone won already
        if (winner('R') || winner('B')) { return false; }

        // Count empty positions
        int emptyCount = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (theBoard[i][j] == 'E') {emptyCount++;}
            }
        }

        // If there are no more positions to fill, it's a draw
        if (emptyCount == 0 && emptyPositions == 0) {return true;}

        // If there's at least 1 empty position and count matches positions
        if (emptyPositions > 0 && emptyCount == emptyPositions) {
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    // Check the surrounding characters to see if they're the same symbol as the current player
                    if (theBoard[i][j] == 'E') {
                        int [][] surroundingCharacters = {{-1, -1}, {-1, 0}, {-1, 1}, 
                                                        {0, -1},            {0, 1}, 
                                                        {1, -1}, {1, 0}, {1, 1}};
                        for (int[] coordinates: surroundingCharacters) {
                            int row = i + coordinates[0];
                            int column = j + coordinates[1];

                            // If empty position has the symbol next to it, we can keep going
                            if (row >= 0 && row < boardSize && column >= 0 && column < boardSize) {
                                 if (theBoard[row][column] == symbol) return false;
                            }
                        }
                    }
                }
            }

            // If all empty positions have been checked, return true
            return true;
        }

        // Otherwise, return false
        return false;
    }
    
    // Evaluate
    @Override
    public int evaluate(char symbol, int emptyPositions) {
        // Return 3 if computer wins, 0 if human wins, 2 if draw, 1 if game is still ongoing
        if (winner('R')) return 3;
        else if (winner('B')) return 0;
        else if (isDraw(symbol, emptyPositions)) return 2;
        else return 1;
    }
}