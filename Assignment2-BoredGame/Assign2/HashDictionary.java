/* 
CS 2210 – Assignment 2
Name: Fardin Abbassi 
Student Number: 251446579 
Email: fabbassi@uwo.ca
Created: October 11, 2025 
*/

/**
This class implements a dictionary using a hash table with separate chaining. 
The size of the table must be a prime number and that it should not be larger than 10,000. 
 */

// Linked list import for hash table w/ seperate chaining
import java.util.ArrayList;

public class HashDictionary implements DictionaryADT {
    // Instance Variables
    private ArrayList<Layout>[] table;
    private int size; // Assumed prime when created

    // Constructor
     public HashDictionary(int size) {
        // Use supression to avoid unchecked cast warning
        @SuppressWarnings("unchecked")
        ArrayList<Layout>[] surpressedTable = (ArrayList<Layout>[]) new ArrayList<?>[size];
        
        // Initialize instance variables
        this.table = surpressedTable;
        this.size = size;
        
        // Initialize table with empty lists
        for (int i = 0; i < size; i++) {
            table[i] = new ArrayList<Layout>();
        }
     }

    // Hash To Index
    private int hashToIndex(String boardLayout) {
        // Initialize variables
        char currentSquare = boardLayout.charAt(0);
        int hashIndex = (int)currentSquare;

        // Use polynomial hash code formula to find hash index and return
        for (int i = 1; i < boardLayout.length(); i++) {
            currentSquare = boardLayout.charAt(i);
            hashIndex = (hashIndex * 31 + (int)currentSquare) % this.size;
        }
        return hashIndex;
    }


    // Put
    @Override
    public int put(Layout data) throws DictionaryException {
        // Find hash index
        int hash = hashToIndex(data.getBoardLayout());

        // If empty, make array list at the specified score and add the layout then return 0
        if (table[hash].isEmpty()) {
            //table[hash] = new ArrayList<Layout>();
            table[hash].add(data);
            return 0;
        }

        // Otherwise, check each layout at hash and check for duplicates, add to the end and return 1 if all unique 
        else {
            // Throw dictionary exception if duplicate found
            for (Layout addedData : table[hash]) {
                if (addedData.getBoardLayout().equals(data.getBoardLayout())) {
                    throw new DictionaryException("Duplicate entry");
                }
            }
            table[hash].add(data);
            return 1;
        }
    }


    // Remove
    @Override
    public void remove(String boardLayout) throws DictionaryException {
        // Find hash and initialize counter
        int hash = hashToIndex(boardLayout);

        // Check each layout in the hash's array list to see if it can be removed, remove and return if we can
        for (int i = 0; i < table[hash].size(); i++){
            if (table[hash].get(i).getBoardLayout().equals(boardLayout)) {
                table[hash].remove(i);
                return;
            }
        }

        // If we haven't returned yet, the entry has yet to be added to the table
       throw new DictionaryException("No entry in table");
        
    }
    

    // Get
    @Override
    public int get(String boardLayout) {
        // Find hash
        int hash = hashToIndex(boardLayout);

        // Loop through array list until layout is found
        for (int i = 0; i < table[hash].size(); i++){
            if (table[hash].get(i).getBoardLayout().equals(boardLayout)) {
                return table[hash].get(i).getScore();
            }
        }

        // If we can't find it, return -1
        return -1;
    }
}
