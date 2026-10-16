/* 
CS 2210 – Assignment 4
Name: Fardin Abbassi 
Student Number: 251446579 
Email: fabbassi@uwo.ca
Created: November 4, 2025 
*/

import java.util.ArrayList;

public class Data {
    // Instance Variables
    private String name;
    private ArrayList<MultimediaItem> media;

    // Constructor
    public Data(String newName) {
        this.name = newName;
        this.media = new ArrayList<MultimediaItem>();
    }

    // Add
    public void add(MultimediaItem newItem) {this.media.add(newItem);}

    // Getters
    public String getName() {return this.name;}
    public ArrayList<MultimediaItem> getMedia() {return this.media;}
}
