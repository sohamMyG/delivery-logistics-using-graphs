package advDS.system;

import advDS.graph.*;
import advDS.algorithms.*;

import java.util.*;

public class DemoRunner {

    public static void runDemo() {

        Graph graph = new Graph();

        // Create nodes (with coordinates for A*)
        Node A = new Node(1, "Warehouse", 0, 0);
        Node B = new Node(2, "B", 2, 2);
        Node C = new Node(3, "C", 4, 2);
        Node D = new Node(4, "Destination", 6, 0);

        graph.addNode(A);
        graph.addNode(B);
        graph.addNode(C);
        graph.addNode(D);

        // Create edges (weighted roads)
        graph.addEdge(A, B, 3);
        graph.addEdge(B, C, 2);
        graph.addEdge(A, C, 6);
        graph.addEdge(C, D, 2);

        System.out.println("=== DEMO GRAPH ===");

        DeliverySystem system = new DeliverySystem(
                graph,
                A,
                Arrays.asList(D)
        );

        // Dijkstra
        system.setAlgorithm(new DijkstraAlgorithm());
        System.out.println("\nDijkstra:");
        system.runDeliveries(true);

        // A*
        system.setAlgorithm(new AStarAlgorithm());
        System.out.println("\nA*:");
        system.runDeliveries(true);
    }
    
    
    public static void main(String[] args) {
        runDemo();
    }
}