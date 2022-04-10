import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Graph {
    public LinkedHashMap<String, ArrayList<Edge>> getAdjList() {
        return adjList;
    }

    private LinkedHashMap<String, ArrayList<Edge>> adjList;
    private ArrayList<String> vertices;


    public Graph(LinkedHashMap<String, ArrayList<Edge>> adjList, ArrayList<String> vertices) {
        this.adjList = adjList;
        this.vertices = vertices;
    }

    public void addEdge(String userInput) {
        String[] input = userInput.split(" ");
        String source = input[0];
        String destination = input[2];
        String weight = input[1];

        if (!vertices.contains(source)) {
            vertices.add(source);
            adjList.put(source, new ArrayList<Edge>());
            adjList.get(source).add(new Edge(source, destination, weight)); //find the source in adjList and create a new edge for it
        } else {
            adjList.get(source).add(new Edge(source, destination, weight));
        }

        if (!vertices.contains(destination)) {
            vertices.add(destination);
            adjList.put(destination, new ArrayList<Edge>());
        }


    }

    public void printGraph() {
        for (String s : adjList.keySet()) {
            System.out.print(s + " : ");
            for (Edge e : adjList.get(s))
                e.printEdge();
            System.out.println();
        }
    }


}

//q0 a q2