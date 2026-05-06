package advDS.algorithms;


import java.util.List;

import advDS.graph.Node;

public class PathResult {
    public List<Node> path;
    public double cost;

    public PathResult(List<Node> path, double cost) {
        this.path = path;
        this.cost = cost;
    }
}