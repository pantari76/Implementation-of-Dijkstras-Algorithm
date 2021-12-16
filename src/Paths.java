// An array of paths is what the output of the algorithm is going to be
public class Paths {

    private Node node;

    // The length is the length of the shortest distance from the starting node
    private int length;

    // The previous node is the node that leads up to the current node in the shortest path
    private Node prevNode;

    public Paths(Node n, int len, Node prevN) {
        node = n;
        length = len;
        prevNode = prevN;

    }

    // Accessor methods
    public Node getNode() {
        return node;
    }

    public int getLength() {
        return length;
    }

    public Node getPrevNode() {
        return prevNode;
    }

    // Mutator methods
    public void changeLength(int newLen) {
        length = newLen;
    }

    public void changePrevNode(Node newNode) {
        prevNode = newNode;
    }

    // A toString to help display the output
    public String toString() {
       return(getNode().getName() + "                     " + getLength() + "                                " + getPrevNode().getName());
    }
}
