package advDS.graph;

public class Edge {
    public Node source;
    public Node destination;
    public double weight;
    public double baseWeight;

    public Edge(Node source, Node destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        this.baseWeight = weight;
    }
}