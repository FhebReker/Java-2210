/* 
CS 2210 – Assignment 2
Name: Fardin Abbassi 
Student Number: 251446579 
Email: fabbassi@uwo.ca
Created: October 11, 2025 
*/

/** This is just the class implementing the class of exceptions thrown by the put and remove methods of Dictionary.  */
public class DictionaryException extends Exception {
    public DictionaryException(String error) {
        super(error);
    }
}
