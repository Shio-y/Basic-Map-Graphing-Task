package nz.ac.auckland.se281;


import java.util.LinkedList;
import java.util.List;
import nz.ac.auckland.se281.Graphs.Graph;
import nz.ac.auckland.se281.Graphs.Node;

/** This class is the main entry point. */
public class MapEngine {
  List<Node> nodeList = new LinkedList<>();
  Graph graph = new Graph();

  public MapEngine() {
    // add other code here if you wan
    loadMap(); // keep this mehtod invocation
  }

  // turns a string into a node;
  public Node fromString(String input) {
    for (Node node : nodeList) {
      if (node.getName().equals(Utils.capitalizeFirstLetterOfEachWord(input))) {
        return node;
      }
    }
    return null;
  }

  // invoked one time only when constracting the MapEngine class.

  private void loadMap() {
    String[] holdCountries = null;
    String[] holdAdj = null;

    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();

    // makes a list of all the countries as nodes
    for (String i : countries) {

      holdCountries = i.split(",");
      nodeList.add(new Node(holdCountries[0], holdCountries[1], Integer.valueOf(holdCountries[2])));
    }
    // adds vertexes and edges to graph
    for (String i : adjacencies) {
      holdAdj = i.split(",");
      for (int j = 1; j < holdAdj.length; j++) {
        graph.addEdge(fromString(holdAdj[0]), fromString(holdAdj[j]));
      }
    }
  }
//checks if the input is a valid country
  public Boolean checkValidCountry(String input) throws InvalidCountryException {
    if (fromString(input) == null) {
      throw new InvalidCountryException();
    }
    return true;
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    boolean valid = false;
    Node currentCountry;
    String userInput = null;

    MessageCli.INSERT_COUNTRY.printMessage();

    // repeatedly asks for user input until user gives valid input
    while (!valid) {
      userInput = Utils.scanner.nextLine().toString();
      try {
        valid = checkValidCountry(userInput);
      } catch (InvalidCountryException e) {
        MessageCli.INVALID_COUNTRY.printMessage(Utils.capitalizeFirstLetterOfEachWord(userInput));
      }
    }
    //placeholder for countries in order to print later
    currentCountry = fromString(userInput);
    String adjNodeString = graph.getAdjNode(currentCountry).toString();
    //print country info
    MessageCli.COUNTRY_INFO.printMessage(
        currentCountry.getName(),
        currentCountry.getRegion(),
        Integer.toString(currentCountry.getCost()),
        adjNodeString);
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}
