package nz.ac.auckland.se281.Graphs;

import java.util.ArrayList;
import java.util.Collections;
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
    Map<Node,Node> parentMap = new HashMap<>();
    List<Node> visited = new ArrayList<>();
    Queue<Node> queue = new LinkedList<>();
    List<Node> path = new LinkedList<>();

    parentMap.put(root, null);
    queue.add(root);
    visited.add(root);
    //BFS implementation
    while (!queue.isEmpty()) {
      Node currentNode = queue.poll();

      //ends the loop when target found 
      if (currentNode.equals(target)){
        //goes backwards through parentMap to find the path you took
        for (Node n = target ; n != null ; n = parentMap.get(n)){
            path.add(n);

        }
        Collections.reverse(path);
        return path;
      }
      //checks if we have visited the adjacent nodes.
      for (Node nextNode : adjNodes.get(currentNode)) {
        if (!visited.contains(nextNode)) {
          visited.add(nextNode);
          queue.add(nextNode);
          parentMap.put(nextNode, currentNode); //for making path later
        }
      }
    }

    return null;
  }
}
