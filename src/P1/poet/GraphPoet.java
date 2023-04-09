/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    //   create a reference graph from a corpus
    // Representation invariant:
    //   all words read in should be free of up letters or punctuation
    // Safety from rep exposure:
    //
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {



        List<String> sentence = new ArrayList<>();
        BufferedReader pointer = null;
        pointer = new BufferedReader(new FileReader(corpus));
        String readin = null;
        while((readin = pointer.readLine()) != null){
          //  String[] storage = readin.split("\\.|,|!\\?");
          //  for(String index:storage){
                String [] process =readin.split(" ");
            sentence.addAll(Arrays.asList(process));
            }
      //  }
        pointer.close();
        for (int i = 0; i < sentence.size() - 1; i++){
            String a = sentence.get(i).toLowerCase();
            if(sentence.get(i+1).equals("")){
                i++;
            }
            String b = sentence.get(i + 1).toLowerCase();
           // System.out.println(a+"-->"+b+"\n");//$$$$$$$$
            int previous = graph.set(a,b,1);
            if(previous!=0){
                graph.set(a,b,previous + 1);
            }
        }
      //  System.out.println(graph.toString());
        checkRep();

        /*
        BufferedReader data = new BufferedReader(new FileReader(corpus));
        String line;
        String[] words;
        while ((line = data.readLine()) != null) {
            words = line.split(" ");
            for (int i = 1; i < words.length; i++) {
                int originWeight = graph.set(words[i - 1].toLowerCase(), words[i].toLowerCase(), 1);
                if (originWeight != 0) {
                    graph.set(words[i - 1].toLowerCase(), words[i].toLowerCase(), originWeight + 1);
                }
            }
        }
        checkRep();


         */
       // throw new RuntimeException("not implemented");
    }
    
    // TODO checkRep
    private void checkRep(){
        for(String vertex : graph.vertices()){
            assert (!vertex.equals(""));
            assert (!vertex.equals(" "));
            assert (!vertex.equals("\n"));
          //  assert (vertex.matches("[a-z0-9]+"));
        }
    }

    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {


        StringBuilder readin = new StringBuilder();
        List<String> storage = new ArrayList<>(Arrays.asList(input.split(" ")));
        Map<String,Integer> source;
        Map<String,Integer> target;
        for(int i = 0; i < storage.size() - 1;i++){
            String a = storage.get(i).toLowerCase();
            String b =storage.get(i+1).toLowerCase();
            readin.append(storage.get(i)+" ");
            target = graph.targets(a);
            source = graph.sources(b);
            int weight = 0;
            String bridge = "";
            for(String tmp : target.keySet()){
                if(source.containsKey(tmp)){
                    if(source.get(tmp) + target.get(tmp) > weight){
                        weight = source.get(tmp) + target.get(tmp);
                        bridge = tmp;
                    }
                }
            }
            if (weight > 0){
                readin.append(bridge + " ");
            }
        }
        readin.append(storage.get(storage.size() - 1));
        return readin.toString();


        //throw new RuntimeException("not implemented");
    }
    
    // TODO toString()
    public String toString(){
        return graph.toString();
    }
}
