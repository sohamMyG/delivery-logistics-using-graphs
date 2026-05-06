package advDS.algorithms;

import advDS.graph.*;
import java.util.*;

public class AStarAlgorithm implements PathFindingAlgorithm {

    public PathResult findPath(Graph graph, Node start, Node goal) {

        Map<Node, Double> gScore = new HashMap<>();
        Map<Node, Double> fScore = new HashMap<>();
        Map<Node, Node> prev = new HashMap<>();

        PriorityQueue<NodeDistance> open = new PriorityQueue<>();

        for (Node n : graph.getNodes()) {
            gScore.put(n, Double.POSITIVE_INFINITY);
            fScore.put(n, Double.POSITIVE_INFINITY);
        }

        gScore.put(start, 0.0);
        fScore.put(start, heuristic(start, goal));

        open.add(new NodeDistance(start, fScore.get(start)));

        while (!open.isEmpty()) {
            Node current = open.poll().node;

            if (current.equals(goal)) break;

            for (Edge edge : graph.getNeighbors(current)) {
                Node neighbor = edge.destination;

                double tentative = gScore.get(current) + edge.weight;

                if (tentative < gScore.get(neighbor)) {
                    prev.put(neighbor, current);
                    gScore.put(neighbor, tentative);
                    double f = tentative + heuristic(neighbor, goal);
                    fScore.put(neighbor, f);
                    open.add(new NodeDistance(neighbor, f));
                }
            }
        }

        return new PathResult(reconstruct(prev, start, goal), gScore.get(goal));
    }

    private double heuristic(Node a, Node b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    private List<Node> reconstruct(Map<Node, Node> prev, Node start, Node goal) {
        List<Node> path = new ArrayList<>();
        for (Node at = goal; at != null; at = prev.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    static class NodeDistance implements Comparable<NodeDistance> {
        Node node;
        double priority;

        NodeDistance(Node n, double p) {
            node = n;
            priority = p;
        }

        public int compareTo(NodeDistance o) {
            return Double.compare(this.priority, o.priority);
        }
    }
}