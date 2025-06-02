package nz.ac.auckland.se281.Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<Node, List<Node>> adjNodes;
    
    public Graph(){
        this.adjNodes = new HashMap<>();
    }

    public void addNodes(Node node){
        adjNodes.putIfAbsent(node, new ArrayList<Node>());
        
    }
    public void addEdge(Node root, Node target){
        adjNodes.get(root).add(target);
    }
    
    
}
