package advDS.graph;

import java.util.*;

public class Graph {
    private Map<Node, List<Edge>> adjList = new HashMap<>();
    private List<Edge> allEdges = new ArrayList<>();

    public void addNode(Node node) {
        adjList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(Node source, Node dest, double weight) {
        Edge e = new Edge(source, dest, weight);
        adjList.get(source).add(e);
        allEdges.add(e);
    }

    public List<Edge> getNeighbors(Node node) {
        return adjList.getOrDefault(node, new ArrayList<>());
    }

    public Set<Node> getNodes() {
        return adjList.keySet();
    }

    public List<Edge> getAllEdges() {
        return allEdges;
    }
}