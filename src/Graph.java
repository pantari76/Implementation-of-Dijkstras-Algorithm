import java.util.ArrayList;

// Contains all the nodes of the graph
public class Graph {
    private ArrayList<Node> nodes;

    public Graph() {
        nodes = new ArrayList<Node>();
    }

    // A way to add nodes into the graph
    public void addNode(Node n) {
        nodes.add(n);
    }

    // Accessor method
    public ArrayList<Node> getNodes() {
        return nodes;
    }
}  
