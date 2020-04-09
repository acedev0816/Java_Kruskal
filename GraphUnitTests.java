package graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import java.util.ArrayList;
import org.junit.Test;
public class GraphUnitTests
{
    private String v1,v2,v3,v4;
    Graph<String,Double> graph;

    @Before
    public void createGraph() {
        v1 = "Roma";
        v2 = "Milano";
        v3 = "Torino";
        v4 = "Aosta";
        graph = new Graph<>(false);
    }

    @Test
    public void testVertex() {
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v4);
        assertTrue(graph.checkVertex(v1));
        assertTrue(graph.checkVertex(v2));
        assertFalse(graph.checkVertex(v3));
    }

    @Test
    public void testWeight() {
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v1);
        graph.addUndirectEdge(v1,v3,10.0);
        graph.addUndirectEdge(v1,v4,3.13);
        graph.addUndirectEdge(v2,v4,21.0);
        assertTrue(10.0 == graph.getEdgeWeight(v1,v3));
        assertFalse(21.0 == graph.getEdgeWeight(v1,v4));
    }

    @Test
    public void testEdge() {
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addUndirectEdge(v1,v3,14.32);
        assertTrue(graph.checkEdge(v1,v3));
        assertFalse(graph.checkEdge(v1,v2));
    }
	
    @Test
    public void testVertexRemove() {
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);	
        graph.addUndirectEdge(v1,v3,14.32);
        graph.addUndirectEdge(v2,v3,9.32);	
        graph.addUndirectEdge(v1,v2,12.32);
        graph.removeVertex(v1);
        assertFalse(graph.checkVertex(v1));	  
        assertFalse(graph.checkEdge(v1,v3));
        assertFalse(graph.checkEdge(v1,v2));	  
    }
	
    @Test
    public void testEdgeRemove() {
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);  
        graph.addUndirectEdge(v1,v3,14.32);
        graph.addUndirectEdge(v2,v3,9.32);  
        graph.addUndirectEdge(v1,v2,12.32);
        graph.removeEdge(v2,v3);
        assertFalse(graph.checkEdge(v2,v3));
    }

    @Test
    public void totalEdgesTest() {
        graph.addVertex(v1);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v2);
        graph.addUndirectEdge(v1,v3,10.16);
        graph.addUndirectEdge(v1,v4,9.34);
        graph.addUndirectEdge(v1,v2,190.2);
        assertTrue(3 == graph.totEdges());
    }

    @Test
    public void testAdjacents() {
        ArrayList<String> adj1 = new ArrayList<>();
        ArrayList<String> adj2;	
        graph.addVertex(v1);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v2);
        graph.addUndirectEdge(v1,v3,10.16);
        graph.addUndirectEdge(v1,v2,8.24);
        graph.addUndirectEdge(v1,v4,12.24);
        adj1.add(v4);
        adj1.add(v3);
        adj1.add(v2);
    	  
        adj2 = graph.getAdjacents(v1);
        assertEquals(adj1, adj2);
    }

    @Test
    public void testVertices() {
        ArrayList<String> vertices = new ArrayList<>();

        graph.addVertex(v1);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v2);
        vertices.add(v1);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v2);
        assertEquals(vertices.size(), graph.totVertices());
    }
}
