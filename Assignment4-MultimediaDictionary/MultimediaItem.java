/* 
CS 2210 – Assignment 4
Name: Fardin Abbassi 
Student Number: 251446579 
Email: fabbassi@uwo.ca
Created: November 4, 2025 
*/

/**
 This class represents each one of the multimedia objects stored in the arrayList of a node of the
 binary search tree. This class has two instance variables:

 String content: This could be text, the name of an audio file, the name of an image file, or the name of an html document.
 int type: The values that this instance variable can take are these:
 – 1: if content is text
 – 2: if content is the name of an audio file
 – 3: if content is the name of an image file
 – 4: if content is the name of an html document.
 */

public class MultimediaItem {
    // Instance Variables
    private String content;
    private int type;

    // Constructor
    public MultimediaItem(String newContent, int newType) {
        this.content = newContent;
        this.type = newType;
    }

    // Getters
    public String getContent() {return this.content;}
    public int getType() {return this.type;}
}
