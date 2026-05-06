package advDS.graph;

import java.util.*;

public class GraphGenerator {

    public static Graph generate(int numNodes, int edgesPerNode) {
        Graph g = new Graph();
        Random rand = new Random();

        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < numNodes; i++) {
            Node n = new Node(i, "N" + i, rand.nextInt(100), rand.nextInt(100));
            g.addNode(n);
            nodes.add(n);
        }

        for (Node n : nodes) {
            for (int i = 0; i < edgesPerNode; i++) {
                Node target = nodes.get(rand.nextInt(numNodes));
                double weight = rand.nextInt(20) + 1;
                g.addEdge(n, target, weight);
            }
        }

        return g;
    }
}