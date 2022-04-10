public class Edge {
    private String source, destination, weight;

    public String getSource() {
        return source;
    }


    public String getDestination() {
        return destination;
    }

    public String getWeight() {
        return weight;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Edge(String source, String destination, String weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }



    public void setSource(String source) {
        this.source = source;
    }
    public void printEdge(){
        System.out.print(source + " (" + weight + ") " + destination + "  |  ");
    }
}
