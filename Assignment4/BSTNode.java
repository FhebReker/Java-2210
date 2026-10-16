/* 
CS 2210 – Assignment 4
Name: Fardin Abbassi 
Student Number: 251446579 
Email: fabbassi@uwo.ca
Created: November 4, 2025 
*/

public class BSTNode {
    // Instance variables
    private BSTNode parent;
    private BSTNode leftChild;
    private BSTNode rightChild;
    private Data data;

    // Constructors
    public BSTNode() {
        this.parent = null;

        this.leftChild = null;
        this.rightChild = null;

        this.data = null;
    }
    public BSTNode(BSTNode newParent, BSTNode newLeftChild, BSTNode newRightChild, Data newData) {
        this.parent = newParent;
        this.leftChild = newLeftChild;
        this.leftChild.setParent(this);

        this.rightChild = newRightChild;
        this.rightChild.setParent(this);

        this.data = newData;
    }

    // Getters
    public BSTNode getParent() {return this.parent;}
    public BSTNode getLeftChild() {return this.leftChild;}
    public BSTNode getRightChild() {return this.rightChild;}
    public Data getData() {return this.data;}

    // Setters
    public void setParent(BSTNode newParent) {this.parent = newParent;}
    public void setLeftChild(BSTNode newLeftChild) {this.leftChild = newLeftChild;}
    public void setRightChild(BSTNode newRightChild) {this.rightChild = newRightChild;}
    public void setData(Data newData) {this.data = newData;}

    // Is Leaf
    public boolean isLeaf() {return (this.leftChild == null && this.rightChild == null && data == null);}
}