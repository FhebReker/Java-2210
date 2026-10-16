/* 
CS 2210 – Assignment 5
Name: Fardin Abbassi 
Student Number: 251446579 
Email: fabbassi@uwo.ca
Created: November 30, 2025 
*/

public class Node {
    // Instance Variables
    private int name; // between 0 - (n-1)
    private boolean marked;

    // Constructor
    public Node (int nodeName) {
        this.name = nodeName;
        this.marked = false;
    }
    
    // Setter and Getters
    public void setMark(boolean mark) {this.marked = mark;}
    public boolean getMark() {return this.marked;}
    public int getName() {return this.name;}

    // Equals
    public boolean equals(Node otherNode) {return this.name == otherNode.getName();}
}