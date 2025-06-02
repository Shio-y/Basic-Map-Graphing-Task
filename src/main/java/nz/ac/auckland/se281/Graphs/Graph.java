package nz.ac.auckland.se281.Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<Node, List<String>> adjNodes;
    
    public Graph(){
        this.adjNodes = new HashMap<>();
    }

    public void addNodes(Node node){
        this.adjNodes.putIfAbsent(node, new ArrayList<String>());
        
    }
    public void addEdge(Node root, String target){
        this.addNodes(root);
        this.adjNodes.get(root).add(target);
    }
    
    
}
