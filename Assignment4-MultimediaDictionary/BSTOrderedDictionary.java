/* 
CS 2210 – Assignment 4
Name: Fardin Abbassi 
Student Number: 251446579 
Email: fabbassi@uwo.ca
Created: November 4, 2025 
*/

/**
 This class implements an ordered dictionary using a binary search tree. 
 In the binary search tree only the internal nodes will store information. 
 The key for an internal node will be the name attribute of the Data object stored in that node. 
 */

import java.util.ArrayList;

public class BSTOrderedDictionary {
    // Instance Variables
    private BSTNode root;
    private int numInternalNodes;

    // Constructor
    public BSTOrderedDictionary() {
        this.root = new BSTNode();
        this.root.setLeftChild(null);
        this.root.setRightChild(null);
        this.numInternalNodes = 0;
    }
    
    // Getters
    public BSTNode getRoot() {return this.root;}
    public int getNumInternalNodes() {return this.numInternalNodes;}

    // Get
    public ArrayList<MultimediaItem> get (BSTNode r, String k) {
        // r is a leaf, return null
        if (r.isLeaf()) {return null;}

        // r is the key, return the arraylist in r's data
        if (r.getData().getName().equals(k)) {return r.getData().getMedia();}

        // Keep recursively going down the tree to find r
        if (k.compareTo(r.getData().getName()) > 0) {return get(r.getRightChild(), k);} // Key is lower alphabetically, search right
        else {return get(r.getLeftChild(), k);} // Key is higher alphabetically, search left
    }

    // Put
    public void put (BSTNode r, String key, String content, int type) {
        // Create the Multimedia item
        MultimediaItem itemToAdd = new MultimediaItem(content, type);

        // r is in the tree, add to list
        if (!r.isLeaf() && r.getData().getName().equals(key)) {r.getData().add(itemToAdd);}

        // r is not in the tree, initialize r, Data list and children, and add MultimediaItem to Data list, increase count
        else if (r.isLeaf()) {
            r.setData(new Data(key));
            r.getData().add(itemToAdd);

            r.setLeftChild(new BSTNode());
            r.getLeftChild().setParent(r);
            r.setRightChild(new BSTNode());
            r.getRightChild().setParent(r);

            numInternalNodes++;
        }

        // Keep recursively going down the tree to find r's position
        else {
            if (key.compareTo(r.getData().getName()) > 0) {put(r.getRightChild(), key, content, type);} // Key is lower alphabetically, search right
            else {put(r.getLeftChild(), key, content, type);} // Key is higher alphabetically, search left
        }
    }

    // Remove
    void remove(BSTNode r, String key) throws DictionaryException {
        // r is leaf, throw exception        
        if (r.isLeaf()) {throw new DictionaryException("No node in the tree stores the given key");}

        // r is an internal, remove based on children
        else if (r.getData().getName().equals(key)) {
            // r children are both leaves, unlink r
            if (r.getLeftChild().isLeaf() && r.getRightChild().isLeaf()) {
                if (r.getParent() == null) {this.root = null;}
                else {
                    // r was right child, set new r to parent right
                    if (key.compareTo(r.getParent().getData().getName()) > 0) {
                        r.getParent().setRightChild(new BSTNode());
                        r.getParent().getRightChild().setParent(r.getParent());
                    }

                    // r was left child, set new r to parent left
                    else {
                        r.getParent().setLeftChild(new BSTNode());
                        r.getParent().getLeftChild().setParent(r.getParent());
                    }
                }
            }

            // Left child is leaf, link right child to parent
            else if (r.getLeftChild().isLeaf()) {
                BSTNode parent = r.getParent();
                // If root, set right child as new root
                if (parent == null) {this.root = r.getRightChild();}
                else {
                    // r was right child, set r right to parent right
                    if (key.compareTo(parent.getData().getName()) > 0) {
                        parent.setRightChild(r.getRightChild());
                        parent.getRightChild().setParent(parent);
                    }

                    // r was left child, set r right to parent left
                    else {
                        parent.setLeftChild(r.getRightChild());
                        parent.getLeftChild().setParent(parent);
                    }
                }
            }

            // Right child is leaf, link left child to parent
            else if (r.getRightChild().isLeaf()) {
                BSTNode parent = r.getParent();

                // If root, set right child as new root
                if (parent == null) {this.root = r.getLeftChild();}
                else {
                    // r was right child, set r left to parent right
                    if (key.compareTo(parent.getData().getName()) > 0) {
                        parent.setRightChild(r.getLeftChild());
                        parent.getRightChild().setParent(parent);
                    }

                    // r was left child, set r left to parent left
                    else {
                        parent.setLeftChild(r.getLeftChild());
                        parent.getLeftChild().setParent(parent);
                    }
                }
                
            }

            // Both children are internals, find successor node to take position and remove from its previous position
            else {
                // Find successor
                BSTNode successor = r.getRightChild();
                while (!successor.getLeftChild().isLeaf()) {        
                    successor = successor.getLeftChild();
                }

                // Copy data and remove successor
                r.setData(successor.getData());
                remove(successor, key);
            }
            numInternalNodes--;
        }

        // Keep recursively going down the tree to find r's position
        else {
            if (key.compareTo(r.getData().getName()) > 0) {remove(r.getRightChild(), key);} // Key is lower alphabetically, search right
            else {remove(r.getLeftChild(), key);}  // Key is higher alphabetically, search left
        }
    }


