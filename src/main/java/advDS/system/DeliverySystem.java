package advDS.system;


import advDS.graph.*;
import advDS.algorithms.*;

import java.util.*;

public class DeliverySystem {

    private Graph graph;
    private Node warehouse;
    private List<Node> deliveryPoints;
    private PathFindingAlgorithm algorithm;

    public DeliverySystem(Graph graph, Node warehouse, List<Node> deliveries) {
        this.graph = graph;
        this.warehouse = warehouse;
        this.deliveryPoints = deliveries;
    }

    public void setAlgorithm(PathFindingAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void simulateTraffic(double factor) {
        for (Edge e : graph.getAllEdges()) {
            e.weight = e.baseWeight * factor;
        }
    }

    public void resetTraffic() {
        for (Edge e : graph.getAllEdges()) {
            e.weight = e.baseWeight;
        }
    }

    public void runDeliveries(boolean verbose) {
        for (Node dest : deliveryPoints) {
            PathResult result = algorithm.findPath(graph, warehouse, dest);

            if (verbose) {
                System.out.println("To " + dest +
                        " → " + result.path +
                        " | Cost: " + result.cost);
            }
        }
    }
    
    public void runDeliveries() {
        for (Node dest : deliveryPoints) {
            PathResult result = algorithm.findPath(graph, warehouse, dest);
        }
    }
}