package nz.ac.auckland.se281.Graphs;



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
    
    public String getRegion(){
        return this.region;
    }
    public int getCost(){
        return this.cost;

    }
    @Override
    public String toString() {
        return getName();
    }
    
    
}
