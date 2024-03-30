package hw4;

import java.util.ArrayList;

import static java.util.List.copyOf;

/**
 * Rep Invariant: No null variables for any variable within the node
 *
 * Abstraction Function: Node will store the majority of information that represents the graph. Node will hold all
 * connected nodes, edge labels, and the name of the node. This works because the graph is directed and when all printed
 * out, should represent a full graph.
 */
public class Node {
    private String name;
    private ArrayList<String> weights;
    private ArrayList<Node> neighbors;

    /**
     *
     * @effects Constructs a new Node with name "unknown"
     */
    public Node() {
        name = "undefined";
        weights = new ArrayList<String>();
        neighbors = new ArrayList<Node>();
    }
    /**
     *
     * @param n The name in the terms which the new Node equals
     * @requires n!=null
     * @effects Constructs a new Node with the name 'n'
     */
    public Node(String n ) {
        name = n;
        weights = new ArrayList<String>();
        neighbors = new ArrayList<Node>();
    }

    /**
     *
     * @param n The name in the terms which the new Node equals
     * @param w The weights of all edges connecting to other nodes
     * @param ne The nodes that connect to this node
     * @requires n!=null
     * @effects Constructs a new Node with the name 'n'
     */
    public Node(String n, ArrayList<String> w, ArrayList<Node> ne ) {
        name = n;
        weights = w;
        neighbors = ne;
    }

    /**
     *
     * @param w The weight added to the Node weight array
     * @param neighbor The node added to the Node neighbors array
     * @requires w!=null && neighbor!=null
     * @effects Creates a new list containing the old nodes and adds another by creating extra space for it
     */
    public void setNeighbors(Node neighbor, String w) {
        neighbors.add(neighbor);
        weights.add(w);
    }

    /**
     *
     * @return name of this Node
     */
    public String getName() {
        return new String(this.name);
    }

    /**
     * @return all weights of this Node
     */
    public ArrayList<String> getWeight() {
        return new ArrayList<String>(this.weights);
    }

    /**
     *
     * @return all neighbor Nodes of this Node
     */
    public ArrayList<Node> getNeighbors() {
        return new ArrayList<Node>(this.neighbors);
    }
}
