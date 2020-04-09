package graph;
import java.util.*;

public class Graph<T,E> {
    private final HashMap<T, HashMap<T,E>> vlist;
    private final ArrayList<T> nodes;
    boolean directed;

    /*
     * Constructor for graph class
     */
    public Graph(boolean directed) {
        this.directed = directed;
        this.nodes = new ArrayList<>();
        this.vlist = new HashMap<>();
    }

    /* 
     * Checks if graph is directed
     */
    public boolean checkDirected() {
        return this.directed;
    }
	
    /*
     * Adds a vertex to the graph
     */
    public boolean addVertex(T vertex) {
        if(this.checkVertex(vertex)) {
            return false;
        }

        nodes.add(vertex);
        vlist.put(vertex, new HashMap<>());
        return true;
    }

    /*
     * Checks if a vertex has been already added to the graph
     */	
    public boolean checkVertex(T vertex) {
        return vlist.containsKey(vertex);
    }

    /*
     * Adds an edge linking two nodes of the graph
     */	
    public boolean addDirectEdge(T vertex1, T vertex2, E weight) {
        if(this.checkEdge(vertex1, vertex2)) {
            return false;
        }
        vlist.get(vertex1).put(vertex2, weight);    
        return true;
    }

    public boolean addUndirectEdge(T vertex1, T vertex2, E weight) {
        if(this.directed) {
            return false;
        } else {
            return this.addDirectEdge(vertex1, vertex2, weight) && this.addDirectEdge(vertex2, vertex1, weight);
        }
    }
    
    /*
     * Checks if an edge between two vertices already exists
     */
    public boolean checkEdge(T v1, T v2) {
        return this.checkVertex(v1) && this.checkVertex(v2) && vlist.get(v1).containsKey(v2);	
    }

    /*
     * Get the weight of an edge
     */	
    public E getEdgeWeight(T v1, T v2) {
        if(!this.checkEdge(v1,v2)) {
            return null;
        }

        return vlist.get(v1).get(v2);
    }
	
    /*
     * Delete a vertex from graph
     */ 
    public boolean removeVertex(T v1) {
        if(this.checkVertex(v1)) {
            ArrayList<T> adj = getAdjacents(v1);

            adj.stream().map((vertex) -> { 
                removeEdge(v1, vertex);
                return vertex;
            }).forEachOrdered((vertex) -> {
                removeEdge(vertex, v1);
            });

            vlist.remove(v1);
            nodes.remove(v1);
            return true;

        } else {
            return false;
        }
    }
    
    /*
     * Delete an edge from  graph
     */	
    public boolean removeEdge(T v1, T v2) {
        if(!this.directed) {
            vlist.get(v1).remove(v2);
            vlist.get(v2).remove(v1);
            return true;	
        } else if (this.directed) {
            vlist.get(v1).remove(v2);	
            return true;	
        } else { 
            return false;	
        }
    }	
    
    /*
     * Get all the adjacents of a specific vertex
     */	
    public ArrayList<T> getAdjacents(T v1) {
        ArrayList<T> adjacents = new ArrayList<>();

        if(!checkVertex(v1) || vlist.get(v1).isEmpty()) {
            return adjacents;
        }

        Set<T> set = vlist.get(v1).keySet();

        set.forEach(adjacents::add);
        return adjacents;
    }
	
    /*
     * Get number of edges of the graph
     */
    public int totEdges() {
        int count = 0;
        for(T vertex : this.getVertices()) {
            count = this.getAdjacents(vertex).stream().map((_item) -> 1).reduce(count, Integer::sum);
        }
        return count;
    }
    
    /*
     * Get number of nodes of the graph
     */
    public int totVertices() {		
        int count = 0;
        count = this.getVertices().stream().map((_item) -> 1).reduce(count, Integer::sum);
        return count;	
    }
    
    /*
     * Returns list of the vertices of the graph
     */
    public ArrayList<T> getVertices() {	
        return this.nodes;
    } 
	
    /*
     * Returns edge list of the graph
     */ 

    public HashMap<T,HashMap<T,E>> getVlist() {
      return this.vlist;
    }	

}
