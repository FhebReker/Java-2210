import java.util.ArrayList;
import java.util.HashMap;

public class UndirectedGraph {
    // Instance variable
    private HashMap<Node, ArrayList<Edge>> adjList;

    // Constructor
    public UndirectedGraph(int n) {
        // Initialize adjacency list
        this.adjList = new HashMap<Node, ArrayList<Edge>>();

        // Loop until all nodes are added
        for (int i = 0; i < n; i++) {
            Node addToList = new Node(i);
            this.adjList.put(addToList, new ArrayList<Edge>());
        }
    }

    // Insert Edge
    public void insertEdge(Node u, Node v, int edgeType) throws GraphException {
        // Check if nodes are legal, throw if not
        try {this.getNode(u.getName());}
        catch (GraphException e) {throw new GraphException("First node doesn't exist in the graph");}
        try {this.getNode(v.getName());} 
        catch (Exception e) {throw new GraphException("Second node doesn't exist in the graph");}

        // Double check if edge isn't already in the list, throw if it is
        Edge edgeToAdd = new Edge(u, v, edgeType);
        Edge invertedEdgeToAdd = new Edge(v, u, edgeType);
        if (this.adjList.get(u).contains(edgeToAdd)){throw new GraphException("Edge already exists");}
        if (this.adjList.get(v).contains(invertedEdgeToAdd)){throw new GraphException("Edge already exists");}

        // Add edge to list
        if (this.adjList.get(u) == null) this.adjList.put(u, new ArrayList<Edge>());
        if (this.adjList.get(v) == null) this.adjList.put(v, new ArrayList<Edge>());
        this.adjList.get(u).add(edgeToAdd);
        this.adjList.get(v).add(invertedEdgeToAdd);
    }

    // Get Node
    public Node getNode(int name) throws GraphException {
        // Look  through graph to find node
        for (Node node : this.adjList.keySet()) {
            if (node.getName() == name) return node;
        }

        // Throw exception if not in the graph
        throw new GraphException("Node not in graph");
    }

    // Incident Edges
    public ArrayList<Edge> incidentEdges (Node u) throws GraphException {
        // Throw exception if u is not valid, throw if not
        try {this.getNode(u.getName());} 
        catch (GraphException e) {throw new GraphException("Node doesn't exist in graph");}
        
        // Look through list of edges to find edges with the node
        ArrayList<Edge> edges = new ArrayList<Edge>();
        for (Edge edge : this.adjList.get(u)) {edges.add(edge);}

        // If empty, return null; otherwise return list of edges
        if (edges.isEmpty()) return null;
        else return edges;
    }

    // Get Edge
    public Edge getEdge(Node u, Node v) throws GraphException {
        // Throw exception if u or v are not valid, throw if not
        try {this.getNode(u.getName());} 
        catch (GraphException e) {throw new GraphException("First node doesn't exist");}
        try {this.getNode(v.getName());} 
        catch (Exception e) {throw new GraphException("Second node doesn't exist");}

        // Loop through all edges with first endpoint to find edge
        for (Edge edge : this.adjList.get(u)) {
            if (edge.secondEndpoint().equals(v)) {return edge;}
        }

        // Throw exception if not in list
        throw new GraphException("Edge does not exist");
    }

    // Are Adjacent
    public boolean areAdjacent(Node u, Node v) throws GraphException{
        // Throw exception if u or v are not valid
        try {this.getNode(u.getName());} 
        catch (GraphException e) {throw new GraphException("First node doesn't exist in graph");}
        try {this.getNode(v.getName());} 
        catch (Exception e) {throw new GraphException("Second node doesn't exist in graph");}

        try {return this.getEdge(u, v) != null;}
        catch (GraphException e) {return false;}
    }
}