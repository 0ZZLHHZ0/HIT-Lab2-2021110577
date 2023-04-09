/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    //  1. refer to the given sample in problem
    //  2. based on given sample write another relatively complex corpus with output
    //  3. build an empty file as corpus
    //  test the new output after processing a simple origin

    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests

    // file contains:
    // I may hate the class, but as a programmer, i will never hate beloved coding.

    @Test
    public void test_poem1() throws IOException{

        String filePath0 = "src/P1/poet/mugar-omni-theater.txt";
        String filePath1 = "src/P1/poet/sample.txt";
        String filePath2 = "src/P1/poet/empty.txt";
        String input0 = "Test the system.";
        String input = "I hate coding.";
        String output0 = "Test of the system.";
        String output = "I may hate beloved coding.";
        GraphPoet given = new GraphPoet(new File(filePath0));
        GraphPoet sample = new GraphPoet(new File(filePath1));
        GraphPoet empty = new GraphPoet(new File(filePath2));
        assertEquals(output0,given.poem(input0));
        assertEquals(output,sample.poem(input));
        assertEquals(input,empty.poem(input));
    }
}
