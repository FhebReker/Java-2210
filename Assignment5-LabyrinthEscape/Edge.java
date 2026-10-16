/* 
CS 2210 – Assignment 5
Name: Fardin Abbassi 
Student Number: 251446579 
Email: fabbassi@uwo.ca
Created: November 30, 2025 
*/

public class Edge {
    // Instance variables
    private Node firstEndpoint, secondEndpoint;
    private int type; // 1 = corridor, 2 = brick wall, 3 = rock wall, 4 = metal wall

    // Constructor
    public Edge(Node u, Node v, int edgeType) {
        this.firstEndpoint = u;
        this.secondEndpoint = v;
        this.type = edgeType;
    }

    // Setter and Getters
    public void setType(int newType) {this.type = newType;}
    public int getType() {return this.type;}
    public Node firstEndpoint() {return this.firstEndpoint;}
    public Node secondEndpoint() {return this.secondEndpoint;}

    // Equals
    public boolean equals(Edge otherEdge) {
        if (this.firstEndpoint.equals(otherEdge.firstEndpoint)) {return this.secondEndpoint.equals(otherEdge.secondEndpoint());}  // (u,v) == (u,v)
        else if (this.firstEndpoint.equals(otherEdge.secondEndpoint())) {return this.secondEndpoint.equals(otherEdge.firstEndpoint());} // (u,v) == (v,u)
        else return false;
    }
}