package nz.ac.auckland.se281.Graphs;

import java.util.LinkedList;
import java.util.List;

public class Node {
    private String name;
    private String region;
    private int cost;
    

    public Node(String name, String region, int cost){
        this.name = name;
        this.region = region;
        this.cost = cost;
        
    }
    
    public String getName(){
        return this.name;
    }
    
    
}
