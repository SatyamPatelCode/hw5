package hw4;

import java.util.Iterator;
import java.util.ArrayList;

/**
 * Rep Invariant: No null variables for any variable within the graph, including node variables
 *
 * Abstraction Function: Graph is represented as an ArrayList of nodes. These nodes store a name, an ArrayList of nodes
 * to represent child nodes, and an ArrayList of edge labels to represent the edges. Although these are separate array
 * lists, the index of a child corresponds to the same index of an edge label. This works because the edges are directed.
 */
public class Graph {
    private String name;
    private ArrayList<Node> nodes;

    /**
     *
     * @effects Constructs a new Graph with an unknown node
     */
    public Graph() {
        name="undefined";
        nodes=new ArrayList<Node>();
        //checkRep();
    }

    /**
     * @param na The name of the graph
     * @param nd The list of Nodes that make up the graph
     * @requires n!=null
     * @effects Constructs a new Graph with an ArrayList of all Nodes
     */
    public Graph(String na, ArrayList<Node> nd) {
        name = na;
        nodes=nd;
        //checkRep();
    }
    public int sizeNodes() {
        return nodes.size();
    }
    /**
     *
     * @param n The constant Node which will be added to the Graph ArrayList
     * @modifies this Graph's nodes variable
     * @requires n not in nodes and not null
     */
    public void addNode(Node n) {
        nodes.add(n);
        //checkRep();
    }

    /**
     *
     * @param n1 The Node that will be the start position of the edge
     * @param n2 The Node that will be the end position of the edge
     * @param w The edge weight
     * @modifes nodes will be updated using Node methods
     * @requires n1 in nodes, n2 in nodes, w is not null
     */
    public void addEdge(Node n1, Node n2, String w) {
        for (int i=0; i<nodes.size(); i++) {
            if (n1.equals(nodes.get(i))) {
                nodes.get(i).setNeighbors(n2, w);
            }
        }
        //checkRep();
    }

    /**
     *
     * @return Iterator to beginning of nodes list
     */
    public Iterator<Node> listNodes() {
        return nodes.iterator();
    }

    /**
     *
     * @param n String that will be reference when searching for children
     * @return an Iterator to a String of the neighbors and weights of the specified node
     * @requires n in nodes
     */
    public Iterator<String> listChildren(String n) {
        ArrayList<String> children = new ArrayList<String>();
        ArrayList<Node> neighbor;
        ArrayList<String> weight;
        for (int i=0; i<nodes.size(); i++) {
            if (n.equals(nodes.get(i).getName())) {
                neighbor=nodes.get(i).getNeighbors();
                weight = nodes.get(i).getWeight();
                for (int j=0; j<neighbor.size(); j++) {
                    children.add(neighbor.get(j).getName() + "(" + weight.get(j) + ")");
                }
            }
        }
        children.sort(String::compareToIgnoreCase);
        return children.iterator();
    }

    /**
     *
     * @return all nodes in this graph
     */
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    /**
     *
     * @return name of graph
     */
    public String getName() {
        return name;
    }

//    /**
//     *
//     * @throws RuntimeException
//     */
//    private void checkRep() throws RuntimeException {
//        if (nodes == null) {
//            throw new RuntimeException("nodes == null");
//        }
//        for (Node node : nodes) {
//            if (node.equals(null)) {
//                throw new RuntimeException("node is null");
//            }
//            for (int i=0; i<node.getNeighbors().size(); i++) {
//                if (node.getNeighbors().get(i).equals(null)) {
//                    throw new RuntimeException("neighbor is null");
//                }
//                if (node.getWeight().get(i).equals(null)) {
//                    throw new RuntimeException("weight is null");
//                }
//            }
//            if (node.getName().equals(null)) {
//                throw new RuntimeException("name is null");
//            }
//        }
//    }

}
