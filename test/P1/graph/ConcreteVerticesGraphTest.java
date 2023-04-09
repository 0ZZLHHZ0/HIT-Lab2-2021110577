/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   build a normal edge with source, target and weight, then call those edge method to test the return

    // TODO tests for ConcreteVerticesGraph.toString()
    @Test public void  test_ConcreteVerticesGraph_toSting(){
        Graph<String> graph = emptyInstance();
        graph.set("first","second",1);
        graph.set("second","third",2);
        graph.set("fourth","first",3);

        assertEquals("{fourth=3}->first->{second=1}\n" +
                "{first=1}->second->{third=2}\n" +
                "{second=2}->third->{}\n" +
                "{}->fourth->{first=3}\n",graph.toString());
    }

    
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //   build a new vertex and add an edge to it by addSource
    //   test getSource by checking contains of key and value
    //   build a map manually to test getSource
    //   test removeSource when the vertex exists and dose not  exist respectively
    //   test toString by comparing return things
    // TODO tests for operations of Vertex
    @Test public void test_vertex(){
        Vertex<String> vertex = new Vertex<>("center");
        vertex.add_source("left-one",1);
        assertTrue(vertex.getSource().containsKey("left-one"));
        assertTrue(vertex.getSource().containsValue(1));
        Map <String,Integer> index = new HashMap<>();
        index.put("left-one",1);
        assertEquals(index,vertex.getSource());
        assertTrue(vertex.remove_source("left-one"));
        assertEquals("{}->center->{}\n",vertex.toString());
        assertFalse(vertex.remove_source("left-one"));

    }
}
