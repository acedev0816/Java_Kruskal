/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kruskal;

import graph.Graph;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static kruskal.Kruskal.getGraphWeight;
import static kruskal.Kruskal.initKruskal;
import static kruskal.Kruskal.printInfo;

/**
 *
 * @author Giova
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException,IOException {
        if(args.length < 1) {
            throw new IOException("Usage: java Main <filepath>");
        }

        Graph<String,Double> graph = new Graph<>(false);
        try {
            String path = "src/sources/italian_dist_graph.csv";
            try (FileReader reader = new FileReader(args[0]); BufferedReader bf = new BufferedReader(reader)) {
                String line;
                boolean endOfFile = false;
                while (true) {
                    line = bf.readLine();
                    endOfFile = (line == null);
                    if (endOfFile) {
                        break;
                    }
                    
                    String[] values = line.split(",");
                    
                    if (values == null) {
                        throw new IOException();
                    }
                    
                    if (values.length == 3) {
                        Double distance = Double.parseDouble(values[2]);
                        String v1 = values[0];
                        String v2 = values[1];
                        
                        graph.addVertex(v1);
                        graph.addVertex(v2);
                        graph.addUndirectEdge(v1, v2, distance);
                    }
                }
            } catch (NullPointerException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } catch(IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        } 

        Graph<String,Double> minForest = initKruskal(graph);
        System.out.println("ORIGINAL GRAPH: ");
        printInfo(graph);

        double mainWeigth = 0;
        int mainEdges = 0;
        int mainVertices = 0;

        
        System.out.println("MST: ");
        mainWeigth = (getGraphWeight(minForest)/2)/1000;
        mainEdges = minForest.totEdges()/2;
        mainVertices = minForest.getVertices().size();

        System.out.println("Vertices: "+ mainVertices);
        System.out.println("Edges: "+ mainEdges + " (expected: 18637)");
        System.out.println("Weight: "+ mainWeigth + " Km (expected: 89939,913)");
        System.exit(0);
      }
}
