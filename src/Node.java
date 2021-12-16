import java.util.ArrayList;

// The vertices or nodes of the graph
public class Node {

    // The name of the node is used to find its index later on in the Implementation
    private String name;

    //All the edges that the node has
    private ArrayList<Edge> connections;

    // Empty constructor 
    public Node() {
        name = "unknown";
        connections = new ArrayList<Edge>();
    }

    public Node(String n) {
        name = n;
        connections = new ArrayList<Edge>();
    }

    // Accessors methods
    public String getName() {
        return name;
    }

    public ArrayList<Edge> getConnections() {
        return connections;
    }

    // Mutator methods
    public void addEdge(Edge e) {
        connections.add(e);
    }

}
