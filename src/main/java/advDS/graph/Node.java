package advDS.graph;

public class Node {
    public int id;
    public String name;
    public double x, y;

    public Node(int id, String name, double x, double y) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return name;
    }
}