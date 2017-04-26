package models;

public class Edge {
    private int id;
    private Node nodeFrom;
    private Node nodeTo;
    private int ves;
    private int length;

    Edge(int id) {
        this.id = id;
    }

    void connectNodes(Node from, Node to, int len, int ves) {
        nodeFrom = from;
        nodeTo = to;
        this.length=len;
        this.ves = ves;
    }

    Node getNodeFrom() {
        return nodeFrom;
    }

    Node getNodeTo() {
        return nodeTo;
    }

    int getVes() {
        return ves;
    }

    int getLength() {
        return length;
    }

    public Node getOtherNode(Node first) {
        if (first == nodeFrom)
            return nodeFrom;
        else if (first == nodeTo) return nodeTo;
        else return null;
    }
}
