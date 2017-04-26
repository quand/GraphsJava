import models.Graph;
import models.Node;

public class MainWindow {
    public static void main(String[] args) {
        String FILE_NAME = "C:\\Graphs\\src\\graph.txt";
        Graph graph = Graph.createGraph(FILE_NAME);
        //graph.depthFirstSearch(1);
        int matrix[][] = graph.createMatrixWeight();
        Node mainMedian = graph.mainInsideMedian(matrix);
        System.out.print("Main median: "+mainMedian.getId());
    }

}
