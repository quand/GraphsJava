package models;

import java.util.ArrayList;

public class Node {
    protected int id;
    private ArrayList<Node> incidentNodes = new ArrayList<>();

    Node(int id) {
        this.id = id;
    }

    private void connectNonOrientedNodes(Node node) {
        this.incidentNodes.add(node);
        node.incidentNodes.add(this);
    }

    private void connectOrientedNodes(Node node) {
        this.incidentNodes.add(node);
    }

    public int getId() {
        return id;
    }
}
