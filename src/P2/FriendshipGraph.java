package P2;

import P1.graph.ConcreteEdgesGraph;

import java.util.*;

public class FriendshipGraph<L> {
  //  private  ArrayList<Person> namelist ;
    final private ConcreteEdgesGraph<Person<L>>graph;
    public FriendshipGraph(){
     //  namelist  = new ArrayList<>();
        graph = new ConcreteEdgesGraph<>();
        graph.vertices().clear();
    }
    /** initialize a new list to record person vertex */

    public int  addVertex(Person somebody){

        if(graph.add(somebody)){
            return 0;
        }else {
            System.out.println("This person name exists already:"+somebody.getName() );
        return 1;
        }
    }
        /*
        for(Person index:namelist){
            if(index.getName().equals(somebody.getName())){
                System.out.println("This person name exists already, Action Failed ");
               // System.exit(1);
                System.exit(1);
            }
        }
            namelist.add(somebody);
        return 0;

     */
    /** add new person vertex to the macro namelist */

    public void addEdge(Person A, Person B){

        if(!graph.vertices().contains(A)||!graph.vertices().contains(B)){
            System.out.println("No such person in name list(graph vertex)");
            System.exit(1);
        }else {
            graph.set(A,B,1);
        }
    }
        /*   if(!namelist.contains(A)||!namelist.contains(B)){
            System.out.println("No such person in name list");
            System.exit(1);
        }else if (A.Relation.contains(B)){
            System.out.println("B is already in relationship with A");
            System.exit(1);
        }else {
            A.AddFriend(B);
        }
      */
/** add new relation between two people [ directed relation ] */

    public int getDistance(Person<L> source,Person<L> target){
        Map<Person<L>, Integer> distance = new HashMap<>();
        Map<Person<L>, Boolean> visited = new HashMap<>();

        for(Person<L> index:graph.vertices()){
            distance.put(index,0);
            visited.put(index,false);
        }
      //  System.out.println("a new call \n"+ graph.toString());
       /* for(Person all:namelist){
            all.setVisit(false);
            all.setLength(0);
        }
        */
        if(source == target)
            return 0;
        Queue<Person<L>> personQueue = new LinkedList<>();
        personQueue.add(source);
        while (!personQueue.isEmpty()){
            Person<L> temp = personQueue.remove();
           // System.out.println(temp.toString()+" this is temp \n");
            if(graph.targets(temp).isEmpty())
                continue;
            if(!visited.get(temp)){
                visited.replace(temp,true);
               // temp.setVisit();
                if(graph.targets(temp).containsKey(target))
                    return  distance.get(temp)+1;
                System.out.println(temp.toString() + graph.targets(temp).keySet()+"\n");
                for(Person<L> t2p: graph.targets(temp).keySet()){
                    System.out.println(t2p.toString() +"\n" );
                    if(!visited.get(t2p)){
                        int newlength = distance.get(temp);
                        distance.replace(t2p,newlength+1);
                        //t2p.addlength();
                        personQueue.add(t2p);
                    }
                }
            }
        }
        //System.out.println("Pitifully, we cannot confirm their relation");
        return -1;
    }
/** use breadth first search algorithm to detect the relation between source person and target person */

    public static void main(String[] args){

        FriendshipGraph<String> graph = new FriendshipGraph<>();
         Person<String> rachel = new Person<>("Rachel");
         Person<String> ross = new Person<>("Ross");
         Person<String> ben = new Person<>("Ben");
         Person<String> kramer = new Person<>("Kramer");
         graph.addVertex(rachel);
         graph.addVertex(ross);
         graph.addVertex(ben);
         graph.addVertex(kramer);
        graph.addEdge(rachel, ross);
        graph.addEdge(ross, rachel);
        graph.addEdge(ross, ben);
        graph.addEdge(ben, ross);
       // System.out.println("rachel\t"+rachel.Relation + "\n"+"ben\t"+ ben.Relation);
        System.out.println(graph.getDistance(rachel, ross));
//should print 1
        System.out.println(graph.getDistance(rachel, ben));
//should print 2
        System.out.println(graph.getDistance(rachel, rachel));
//should print 0
        System.out.println(graph.getDistance(rachel, kramer));
//should print ‐1
        System.out.println("DON'T PANIC");
    }
}

