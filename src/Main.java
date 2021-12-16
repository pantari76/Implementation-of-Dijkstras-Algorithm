import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Creating an object of the Implementation Class to run all the algorithm functions
        Implementation implement = new Implementation();

        // Creating a graph of nodes and edges as specified in implementation
        Graph g = implement.createGraph();

        // Creating an array that stores the shortest path from the starting node to every node
        ArrayList<Paths> pathTable = implement.dijkstra(g, g.getNodes().get(0));

        // Setting a starting node
        Node start = pathTable.get(0).getNode();

        // Setting an end node
        Node end = pathTable.get(6).getNode();

        // Finding the path of nodes from the starting node to the end node
        String shortestPath = implement.findShortestPath(start, end, pathTable, "");

        System.out.println(shortestPath);
        System.out.println(implement.toString(pathTable));
        

    }
}