    // Remove (type)
    void remove(BSTNode r, String key, int type) throws DictionaryException {
        // r is leaf, throw exception        
        if (r.isLeaf()) {throw new DictionaryException("No node in the tree stores the given key");}
        
        // r is an internal, check if r matches key
        else {
            // Found key, remove based items based on type
            if (r.getData().getName().equals(key)) {
                ArrayList<MultimediaItem> list = r.getData().getMedia();
                int i = 0;

                // Loop through list and delete based on type
                while (i < list.size()) {
                    if (list.get(i).getType() == type) {list.remove(i);}
                    i++;
                }

                // If the list ends up empty, remove the node entirely
                if (list.isEmpty()) {remove(r, key);}
            }

            // Keep recursively going down the tree to find r's position
            else {
                if (key.compareTo(r.getData().getName()) > 0) {remove(r.getRightChild(), key, type);} // Key is lower alphabetically, search right
                else {remove(r.getLeftChild(), key, type);} // Key is higher alphabetically, search left
            }
        }
    }

    // Smallest
    public Data smallest (BSTNode r) {
        // Check if r is a leaf
        if (r.isLeaf()) {return null;}

        // Find farthest left child
        BSTNode current = r;
        while (!current.isLeaf() && !current.getLeftChild().isLeaf()) {current = current.getLeftChild();}
        return current.getData();
    }
    // Largest
    public Data largest (BSTNode r) {
        // Check if r is a leaf
        if (r.isLeaf()) {return null;}


        // Find farthest right child
        BSTNode current = r;
        while (!current.getRightChild().isLeaf()) {current = current.getRightChild();}
        return current.getData();
    }

    // Successor
    public Data successor (BSTNode r, String key) {
        // Check if r is a leaf
        if (r.isLeaf()) {return null;}

        BSTNode current = r;
        BSTNode successor = null;

        // Loop until we find the key or reach a leaf
        while (!current.isLeaf()) {
            // Search right if key is higher alphabetically
            if (key.compareTo(current.getData().getName()) > 0) {current = current.getRightChild();}
            
            // Search left if key is lower alphabetically, and update successor
            else if ((key.compareTo(current.getData().getName()) < 0)) {
                successor = current;
                current = current.getLeftChild();
            } 
            
            // Break out once we find the node holding the key
            else {break;}
        }

        // If we found the key with a right child, find smallest in right subtree
        if (!current.isLeaf() && !current.getRightChild().isLeaf()) {return smallest(current.getRightChild());}

        // Otherwise check if successor is null, and return either null or its data
        else if (successor == null) return null;
        else return successor.getData();
    }

    // Predecessor
    public Data predecessor (BSTNode r, String key) {
        // Return null if r is a leaf
        if (r.isLeaf()) {return null;}

        BSTNode current = r;
        BSTNode predecessor = null;

        // Loop until we find the key or reach a leaf
        while (!current.isLeaf()) {
            // Search left if key is lower alphabetically
            if (key.compareTo(current.getData().getName()) < 0) {current = current.getLeftChild();}

            // Search right if key is higher alphabetically, and update predecessor 
            else if (key.compareTo(current.getData().getName()) > 0) {
                predecessor = current;
                current = current.getRightChild();
            }

            // Break out once we find the node holding the key
            else {break;}
        }

        // If we found the key with a left child, find largest in left subtree
        if (!current.isLeaf()) {
            if ( !current.getLeftChild().isLeaf()) {return largest(current.getLeftChild());}
            else {return predecessor.getData();}
        }

        // Otherwise check if predecessor is null, and return either null or its data
        if (predecessor == null) return null;
        else return predecessor.getData();
    }
}
