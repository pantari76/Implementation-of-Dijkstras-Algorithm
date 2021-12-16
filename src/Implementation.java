import java.util.ArrayList;

public class Implementation {

    // This function serves to create the graph that Dijkstra's Algorithm will be implemented on
    public Graph createGraph() {
        Graph graph = new Graph();

        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");
        Node n5 = new Node("5");
        Node n6 = new Node("6");
        Node n7 = new Node("7");

        // The edges are added both ways so that the graph is undirected
        n1.addEdge(new Edge(n2, 6));
        n2.addEdge(new Edge(n1, 6));

        n1.addEdge(new Edge(n3, 7));
        n3.addEdge(new Edge(n1, 7));

        n2.addEdge(new Edge(n4, 3));
        n4.addEdge(new Edge(n2, 3));

        n2.addEdge(new Edge(n6, 10));
        n6.addEdge(new Edge(n2, 10));

        n3.addEdge(new Edge(n4, 1));
        n4.addEdge(new Edge(n3, 1));

        n3.addEdge(new Edge(n5, 5));
        n5.addEdge(new Edge(n3, 5));

        n4.addEdge(new Edge(n5, 2));
        n5.addEdge(new Edge(n4, 2));

        n5.addEdge(new Edge(n7, 6));
        n7.addEdge(new Edge(n5, 6));

        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);
        graph.addNode(n6);
        graph.addNode(n7);

        return graph;
    }


    public ArrayList<Paths> dijkstra(Graph graph, Node start) {
        ArrayList<Node> unvisited = new ArrayList<Node>();
        ArrayList<Paths> pathTable = new ArrayList<Paths>();
        unvisited = graph.getNodes();
        pathTable = intitializeTable(graph, start);
        return performAlgo(unvisited, pathTable, start);
    } 

     // Initializing the table of all the paths so that the length of the shortest distance for each node is the Integer.MAX_VALUE
    public ArrayList<Paths> intitializeTable(Graph graph, Node start) {
        ArrayList<Paths> table = new ArrayList<Paths>();
        for (int i = 0; i < graph.getNodes().size(); i++) {
            // We want the starting node to have a shortest distance of 0, so the if statement is to find the the starting node in the graph
            // and make it 0 
            if (start.getName().equals(graph.getNodes().get(i).getName()))
                table.add(new Paths(graph.getNodes().get(i), 0, (new Node())));
            else {
                table.add(new Paths(graph.getNodes().get(i), Integer.MAX_VALUE, (new Node()))); 
            }      
        }
        return table;
    }
   
    // Helper method to find what the index of the node is in the list of paths
    // The names of the nodes are their index as they were inputted into the graph, 
    // so we can easily find their index in the table by subtracting one from their name
    public int findTableIndex(Node node) {
       return Integer.parseInt(node.getName()) - 1;
    }

    // Helper method to determine if a node has already been visited
    public boolean checkIfUnvisited(Node node, ArrayList<Node> unvisited) {
        for (int i = 0; i < unvisited.size(); i++) {
            if (node.getName().equals(unvisited.get(i).getName())) {
                return true;
            }
        }
        return false;
    }

    // Helper method to find what the index of the node is in the unvisited list
    public int findUnvisitedIndex(Node node, ArrayList<Node> unvisited) {
        for (int i = 0; i < unvisited.size(); i++) {
            if (node.getName().equals(unvisited.get(i).getName()))
                return i;
        }
        return -1;
    }

    // Determines what node is both unvisited and has the current shortest distance to it
    public Node getNextCurrent(ArrayList<Node> unvisited, ArrayList<Paths> table) {
        int min = Integer.MAX_VALUE;
        Node node = new Node(); 
        for (int i = 0; i < unvisited.size(); i++) {
            Paths path = table.get(findTableIndex(unvisited.get(i)));
            if (path.getLength() < min) {
                min = path.getLength();
                node = path.getNode();
            }
        }
        return node;
    }

    // A recursive function that generates the output
    // The main parts of Dijkstra's Algorithm take place here
    public ArrayList<Paths> performAlgo(ArrayList<Node> unvisited, ArrayList<Paths> table, Node current) {
        // Terminates the function if we have visited all the nodes so it doesn't go on forever
        if (unvisited.size() == 0) {
            return table;
        }
        // Calculates the distance of all the unvisited nodes that connect to the current node
        // and updates the table if any of them are shorter than the distances in the table of paths
        else {
            int indexCurrent = findTableIndex(current);
            for (int i = 0; i < current.getConnections().size(); i++) {
                if (checkIfUnvisited(current.getConnections().get(i).getVertex(), unvisited)) {
                    int distance = current.getConnections().get(i).getWeight() + table.get(indexCurrent).getLength();
                    if (distance < table.get(findTableIndex(current.getConnections().get(i).getVertex())).getLength()) {
                        table.get(findTableIndex(current.getConnections().get(i).getVertex())).changeLength(distance);
                        table.get(findTableIndex(current.getConnections().get(i).getVertex())).changePrevNode(current);
                    }
                }
            }
            unvisited.remove(findUnvisitedIndex(current, unvisited));
            // Uses recursion to execute the performAlgo function with a smaller unvisted list
            return performAlgo(unvisited, table, getNextCurrent(unvisited, table));
        }
    }

    // A recursive function that finds the shortest path between two nodes in the graph using Dijkstra's Algorithm
    public String findShortestPath(Node start, Node end, ArrayList<Paths> table, String str) {
        int startIndex = findTableIndex(start);
        int endIndex = findTableIndex(end);

        if(startIndex != endIndex)
            return str += table.get(endIndex).getNode().getName() + " <- " + findShortestPath(start, table.get((findTableIndex(table.get(endIndex).getPrevNode()))).getNode(), table, str);
        else
            return (str += table.get(endIndex).getNode().getName());
    }


    // A toString function to display all paths in a table with three columns
    // The first column is the nodes
    // The second column is the length of the shortest distance from the starting node to that node
    // The third column is the previous node needed to make the shortest path to it
    // If you follow all the previous paths you will create the shortest path to the node
    // Ex: If you wanted to find the shortest path to node X from starting node Z, 
    //     you would look at the previous node section of the table for X. Let's say it says Y, 
    //     then you would look at the previous node secion of the table for Y, which says Z.
    //     That means the shortest path is Z -> Y -> X.
    public String toString(ArrayList<Paths> table) {
        String str = "Node         Distance From Starting Node         Previous Node      \n";
        for (int i = 0; i < table.size(); i++) {
            str += table.get(i).toString() +"\n";
        }
        return str;
    }

}
