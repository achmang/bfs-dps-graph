import java.util.*;

public class Graph {
    //Store Nodes in HashMap
    public HashMap<Integer, Node> nodes = new HashMap<>();

    //Nodes can be identified with an Id
    public static class Node {
        int id;
        //This represents the connection between nodes.
        public LinkedList<Node> adjacentNodes = new LinkedList<>();

        public Node(int id) {
            this.id = id;
        }
    }

    public void addNode(int id) {
        nodes.put(id, new Node(id));
    }

    public Node getNode(int id){
        return nodes.get(id);
    }

    /**
     * Add an adjacent node to the source node.
     * The direction on the graph will go one way.
     * source -> destination.
     * @param source Node we want to be able to travel from.
     * @param destination Node we want to be able to travel to.
     */
    public void addAdjacentNode(int source, int destination) {
        Node s = getNode(source);
        Node d = getNode(destination);
        s.adjacentNodes.add(d);
    }

    //Utility purposes, can call the function with integers instead of node objects.
    public boolean hasPathDPS(int source, int destination) {
        Node s = getNode(source);
        Node d = getNode(destination);

        //Doesn't really need to be accessed outside of this.
        //Makes sure we don't go in circles, keeps track of nodes visited.
        HashSet<Integer> visited = new HashSet<>();
        return hasPathDPS(s, d, visited);
    }

    //More functional.
    private boolean hasPathDPS(Node source, Node destination, HashSet<Integer> visited) {
        //Makes sure we are not going round in circles.
        //This will stop any recursive calls visiting the same node from progressing any further.
        if (visited.contains(source.id)) {
            return false;
        }

        //Keep track of nodes we visit to prevent going round in circles.
        visited.add(source.id);
        //If current node is the same as the destination. Then we have arrived.
        if (source == destination) {
            return true;
        }

        //Run through every adjacent node to the current node.
        for(Node child : source.adjacentNodes){
            //This will set of a chain, and will visit. Every adjacent node of the adjacent nodes.
            //Takes every possible route in the graph.
            //The child node is updated, and destination always stays the same. So if there is a path, at some point.
            //These two params will be the same.
            if (hasPathDPS(child, destination, visited)) {
                return true;
            }
        }
        return false;
    }

    //Utility for the breadth first function.
    public boolean hasPathBFS(int source, int destination) {
        Node s = getNode(source);
        Node d = getNode(destination);

        //Doesn't really need to be accessed outside of this.
        //Makes sure we don't go in circles, keeps track of nodes visited.
        HashSet<Integer> visited = new HashSet<>();
        return hasPathBFS(s, d, visited);
    }

    private boolean hasPathBFS(Node source, Node destination, HashSet<Integer> visited) {
        Queue<Node> nextNode = new LinkedList<>();
        //Add our first node to the queue, makes sense to add as we start here.
        nextNode.add(source);

        while (!nextNode.isEmpty()) {

            //Get the most recent node out of the queue.
            Node node = nextNode.remove();
            //If we have visited this node, just skip.
            if (visited.contains(node.id)) {
                continue;
            }
            visited.add(node.id);

            //If its our destination, then we arrive.
            if (node == destination) {
                return true;
            }

            //Add each child of the node to the queue.
            //This explores all possible routes, instead of
            for (Node child : node.adjacentNodes) {
                nextNode.add(child);
            }
        }

        return false;

    }
}
