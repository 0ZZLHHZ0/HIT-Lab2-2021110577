/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    //  create a graph and test the return to check its matching

    // TODO tests for ConcreteEdgesGraph.toString()
    @Test public void test_toString(){
        Graph<String> graph = emptyInstance();
        graph.set("first","second",1);
        graph.set("second","third",2);
        graph.set("fourth","first",3);
        assertEquals("first-->second:1\n"+"second-->third:2\n"+"fourth-->first:3\n",graph.toString());
    }
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    //  build a normal edge with source, target and weight, then call those edge method to test the return

    // TODO tests for operations of Edge
    @Test public  void test_getSource(){
        Edge a = new Edge("%rdi","%rsi",12);
        assertEquals("%rdi",a.getSource());
    }
    @Test public void test_getTarget(){
        Edge b = new Edge("%rsi","%rdx",23);
        assertEquals("%rdx",b.getTarget());
    }

    @Test public void test_getWeight(){
        Edge c =new Edge("%rdx","%rcx",34);
        assertEquals(34,c.getWeight());
    }
}
