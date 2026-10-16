/* 
CS 2210 – Assignment 5
Name: Fardin Abbassi 
Student Number: 251446579 
Email: fabbassi@uwo.ca
Created: November 30, 2025 
*/

import java.io.*;
import java.util.Iterator;
import java.util.Stack;

public class Solve {
    // Instance variables
    private int blastBombNum, meltBombNum;
    private UndirectedGraph labyrinth;
    private Node start, end;
    
    // Constructor
    public Solve(String inputFile) throws LabyrinthException {
        try {
            // Initialize variables 
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line = br.readLine(); // initialize while getting rid of first line
            
            // Handle first few lines and initialize variables
            line = br.readLine();
            int width = Integer.parseInt(line); line = br.readLine(); 
            int length = Integer.parseInt(line); line = br.readLine();
            blastBombNum = Integer.parseInt(line); line = br.readLine();
            meltBombNum = Integer.parseInt(line);  line = br.readLine();
            this.labyrinth = new UndirectedGraph(length*width);
            
            // Handle the board
            char[][] board = new char[2*length-1][2*width-1];
            int row = 0;
            while (line != null) {
                // Add to board of all characters (both to check walls and rooms)
                for (int column = 0; column < line.length(); column++) {
                    board[row][column] = line.charAt(column);
                    if (board[row][column] == 'e') start = this.labyrinth.getNode(column/2 + row/2*width); 
                    else if (board[row][column] == 'x') end = this.labyrinth.getNode(column/2 + row/2*width); 
                }


                // Check for new edges, then add to labyrinth graph if found any
                for (int column = 0; column < line.length(); column+=2) {
                    // Initialize variables
                    Node currNode = this.labyrinth.getNode(column/2 + row/2*width);
                    Node surroundingNode;
                    // Continue if line doesn't have rooms
                    if (row % 2 == 1) continue;
                    int edgeType = -1;
                    boolean possibleEdge = true;

                    // Find corridors to north room
                    if (row > 0) {
                        surroundingNode = this.labyrinth.getNode(column/2 + row/2*width - width);
                        switch (board[row - 1][column]) {
                            case '|':
                                edgeType = 1;
                                break;
                            case 'B':
                                edgeType = 2;
                                break;
                            case 'R':
                                edgeType = 3;
                                break;
                            case 'M':
                                edgeType = 4;
                                break;
                            case '*':
                                possibleEdge = false;
                                break;
                            default:
                                throw new LabyrinthException("Error in labyrinth vertical edge type handling");
                        }

                        // Add to the labyrinth if possible, otherwise handle exception
                        if (possibleEdge && edgeType != -1) {
                            try {this.labyrinth.insertEdge(currNode, surroundingNode, edgeType);} 
                            catch (GraphException e) {e.printStackTrace();}
                        }
                    }


                    // Find corridors to east room if exists
                    if (column < line.length() - 2) {
                        surroundingNode = this.labyrinth.getNode((column/2)+1 + row/2*width);
                        possibleEdge = true;
                        switch (board[row][column+1]) {
                            case '-':
                                edgeType = 1;
                                break;
                            case 'b':
                                edgeType = 2;
                                break;
                            case 'r':
                                edgeType = 3;
                                break;
                            case 'm':
                                edgeType = 4;
                                break;
                            case '*':
                                possibleEdge = false;
                                break;
                            default:
                                throw new LabyrinthException("Error in labyrinth horizontal edge handling");
                        }

                        // Add to the labyrinth if possible, otherwise handle exception
                        if (possibleEdge && edgeType != -1) {
                            try {this.labyrinth.insertEdge(currNode, surroundingNode, edgeType);} 
                            catch (Exception e) {e.printStackTrace();}
                        }
                    }
                }
                row++;
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.err.println("\nError reading file: " + inputFile);
            e.printStackTrace();
            System.exit(1);
        }
    }

    // Get Graph
    public UndirectedGraph getGraph() {return this.labyrinth;}

    // Find Exit
    public Iterator<Node> findExit() {
        // Initialize stack, then use DFS to find a possible path
        Stack<Node> nodeStack = new Stack<Node>();
        try {
            this.DFS(start, nodeStack, end); // debug
            //if (this.DFS(start, nodeStack, end)) 
            if (!nodeStack.isEmpty()) return nodeStack.iterator();
            else return null;
        } 

        // If something goes wrong, output error and return null
        catch (GraphException e) {
            e.printStackTrace();
            System.err.println("Error reading labyrinth");
            return null; // debug
        }

        //return nodeStack.iterator(); // debug
    }
    private boolean DFS(Node u, Stack<Node> s, Node target) throws GraphException {
        // Push and mark current node
        s.push(u);
        u.setMark(true);

        // Return true if we found the target
        if (u.equals(target)) return true;

        // Check each connected node to u to see if it's not marked already, check if we need to use a bomb, then check the path from there
        for (Edge connection : labyrinth.incidentEdges(u)) {
            if (!connection.secondEndpoint().getMark()) {
                boolean usedMeltBomb = false;
                boolean usedBlastBomb = false;

                // Blast wall or move onto next connection
                if ((connection.getType() == 2 || connection.getType() == 3) && this.blastBombNum > 0) {
                    usedBlastBomb = true;
                    this.blastBombNum--;
                } else if (connection.getType() == 2 || connection.getType() == 3) {continue;}
                
                // Melt wall or move onto next connection
                if ((connection.getType() == 4) && this.meltBombNum > 0) {
                    usedMeltBomb = true;
                    this.meltBombNum--;
                } else if (connection.getType() == 4) {continue;}

                // DFS to next node
                if (this.DFS(connection.secondEndpoint(), s, target)) return true;

                // Add bombs back if we can't find the path from here
                if (usedBlastBomb){ this.blastBombNum++;}
                if (usedMeltBomb){ this.meltBombNum++;}
            }
        }

        // Pop, unmark, then return false
        s.pop();
        u.setMark(false);
        return false;
    }
}