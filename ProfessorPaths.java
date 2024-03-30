package hw5;

import hw4.Graph;
import hw4.Node;

import java.io.IOException;
import java.util.*;

public class ProfessorPaths {
    private Graph RPI;

    private ArrayList<Node> queue;

    private Map<Node, List<String>> next;
    public void createNewGraph(String filename) {
        try {
            String file = "data/courses.csv"; //rename to arg[0] later for testing new files
            Map<String, Set<String>> profsTeaching = new HashMap<String, Set<String>>();
            Set<String> profs = new HashSet<String>();
            ProfessorParser.readData(file, profsTeaching, profs);
            int countEdges=0; //delete
            RPI = new Graph();
            for (String i : profs) {
                RPI.addNode(new Node(i));
            }
            for (Map.Entry<String,Set<String>> entry : profsTeaching.entrySet()) {
                int foundP=-1;
                int foundC=-1;

                for (String parentNode : entry.getValue()) {
                    for (String childNode : entry.getValue()) {
                        if (!Objects.equals(parentNode, childNode)) {
                            for (int i=0; i<RPI.getNodes().size(); i++) {
                                if (parentNode.equals(RPI.getNodes().get(i).getName())) {
                                    foundP=i;
                                }
                                if (childNode.equals(RPI.getNodes().get(i).getName())) {
                                    foundC=i;
                                }
                            }
                            if (foundP!=-1 && foundC!=-1) {
                                RPI.addEdge(RPI.getNodes().get(foundP), RPI.getNodes().get(foundC), entry.getKey());
                                countEdges++; //delete
                            }
                        }
                    }
                }
            }
            System.out.println(countEdges);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String printPath(List<String> pathNodes, Node start, Node dest) {
        Node prev = start;
        String prevW="";
        String path="path from " +start.getName() + " to "+dest.getName()+":\n";
        for (String i : pathNodes) {
            path+=prev.getName() + " to ";
//            for (int j=0; j<prev.getWeight().size(); j++) {
//                if (prev.getNeighbors().get(j).getName()==i) {
//                    prevW=prev.getWeight().get(j);
//                }
//            }
            prevW=pathNodes.get((pathNodes.indexOf(prev.getName())+1));
            path+= i+ " via " + prevW +"\n";
            for (int k = 0; k < RPI.getNodes().size(); k++) {
                if (i.equals(RPI.getNodes().get(k).getName())) {
                    prev = RPI.getNodes().get(k);
                }
            }
        }
        return path;
    }
    public String findPath(String node1, String node2) {
        //return alphabetically sorted path
        //when picking a path, choose the alphabetically first professor for each next step
        //if both professors in each step taught multiple classes together, print the edge with alphabetically first
        //return string is formatted according the hw document, that can be fixed later, first store the path in a different container
        //use pseudocode given to code algorithm
        int foundP = -1;
        int foundC = -1;
        queue = new ArrayList<Node>();
        next = new HashMap<Node, List<String>>();
        Node upNext;
        String path;
        for (int i = 0; i < RPI.getNodes().size(); i++) {
            if (node1.equals(RPI.getNodes().get(i).getName())) {
                foundP = i;
            }
            if (node2.equals(RPI.getNodes().get(i).getName())) {
                foundC = i;
            }
        }
        if (foundP == -1 && foundC == -1) {
            return "unknown professor " + node1 + "\n" + "unknown professor " + node2;
        } else if (foundP == -1) {
            return "unknown professor " + node1;

        } else if (foundC == -1) {
            return "unknown professor " + node2;
        } else {
            Node start = RPI.getNodes().get(foundP);
            Node dest = RPI.getNodes().get(foundC);
            queue.add(start);
            next.put(start, new ArrayList<>());
            while (!queue.isEmpty()) {
                upNext=queue.getFirst();
                queue.removeFirst();
                if (upNext.equals(dest)) {
                    List<String> pathNodes = next.get(upNext);
                    path = printPath(pathNodes, start, dest);
                    return path;
                }
                else {
                    Iterator<String> pathway =  RPI.listChildren(upNext.getName());
                    while (pathway.hasNext()) {
                        String temp=pathway.next();
                        String child=temp.substring(0, temp.indexOf("("));
                        String edge=temp.substring(temp.indexOf("(")+1, temp.indexOf(")"));
                        Node check = new Node();
                        for (int k = 0; k < RPI.getNodes().size(); k++) {
                            if (child.equals(RPI.getNodes().get(k).getName())) {
                                check = RPI.getNodes().get(k);
                            }
                        }
                        if (!next.containsKey(check)) {
                            List<String> p = new ArrayList<>();
                            if (next.get(check)!=null) {
                                p=next.get(check);
                            }
                            p.add(child);
                            p.add(edge);
                            next.put(check, p);
                            queue.add(check);
                        }
                    }
//                    for (Node i : upNext.getNeighbors()) {
//                        if (!next.containsKey(i)) {
//                            ArrayList<Node> p = next.get(upNext);
//                            p.add(i);
//                            next.put(i, p);
//                            queue.add(i);
//                        }
//                    }
                }
            }
        }
        return "path from " + node1 + " to " + node2 + ":\n" + "no path found";
    }

    public static void main(String[] arg) {
        String file = "data/test1.csv"; //rename to arg[0] later for testing new files
        ProfessorPaths newPath = new ProfessorPaths();
        newPath.createNewGraph(file);
        System.out.println(newPath.findPath("Mohammed J. Zaki", "Wilfredo Colon"));
    }
}

