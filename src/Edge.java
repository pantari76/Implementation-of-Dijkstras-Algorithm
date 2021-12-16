//The edges between the nodes
public class Edge {

    // The node represents what the edge points to
    private Node vertex;

    // Weight of the edge
    private int weight;

    public Edge(Node n, int w) {
        vertex = n;
        weight = w;
    }

    // Accessor methods
    public Node getVertex() {
        return vertex;
    }

    public int getWeight() {
        return weight;
    }
}
