package nz.ac.auckland.se281;

import static nz.ac.auckland.se281.Main.Command.valueOf;

import java.util.LinkedList;
import java.util.List;

import nz.ac.auckland.se281.Graphs.Graph;
import nz.ac.auckland.se281.Graphs.Node;

/** This class is the main entry point. */
public class MapEngine {
  List<Node> nodeList = new LinkedList<>();

  public MapEngine() {
    // add other code here if you wan
    loadMap(); // keep this mehtod invocation
  }
  //turns a string into a node;
  public Node fromString(String input){
        for(Node node : nodeList){
          if(node.getName().equalsIgnoreCase(input)){
            return node;
          }
        }
        return null;
    }
  

  /** invoked one time only when constracting the MapEngine class. */
  private void loadMap() {
   String[] holdCountries = null; 
   String[] holdAdj = null;
   
   
   Graph graph = new Graph();
   


    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();
    
    //makes a list of all the countries as nodes
    for (String i : countries){
      
      holdCountries = i.split(",");
      nodeList.add(new Node(holdCountries[0], holdCountries[1],Integer.valueOf(holdCountries[2])));

    }

    // for (String i : adjacencies){
    //   holdAdj = i.split(",");
    //   for( String j : holdAdj){
    //     graph.addEdge(holdAdj[0], j);

    //   }
      

    // }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {}

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}
