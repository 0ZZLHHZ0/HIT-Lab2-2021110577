package P2;

import org.junit.Test;

import static org.junit.Assert.*;

public class FriendshipGraphTest {
/*
    @Test
    public void addVertex() {
        Person a = new Person("A");
        Person b = new Person("B");
        Person c = new Person("A");
        FriendshipGraph graph = new FriendshipGraph();
       // FriendshipGraph namelist = new FriendshipGraph();
        assertEquals(0,graph.);
       assertEquals(0,namelist.addVertex(b));
      //  assertEquals(1,namelist.addVertex(c));
      //  Assert.assertThrows()

    }


 */
    @Test
    public void test_2add() {
        Person<String> a = new Person<>("A");
        Person<String> b = new Person<>("B");
        FriendshipGraph<String> namelist = new FriendshipGraph<>();
        namelist.addVertex(a);
        namelist.addVertex(b);
        namelist.addEdge(a,b);
        assertEquals(1,namelist.getDistance(a,b));
        assertEquals(-1,namelist.getDistance(b,a));
    }

    @Test
    public void getDistance() {
        Person<String> a = new Person<>("A");
        Person<String> b = new Person<>("B");
        Person<String> c = new Person<>("C");
        Person<String> d = new Person<>("D");
        FriendshipGraph<String> namelist = new FriendshipGraph<>();
        namelist.addVertex(a);
        namelist.addVertex(b);
        namelist.addVertex(c);
        namelist.addVertex(d);
        namelist.addEdge(a,b);
        namelist.addEdge(b,c);
        namelist.addEdge(c,b);
        namelist.addEdge(c,d);
        assertEquals(1,namelist.getDistance(a,b));
        assertEquals(-1,namelist.getDistance(b,a));
        assertEquals(2,namelist.getDistance(a,c));
        assertEquals(3,namelist.getDistance(a,d));
        assertEquals(-1,namelist.getDistance(c,a));
    }
}