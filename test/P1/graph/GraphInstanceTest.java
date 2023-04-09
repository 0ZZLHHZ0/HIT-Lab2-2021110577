/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.*;

import P1.graph.Graph;
import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //   TODO
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // TODO other tests for instance methods of Graph

    /**
     * strategy fot add()
     * 1.add a vertex to the graph normally
     * 2.add a vertex which is already in the graph
     * 3. additionally check whether the added vertex is in the grpah
     */
    public void test_add(){
        Graph<String> graph = emptyInstance();
        assertEquals(true,graph.add("a"));
        assertEquals(true,graph.add("b"));
        assertEquals(true,graph.add("c"));//add normally
        assertEquals(false,graph.add("a"));//add already exits
        assertEquals(true,graph.vertices().contains("b"));//test include

    }

    /**
     * strategy for set()
     * 1.add a new weighted edge whose vertices are already exist
     * 2.add a new weighted edge whose vertices are not added yet
     * 3.set an existed edge with new weight 0 and check its previous set weight
     * 4.check the weighted edge including undefined vertex
     */

    public void test_set(){
        Graph<String> graph = emptyInstance();
        graph.add("a");
        graph.add("b");
        assertEquals(0,graph.set("a","b",2));//set normally
        assertEquals(0,graph.set("a","d",8));//undefined vertex
        assertEquals(2,graph.set("a","b",0));//recheck normally
        assertEquals(8,graph.set("a","d",8));//recheck und
        assertEquals(0,graph.set("a","b",5));//zerolize

    }
    /**
     * strategy for remove()
     * 1. remove a vertex in the graph normally
     * 2. remove an undefined vertex
     * 3. remove a vertex which has been already removed
     * 4. check the edge related to the removed vertex
     */
    public void test_remove(){
        Graph<String> graph = emptyInstance();
        graph.add("a");
        graph.add("b");
        graph.add("c");
        graph.set("a","b",2);
        assertEquals(true,graph.remove("a"));//normally
        assertEquals(false,graph.remove("d"));//und
        assertEquals(false,graph.remove("a"));//already
        assertEquals(0,graph.set("a","b",5));
    }

    /**
     * strategy for vertices()
     * 1. check an empty graph
     * 2. check a mormally  created graph
     * 3. check the graph vertices again after a removal
      */
    public void test_vertices(){
        Graph<String> graph = emptyInstance();
        Set<String> expected = new HashSet<>();
        assertEquals(expected,graph.vertices());
        graph.add("a");
        graph.add("b");
        graph.add("c");
        expected.add("a");
        expected.add("b");
        expected.add("c");
        assertEquals(expected,graph.vertices());
        graph.remove("a");
        expected.remove("a");
        assertEquals(expected,graph.vertices());
    }

    /**
     * strategy for sources()
     * 1.check an edge-target-unrelated vertex
     * 2.check an edge-target-related vertex
     * 3.check the target after a related vertex removal
     */
    public void test_sources(){

        Graph<String> graph = emptyInstance();
        graph.add("a");
        graph.add("b");
        graph.add("c");
        graph.set("a","b",2);
        graph.set("a","c",3);
        graph.set("c","b",4);
        Map<String,Integer> map = new HashMap<>();
        assertEquals(map,graph.sources("a"));
        map.put("a",2);
        map.put("c",4);
        assertEquals(map,graph.sources("b"));
        graph.remove("a");
        map.remove("a");
        assertEquals(map,graph.sources("b"));

    }

    /**
     * strategy for targets()
     * 1.check an edge-source-unrelated vertex
     * 2.check an edge-source-related vertex
     * 3.check the source after a vertex removal
     */
    public void test_targets(){

        Graph<String> graph = emptyInstance();
        graph.add("a");
        graph.add("b");
        graph.add("c");
        graph.set("a","b",2);
        graph.set("a","c",3);
        graph.set("c","b",4);
        Map<String,Integer> map = new HashMap<>();
        assertEquals(map,graph.targets("b"));
        map.put("b",2);
        map.put("c",3);
        assertEquals(map,graph.targets("a"));
        graph.remove("b");
        map.remove("b");
        assertEquals(map,graph.targets("a"));
    }
}

