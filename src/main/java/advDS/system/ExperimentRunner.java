package advDS.system;

import advDS.graph.*;
import advDS.algorithms.*;

import java.util.*;

public class ExperimentRunner {

	public static void run() {

        int[] sizes = {50, 200, 500, 1000};
        int runs = 5;

        System.out.println("\n=== PERFORMANCE EXPERIMENTS ===");

        for (int size : sizes) {

            long dTotal = 0;
            long aTotal = 0;

            for (int r = 0; r < runs; r++) {

                Graph g = GraphGenerator.generate(size, 5);
                List<Node> nodes = new ArrayList<>(g.getNodes());

                Node warehouse = nodes.get(0);
                List<Node> deliveries = nodes.subList(1, Math.min(6, nodes.size()));

                DeliverySystem system = new DeliverySystem(g, warehouse, deliveries);

                // ---------------- Dijkstra ----------------
                system.setAlgorithm(new DijkstraAlgorithm());

                long t1 = System.nanoTime();
                system.runDeliveries(false);
                long t2 = System.nanoTime();

                dTotal += (t2 - t1);

                // ---------------- A* ----------------
                system.setAlgorithm(new AStarAlgorithm());

                long t3 = System.nanoTime();
                system.runDeliveries(false);
                long t4 = System.nanoTime();

                aTotal += (t4 - t3);
            }

            System.out.println("\nGraph Size: " + size);
            System.out.printf("Avg Dijkstra Time: %.3f ms\n", (dTotal / runs) / 1_000_000.0);
            System.out.printf("Avg A* Time:       %.3f ms\n", (aTotal / runs) / 1_000_000.0);
        }
    }
    public static void main(String[] args) {
        ExperimentRunner.run();
    }
}