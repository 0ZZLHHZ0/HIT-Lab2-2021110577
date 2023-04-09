/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.*;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();

//     Abstraction function:
//      data type will be later defined by given 'L' when called
//      vertices are represented by label 'L' and stored by set
//      edges are built as a class and stored by list
//     Representation invariant:
//      the weight of edge should be positive, and 0 weight means not existing
//      the two vertices of a added edge should also be stored in vertex set
//     Safety from rep exposure:
//      vertex set and edge list are both defined as private final
//      so does local variables in class edge
    
    // TODO constructor
    public ConcreteEdgesGraph(){
        vertices.clear();
        edges.clear();
        checkRep();
    }
    // TODO checkRep
    private void checkRep(){
        for(Edge<L> tmp:edges){
            assert tmp.getWeight() > 0;
            assert vertices.contains(tmp.getSource());
            assert  vertices.contains(tmp.getTarget());
        }
    }
    @Override public boolean add(L vertex) {
        if(!vertices.contains(vertex)){
            vertices.add(vertex);
            checkRep();
            return true;
        }
        return false;
     //   throw new RuntimeException("not implemented");
    }
    
    @Override public int set(L source, L target, int weight) {
       if(weight<0)
           throw new RuntimeException("weight value must be positive or zero");
       this.add(source);
       this.add(target);
       for(Edge<L> tmp : edges){
           if(tmp.getSource().equals(source)&&tmp.getTarget().equals(target)){
               if(weight == 0){
                   edges.remove(tmp);
                   checkRep();
                   return tmp.getWeight();
               }else{
                   int i = tmp.getWeight();
                   edges.remove(tmp);
                   Edge<L> update = new Edge<>(source,target,weight);
                   edges.add(update);
                   checkRep();
                   return i;
               }
           }
       }
    //    Iterator<Edge<L>> iterator = edges.iterator();
    //    while(iterator.hasNext()){
     //   }           Edge<L> tmp = iterator.next();
        if(weight > 0) {
            Edge<L> update = new Edge<>(source, target, weight);
            edges.add(update);
        }
        checkRep();
        return 0;
        //throw new RuntimeException("not implemented");
    }
    
    @Override public boolean remove(L vertex) {
        if(!vertices.contains(vertex))
            return false;

        Iterator<Edge<L>> iterator = edges.iterator();
        while(iterator.hasNext()){
            Edge<L> tmp = (Edge<L>) iterator.next();
            if((tmp.getSource().equals(vertex))||(tmp.getTarget().equals(vertex)))
                iterator.remove();
        }
        vertices.remove(vertex);
        checkRep();
        return true;

        //throw new RuntimeException("not implemented");
    }
    
    @Override public Set<L> vertices() {
        return vertices;
       // throw new RuntimeException("not implemented");
    }
    
    @Override public Map<L, Integer> sources(L target) {
        Map<L,Integer> result = new HashMap<>();
        for(Edge<L> tmp : edges){
            if(tmp.getTarget().equals(target)){
                result.put(tmp.getSource(),tmp.getWeight());
            }
        }
        /*
        Iterator<Edge<L>> iterator = edges.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getTarget().equals(target)){
                result.put(iterator.next().getSource(), iterator.next().getWeight());
            }
        }

         */
        return result;
   //     throw new RuntimeException("not implemented");
    }
    
    @Override public Map<L, Integer> targets(L source) {
        Map<L,Integer> result = new HashMap<>();
        for(Edge<L> tmp : edges){
            if(tmp.getSource().equals(source)){
                result.put(tmp.getTarget(),tmp.getWeight());
            }
        }
        /*
        Iterator<Edge<L>> iterator = edges.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getSource().equals(source)){
                result.put(iterator.next().getTarget(),iterator.next().getWeight());
            }
        }

         */
        return result;
       // throw new RuntimeException("not implemented");
    }
    
    // TODO toString()
    @Override
    public String toString(){
        checkRep();
        StringBuilder result = new StringBuilder();
        for(Edge<L> tmp:edges){
            result.append(tmp.toString());
        }
        return result.toString();
    }
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    
    // TODO fields
    private L source, target;
    private int weight;
//     Abstraction function:
//       'source' and 'target' represents begin and end of an edge respectively
//       'weight' represent the weight of a weighted edge
//     Representation invariant:
//       source and target should not be void/empty/null
//       the weight value should not be negative
//     Safety from rep exposure:
//        all those three variables are defined as private as to be
//          reached from local methods only, and macro information
//           is defensively copied before method return
    
    // TODO constructor
    Edge(L source,L target,int weight){
        if(weight > 0){
            this.source = source;
            this.target = target;
            this.weight = weight;
        checkRep();
        }

    }
    // TODO checkRep
    private void checkRep(){
        assert weight > 0;
        assert source != null;
        assert target != null;
    }
    // TODO methods
    public L getSource(){
        return this.source;
    }

    public L getTarget(){
        return this.target;
    }

    public int getWeight(){
        return this.weight;
    }
    // TODO toString()
    @Override
    public String toString(){
        return source + "-->" + target + ":" + weight + "\n";
    }

}
