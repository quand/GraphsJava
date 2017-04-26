package models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Graph {
    private Node[] nodes;
    private Edge[] edges;
    private boolean[] used;

    private Graph(int numberOfNodes, int numberOfEdges) {
        nodes = new Node[numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++)
            nodes[i] = new Node(i + 1);
        edges = new Edge[numberOfEdges];
        for (int i = 0; i < numberOfEdges; i++)
            edges[i] = new Edge(i);
        used = new boolean[numberOfNodes];
    }

    public static Graph createGraph(String input) {
        Graph graph;
        String[] array = new String[0];
        try {
            List<String> list = new ArrayList<>();
            Scanner in = new Scanner(new File(input));
            while (in.hasNextLine())
                list.add(in.nextLine());
            array = list.toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }


        graph = new Graph(Integer.parseInt(array[0]), array.length - 1);
        for (int i = 1; i < array.length; i++) {
            StringTokenizer st = new StringTokenizer(array[i], " ", false);
            int k = 0;
            int[] m = new int[4];
            while (st.hasMoreTokens()) {
                m[k] = Integer.parseInt(String.valueOf(st.nextToken()));
                k++;
            }
            graph.nodes[m[0]-1].connectOrientedNodes(graph.nodes[m[1]-1]);
            graph.edges[i - 1].connectNodes(graph.nodes[m[0] - 1], graph.nodes[m[1] - 1], m[2],m[3]);
        }
        return graph;
    }

    public int[][] createMatrixWeight() {
        int numberOfNodes = this.nodes.length;
        int[][] matrixWeight = new int[numberOfNodes][numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                matrixWeight[i][j] = (int) 2e6;
            }
            matrixWeight[i][i] = 0; //maybe should be maxValue insted of 0
        }
        for (int i = 0; i < this.edges.length; i++) {
            matrixWeight[this.edges[i].getNodeFrom().getId() - 1][this.edges[i].getNodeTo().getId() - 1] = this.edges[i].getLength();
        }
        return matrixWeight;
    }

    private int[][] algorithmFloyd(int[][] matrixWeight) {
        int numberOfNodes = this.nodes.length;
        int[][] matrixDistances = new int[numberOfNodes][numberOfNodes];

        for (int i = 0; i < numberOfNodes; i++) {
            System.arraycopy(matrixWeight[i], 0, matrixDistances[i], 0, numberOfNodes);

        }
        for (int i = 0; i < numberOfNodes; i++) matrixDistances[i][i] = 0;
        for (int k = 0; k < numberOfNodes; k++) {
            for (int i = 0; i < numberOfNodes; i++) {
                for (int j = 0; j < numberOfNodes; j++) {
                    matrixDistances[i][j] = Math.min(matrixDistances[i][j], matrixDistances[i][k] + matrixDistances[k][j]);
                }
            }
        }
        System.out.print("Algoritm Floyda:");
        System.out.println();
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                System.out.print(matrixDistances[i][j]+" ");
            }
            System.out.println();
        }
        return matrixDistances;
    }

    public Node mainInsideMedian(int[][] matrixWeight) {
        Node mainMedian;
        int[][] matrixDistancesEdgeToNode = new int[this.edges.length][this.nodes.length];
        int[] median = new int[this.nodes.length];
        int[][] matrixDistances = algorithmFloyd(matrixWeight);

        for (int i = 0; i < this.edges.length; i++) {
            for (int j = 0; j < this.nodes.length; j++) {
                matrixDistancesEdgeToNode[i][j] = matrixWeight[this.edges[i].getNodeFrom().getId() - 1][this.edges[i].getNodeTo().getId() - 1] + matrixDistances[this.edges[i].getNodeTo().getId() - 1][j];
            }

        }

        System.out.print("Matrix Distances Edge to Node:");
        System.out.println();
        for (int i = 0; i < this.edges.length; i++) {
            for (int j = 0; j < this.nodes.length; j++) {
                System.out.print(matrixDistancesEdgeToNode[i][j]+" ");
            }
            System.out.println();
        }
        for (int i = 0; i < this.nodes.length; i++) {
            for (int j = 0; j < this.edges.length; j++)
                median[i] += matrixDistancesEdgeToNode[j][i] * this.edges[j].getVes();
        }
        int min = 0;

        System.out.print("Median's:");
        System.out.println();
        for (int i = 1; i < this.nodes.length; i++){

            if (median[min] > median[i]) min = i;
        }
        for (int i = 0; i < this.nodes.length; i++) System.out.print(median[i]+" ");
        System.out.println();
        mainMedian = this.nodes[min];
        return mainMedian;
    }

    public void depthFirstSearch(int pos) {

        dfs(this.nodes[pos-1].getId());
    }

    private void dfs(int pos) {
        used[pos-1] = true;
        System.out.println(pos);
        for (Node next : this.nodes[pos-1].getIncidentNodes())
            if (!used[next.getId()-1])
                dfs(next.getId());
    }

    public void breadthFirstSearch() {
    }

    /*
    Данно: Граф G=<V, E>, представленный списками ЗАПИСЬ[V], v V.
    Вывести множество фундаментальных циклов Ф графа G, используя алгоритм нахождения множества фундаментальных циклов графа.
    */
}
