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
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   graph is described abstractly by vertex list
    //   weighted edges are stored in maps of vertex class
    // Representation invariant:
    //    label of vertex should not be empty
    //    there should not be repeated vertex
    // Safety from rep exposure:
    //   vertices list are defined as private final
    //   edge information in vertex class is defined as private
    //      so that it could only be reached by inner method safely
    //   the access to private maps is processed after defensive copy
    
    // TODO constructor
    public  ConcreteVerticesGraph(){
        vertices.clear();
        checkRep();
    }
    // TODO checkRep
    public void checkRep(){
       for(int i = 0; i < vertices.size(); i++){
           assert vertices.get(i).getName() != null;
           for(int j = i +1 ; j < vertices.size() ; j++){
               assert !vertices.get(i).getName().equals(vertices.get(j).getName());
           }
       }
    }
    @Override public boolean add(L vertex) {
        for(Vertex<L> tmp : vertices){
            if (tmp.getName().equals(vertex))
                return false;
        }
        vertices.add(new Vertex<>(vertex));
        checkRep();
        return true;
        //throw new RuntimeException("not implemented");
    }
    
    @Override public int set(L source, L target, int weight) {
        if(weight < 0)
            throw new RuntimeException("weight value should not be negative");
        this.add(source);
        this.add(target);
        if(source == null || target == null)
            throw new RuntimeException("vertex label should not be empty");
        int previous = 0;
        for(Vertex<L> tmp : vertices){
            if(tmp.getName().equals(source)){
                previous = tmp.add_target(target,weight);
                }
            if(tmp.getName().equals(target)){
                previous = tmp.add_source(source,weight);
            }
        }
        checkRep();
        return previous;
        //throw new RuntimeException("not implemented");
    }
    
    @Override public boolean remove(L vertex) {
        boolean found = false;
        Vertex<L> idx = null;
        for(Vertex<L> tmp : vertices){
            if(tmp.getName().equals(vertex)){
                found = true;
                idx = tmp;
            }else if(tmp.getTarget().containsKey(vertex)){
                tmp.remove_target(vertex);
            } else if (tmp.getSource().containsKey(vertex)) {
                tmp.remove_source(vertex);
            }
        }
        vertices.remove(idx);
        return found;
       // throw new RuntimeException("not implemented");
    }
    
    @Override public Set<L> vertices() {
        Set<L> result = new HashSet<>();
        for(Vertex<L> tmp : vertices){
            result.add(tmp.getName());
        }
        return result;
        //throw new RuntimeException("not implemented");
    }
    
    @Override public Map<L, Integer> sources(L target) {
        Map<L,Integer> result = new HashMap<>();
        for(Vertex<L> tmp : vertices){
            if(tmp.getName().equals(target)){
                result.putAll(tmp.getSource());
                break;
            }
        }
        return result;
     //   throw new RuntimeException("not implemented");
    }
    
    @Override public Map<L, Integer> targets(L source) {
        Map<L,Integer> result = new HashMap<>();
        for(Vertex<L> tmp: vertices){
            if(tmp.getName().equals(source)){
                result.putAll(tmp.getTarget());
                break;
            }
        }
        return result;
    //    throw new RuntimeException("not implemented");
    }
    
    // TODO toString()
    public String toString(){
        String result = new String();
        for(Vertex<L> tmp :vertices){
            result += tmp.toString();
        }
        return result;
    }
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {

    // TODO fields
    private final L name;
    private final Map<L,Integer> source ;
    private final  Map<L,Integer> target;
    // Abstraction function:
    //   'source' and 'target' are two maps representing edges related to the vertex
    //   'source' are those edges in which the vertex plays target
    //   'target' are those edges in which the vertex plays source
    //    'name' represents the label of vertex and L is a data type later defined
    // Representation invariant:
    //   the weight value in both maps should all be positive
    // Safety from rep exposure:
    //   all local variables are defined as private and could only be reached by inner methods
    
    // TODO constructor
    public Vertex(L name){
        this.name = name;
        this.source = new HashMap<>();
        this.target = new HashMap<>();
    }
    // TODO checkRep
    private void checkRep(){
        for(Integer weight:source.values()){
            assert weight > 0;
        }
        for(Integer weight:target.values()){
            assert weight > 0;
        }
        assert name != null;
    }
    // TODO methods
    public boolean remove_source(L source){
        if(source == null)
            throw new RuntimeException("label should not be empty");

        if(this.source.keySet().contains(source)){
            this.source.remove(source);
            checkRep();
            return true;
        }
        return false;
    }

    public boolean remove_target(L target){
        if(target == null)
            throw new RuntimeException("label should not be empty");
        if(this.target.keySet().contains(target)){
            this.target.remove(target);
            checkRep();
            return true;
        }
        return false;
    }

    public int add_source(L source,int weight){
        if(weight < 0)
            throw new RuntimeException("weight shouldn't be negative");
        if(source == null)
            throw new RuntimeException("label should not be empty");

        if(this.source.keySet().contains(source)){
            Integer previous = this.source.get(source);
            if(weight == 0){
                this.source.remove(source);
            }else{
                this.source.replace(source,weight);
            }
            checkRep();
            return previous;
        }
        if(weight > 0){
            this.source.put(source,weight);
        }
        checkRep();
        return 0;
    }

    public int add_target(L target,int weight){
        if(weight < 0)
            throw new RuntimeException("weight value should not be negative");
        if(target == null)
            throw new RuntimeException("label should not be empty");
        if(this.target.keySet().contains(target)){
            Integer previous = this.target.get(target);
            if(weight == 0){
                this.target.remove(target);
            }else {
                this.target.replace(target,weight);
            }
            checkRep();
            return  previous;
        }
        if(weight > 0){
            this.target.put(target,weight);
        }
        checkRep();
        return  0;
    }

    public Map<L,Integer> getSource(){
        return source;
    }

    public Map<L,Integer> getTarget(){
        return target;
    }

    public L getName(){
        return name;
    }

    public String toString(){
        String result = new String();
        result = source.toString() + "->" + name.toString() + "->" + target.toString() + "\n";
        return result;
    }
    // TODO toString()
    
}
