package nz.ac.auckland.se281.Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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

  public List<Node> getShortestRoute(Node root, Node target) {
    List<Node> visited = new ArrayList<>();
    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    visited.add(root);
    //BFS implementation
    while (!queue.isEmpty()) {
      Node currentNode = queue.poll();
      //ends the loop when target found 
      if (currentNode.equals(target)){
        return visited;
      }
      //checks if we have visited the adjacent nodes.
      for (Node nextNode : adjNodes.get(currentNode)) {
        if (!visited.contains(nextNode)) {
          visited.add(nextNode);
          queue.add(nextNode);
        }
      }
    }

    return null;
  }
}
