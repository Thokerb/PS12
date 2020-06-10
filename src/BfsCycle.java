import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class BfsCycle {

    public static void main(String[] args) {
        var graph = new AdjListGraph<Integer>();
        for (int i = 1; i < 9; i++) {
            graph.addNode(i);

        }

        // Slightly modified the example with an 8 between 2 and 3 for better tests
        graph.addEdgeBiDirect(1,2);
        graph.addEdgeBiDirect(1,5);
        graph.addEdgeBiDirect(2,5);
        //graph.addEdgeBiDirect(2,8);
        //graph.addEdgeBiDirect(8,3);
        graph.addEdgeBiDirect(2,3); //swap with 2,8 and 8,3
        graph.addEdgeBiDirect(3,4);
        graph.addEdgeBiDirect(3,7);
        graph.addEdgeBiDirect(4,7);
        graph.addEdgeBiDirect(4,6);
        graph.addEdgeBiDirect(7,6);
        var traversed = new ArrayList<>();
        traversed.add(graph.getStartPoint());
        bfsCycleDetectRec(graph.getStartPoint(),null,graph,traversed);

    }

    public static void bfsCycleDetectRecFin(Object point,Object searchPoint,AdjListGraph graph,ArrayList traversed,ArrayList remPath){
        var nextTrav = new ArrayList<>();
        for(var connection : graph.getElements(point)){
            if(connection == point){
                continue;
            }

            if(connection == searchPoint){
                for (var elem :
                        remPath) {
                    System.out.print("->"+elem);
                }
                System.out.println("->"+connection);
                nextTrav = new ArrayList<>();
                break;
            }
            if(traversed.contains(connection)){
                continue;
            }

            traversed.add(connection);
            remPath.add(connection);
            nextTrav.add(connection);
        }
        for (var next:nextTrav) {
            bfsCycleDetectRecFin(next,searchPoint,graph,traversed,remPath);
        }

    }

    public static void bfsCycleDetectRec(Object point,Object prevPoint,AdjListGraph graph,ArrayList traversed){
        var nextTrav = new ArrayList<>();
            for(var connection : graph.getElements(point)){
                if(connection == point){
                    continue;
                }
                if(traversed.contains(connection) && connection != prevPoint){
                    System.out.print(prevPoint+" -> " + point +"-> "+connection);
                   // System.out.println("Cycle,now we would have to search "+prevPoint+" from this point for a cycle");
                    var list = new ArrayList<>();
                    list.add(point);
                    list.add(prevPoint);
                    list.add(connection);
                    bfsCycleDetectRecFin(connection,prevPoint,graph,list,new ArrayList());
                    continue;
                }
                if(traversed.contains(connection)){
                    continue;
                }
                traversed.add(connection);
                nextTrav.add(connection);
            }
        for (var next:nextTrav) {
            bfsCycleDetectRec(next,point,graph,traversed);
        }

    }

}
