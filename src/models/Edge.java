package models;

public class Edge {
    protected int id;
    private Node nodeFrom;
    private Node nodeTo;
    private int ves;

    Edge(int id) {
        this.id = id;
    }

    protected void connectNodes(Node from, Node to, int ves) {
        nodeFrom = from;
        nodeTo = to;
        this.ves = ves;
    }

    public Node getNodeFrom() {
        return nodeFrom;
    }

    public Node getNodeTo() {
        return nodeTo;
    }

    public int getVes() {
        return ves;
    }

    public Node getOtherNode(Node first) {
        if (first == nodeFrom)
            return nodeFrom;
        else if (first == nodeTo) return nodeTo;
        else return null;
    }
}
