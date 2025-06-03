package nz.ac.auckland.se281.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import nz.ac.auckland.se281.MessageCli;

public class Graph {
  private Map<Node, List<Node>> adjNodes;

  public Graph() {
    this.adjNodes = new HashMap<>();
  }

  public void addNodes(Node node) {
    this.adjNodes.putIfAbsent(node, new ArrayList<Node>());
  }

  public void addEdge(Node root, Node target) {
    this.addNodes(root);
    this.adjNodes.get(root).add(target);
  }

  public List<Node> getAdjNode(Node key) {
    return adjNodes.get(key);
  }

  // finds the shortest route from start to end
  public List<Node> getShortestRoute(Node root, Node target) {
    Map<Node, Node> parentMap = new HashMap<>();
    List<Node> visited = new ArrayList<>();
    Queue<Node> queue = new LinkedList<>();
    List<Node> path = new LinkedList<>();

    parentMap.put(root, null);
    queue.add(root);
    visited.add(root);
    // BFS implementation
    while (!queue.isEmpty()) {
      Node currentNode = queue.poll();

      // ends the loop when target found
      if (currentNode.equals(target)) {
        // goes backwards through parentMap to find the path you took
        for (Node n = target; n != null; n = parentMap.get(n)) {
          path.add(n);
        }
        Collections.reverse(path);
        return path;
      }
      // checks if we have visited the adjacent nodes and adds to list if not
      for (Node nextNode : adjNodes.get(currentNode)) {
        if (!visited.contains(nextNode)) {
          visited.add(nextNode);
          queue.add(nextNode);
          parentMap.put(nextNode, currentNode); // for making path later
        }
      }
    }

    return null;
  }

  // calculates the amount of fuel spent excluding source and destination and prints message.
  public void printTotalFuel(List<Node> path) {
    int totalFuel = 0;
    for (int i = 1; i < path.size() - 1; i++) {
      totalFuel += path.get(i).getCost();
    }
    MessageCli.FUEL_INFO.printMessage(Integer.toString(totalFuel));
  }

  public String printContinentsVisited(List<Node> path) {
    Map<String, Integer> continentMap = new HashMap<>();
    List<String> finalList = new LinkedList<>();
    List<String> continentList = new LinkedList<>();
    String currentContinent;
    String[] hold;
    int max = -1;
    int currentIndex = 0;
    int currentNumber;
    int count = -1;
    int currentFuel;

    // counts the amount of fuel used in each continent and adds it to a hashmap
    for (int i = 1; i < path.size() - 1; i++) {
      currentContinent = path.get(i).getRegion();
      currentFuel = path.get(i).getCost();

      if (continentMap.containsKey(path.get(i).getRegion())) {
        // adds the new fuel value to the old one if so
        continentMap.put(currentContinent, currentFuel + continentMap.get(currentContinent));
      } else {
        // adds a new entry into the hashmap if not
        continentMap.put(currentContinent, currentFuel);
      }
    }
    // finds the regions that were passed through
    for (int i = 0; i < path.size(); i++) {
      if (!continentList.contains(path.get(i).getRegion())) {
        continentList.add(path.get(i).getRegion());
      }
    }
    // assembles a list in the right format
    for (String i : continentList) {
      finalList.add(i + " (" + Integer.toString(continentMap.getOrDefault(i, 0)) + ")");
    }

    MessageCli.CONTINENT_INFO.printMessage(finalList.toString());
    // takes the list of total fuel costs and splits it to find which most fuel spent on a continent
    for (String i : finalList) {
      hold = i.split("[\\(\\)]");
      currentNumber = Integer.valueOf(hold[1]);
      count++;

      if (currentNumber > max) {
        max = currentNumber;
        currentIndex = Integer.valueOf(count);
      }
    }
    return finalList.get(currentIndex);
  }
}
