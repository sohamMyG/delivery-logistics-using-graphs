package advDS.algorithms;

import advDS.graph.*;
import java.util.*;

public class DijkstraAlgorithm implements PathFindingAlgorithm {

    public PathResult findPath(Graph graph, Node start, Node goal) {

        Map<Node, Double> dist = new HashMap<>();
        Map<Node, Node> prev = new HashMap<>();

        PriorityQueue<NodeDistance> pq = new PriorityQueue<>();

        for (Node node : graph.getNodes()) {
            dist.put(node, Double.POSITIVE_INFINITY);
        }

        dist.put(start, 0.0);
        pq.add(new NodeDistance(start, 0.0));

        while (!pq.isEmpty()) {
            NodeDistance current = pq.poll();

            if (current.distance > dist.get(current.node)) continue;

            if (current.node.equals(goal)) break;

            for (Edge edge : graph.getNeighbors(current.node)) {
                Node neighbor = edge.destination;
                double newDist = dist.get(current.node) + edge.weight;

                if (newDist < dist.get(neighbor)) {
                    dist.put(neighbor, newDist);
                    prev.put(neighbor, current.node);
                    pq.add(new NodeDistance(neighbor, newDist));
                }
            }
        }

        return new PathResult(reconstruct(prev, start, goal), dist.get(goal));
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
        double distance;

        NodeDistance(Node n, double d) {
            node = n;
            distance = d;
        }

        public int compareTo(NodeDistance o) {
            return Double.compare(this.distance, o.distance);
        }
    }
}
