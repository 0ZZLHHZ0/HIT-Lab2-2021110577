package P2;

import java.security.PublicKey;
import java.util.LinkedList;

public class Person<L> {
    private final L name;
   // private boolean visit;
   // private int length;
   // public LinkedList<Person> Relation;
    public Person(L name){
        this.name = name;
     //   Relation = new LinkedList<>();
       // this.length = 0;
       // this.visit = false;
    }
    /** struct method  */

    /** add new person vertex to the firend list */
    public  L getName(){
        return name;
    }
    /** get the name of object */
  /*  public  void setVisit(){
        this.visit = true;
    }
    /*
    public void AddFriend(Person friend){
        Relation.add(friend);
    }
     */

    /**  manually set the condition of visit */
    /*public int getLength()
    {
        return length;
    }
    /** get the length for a certain moment */
    /*

    public void setLength(int n)

    {
        this.length = n;
    }
     */
    /*public void addlength(){
        this.length++;
    }

     */
    /** manually set the length */
    /*public boolean isVisited(){
        return visit ;
    }

     */
    /** check the visit condition of object */
}
