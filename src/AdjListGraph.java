import java.util.*;


/*
This implementation saves the adjacency list edges as nodes
 */
public class AdjListGraph<T> {

    private Map<T,LinkedList<T>> adjList = new HashMap<T,LinkedList<T>>();
    private T StartPoint;

    public T getStartPoint(){
        return StartPoint;
    }

    public List<T> getElements(T i){
        return adjList.get(i);
    }

    public void addEdgeBiDirect(T i, T e){
        adjList.get(i).add(e);
        adjList.get(e).add(i);
    }
    
    public void addNode(T node){
        if(adjList.isEmpty())StartPoint = node;
        adjList.put(node,new LinkedList<>());
    }
}
