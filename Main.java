public class Main {

    public static void main(String[] args) {
        Graph myGraph = new Graph();
        initGraph(myGraph);

        System.out.println(myGraph.hasPathDPS(1,8));
        System.out.println(myGraph.hasPathDPS(1,10));
    }

    //TODO
    //Ideally want to generate a random graph in the future.
    //Then need a way of presenting it.
    private static void initGraph(Graph graph) {
        createNodes(graph);

        //Add some connections, hardcoded for now.
        graph.addAdjacentNode(1,6);
        graph.addAdjacentNode(6,5);
        graph.addAdjacentNode(5,7);
        graph.addAdjacentNode(7,8);
        graph.addAdjacentNode(1,2);
        graph.addAdjacentNode(2,5);
        graph.addAdjacentNode(2,4);
        graph.addAdjacentNode(4,3);
    }

    private static void createNodes(Graph graph) {
        //Add some nodes to the graph.
        for (int i = 1; i < 11 ; i++) {
            graph.addNode(i);
        }
    }
}
