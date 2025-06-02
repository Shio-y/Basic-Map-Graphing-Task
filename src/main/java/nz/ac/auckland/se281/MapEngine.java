package nz.ac.auckland.se281;

import static nz.ac.auckland.se281.Main.Command.valueOf;

import java.util.LinkedList;
import java.util.List;

import nz.ac.auckland.se281.Graphs.Node;

/** This class is the main entry point. */
public class MapEngine {

  public MapEngine() {
    // add other code here if you wan
    loadMap(); // keep this mehtod invocation
  }

  /** invoked one time only when constracting the MapEngine class. */
  private void loadMap() {
   String[] holdCountries = null; 
   List<Node> nodeList = new LinkedList<>();


    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();
    
    for (String i : countries){
      //makes a list of all the countries as nodes
      holdCountries = i.split(",");
      nodeList.add(new Node(holdCountries[0], holdCountries[1],Integer.valueOf(holdCountries[2])));

    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {}

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}
