package kruskal;

import graph.Graph;
import union.UnionFind;
import java.util.*;

public class Kruskal {
    
    public static double getGraphWeight(Graph<String,Double>graph) {
        double w = 0;
        Collection<String> vertices = graph.getVertices();
        
        for (String v1 : vertices) {
            w = graph.getAdjacents(v1).stream().map((v2) -> graph.getEdgeWeight(v1, v2)).reduce(w, (accumulator, _item) -> accumulator + _item);
        }
        return w;
    }
	
    /*
     * Method used to return an ordered list of all the weights in the graph
     *
     */      
    public static  ArrayList<Double> getWeights(Graph<String,Double> graph) {
        HashMap<String, HashMap<String,Double>> outer = new HashMap<>(graph.getVlist());
        ArrayList<Double> weights = new ArrayList<>();

        outer.entrySet().stream().map((entry) -> {
            return entry;
        }).map((entry) -> entry.getValue()).forEachOrdered((toEdge) -> {
            toEdge.entrySet().stream().map((innerEntry) -> {
                return innerEntry;
            }).map((innerEntry) -> innerEntry.getValue()).forEachOrdered((edge) -> {
                weights.add(edge);
            });
        });
        
        Collections.sort(weights);
        return weights;	
    }

    public static void printInfo(Graph<String,Double> graph)    {
        System.out.println("Vertices: "+ graph.getVertices().size());
        System.out.println("Edges: "+ graph.totEdges()/2);
        System.out.println("Weight: "+ (getGraphWeight(graph)/2)/1000 +" Km\n ");
    }

 	
    public static Graph<String,Double> initKruskal(Graph<String,Double> graph) throws IllegalAccessException {
        ArrayList<String> vertices = graph.getVertices();
        Graph<String,Double> mst = new Graph<>(false);
        String start = "abadia a isola";
        mst = kruskal(graph, vertices, start);
        return mst;
    }
	
    public static Graph<String,Double> kruskal(Graph<String,Double> graph, ArrayList<String> vertices, String start) {        
	Graph<String,Double> mst = new Graph<>(false);
        UnionFind<String> dset = new UnionFind<>();
        HashMap<String,String> edges = new HashMap<>();	    
        
        vertices.stream().map((v1) -> {         
            dset.makeSet(v1);
            return v1;
        }).forEachOrdered((String v1) -> {
            ArrayList<Double> weights = new ArrayList<>();
            double min = 0.0;
            
            graph.getAdjacents(v1).forEach((v2) -> {
                weights.add(graph.getEdgeWeight(v1, v2));
            });
	    
            for(String v2 : graph.getAdjacents(v1)) {
                min = Collections.min(weights);
                if(graph.checkEdge(v1,v2) && graph.getEdgeWeight(v1,v2) <= min) {
                    edges.put(v1,v2);
                    weights.remove(min);
                }
            }
        });
        
        edges.entrySet().forEach((e) -> {
            var u = e.getKey();
            var v = e.getValue();
            if (dset.find(u) != dset.find(v)) {
                mst.addVertex(u);
                mst.addVertex(v);
                mst.addUndirectEdge(u,v,graph.getEdgeWeight(u,v));
                dset.union(u,v);
            }
        });	
	return mst;       
    }
}    
	


			
