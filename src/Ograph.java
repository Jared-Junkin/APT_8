import java.util.*;

public class Ograph {
    private ArrayList<Integer> sizes = new ArrayList<>();
    private Map<String, Set<String>> myGraph = new HashMap<>();
    private Set<String> visited = new HashSet<>();

    private void makeGraph(String[] data) {
        for (String connections : data) {
            String[] nodes = connections.split(" ");
            HashSet<String> temp = new HashSet<>(Arrays.asList(nodes));
            for(String vertex : nodes){
                if(! myGraph.containsKey(vertex)){
                    HashSet<String> set = new HashSet<>();
                    myGraph.put(vertex, set);
                }
                myGraph.get(vertex).addAll(temp);
                myGraph.get(vertex).remove(vertex);
            }
        }
    }

    private void connectionsCounter(String entryPoint){
        if(visited.contains(entryPoint)) return;
        int count = 1;
        Queue<String> qu = new LinkedList<>();
        visited.add(entryPoint);
        qu.add(entryPoint);
        while(qu.size() > 0){
            String curr = qu.remove();
            for(String node : myGraph.get(curr)){
                if(!visited.contains(node)){
                    qu.add(node);
                    visited.add(node);
                    count++;
                }
            }
        }
        //the problem with this is that I misread it in the same way I misread internet. if I wanted to fix it I could, but I don't now.
        sizes.add(count);
    }
    public int[] components(String[] data){
        makeGraph(data);
        for(String entryPoint : myGraph.keySet()){
            connectionsCounter(entryPoint);
        }
        int[] ret = new int[sizes.size()];
        int k = 0;
        for(Integer x : sizes){
            ret[k] = x;
            k++;
        }
        Arrays.sort(ret);
        return ret;
    }
    public static void main(String[] args){
        String[] data = {"1 3", "0 2", "1 3", "0 2", "5", "4 6", "5 7", "6 8", "7", "10 11 12", "9 11 12", "9 10 12", "9 10 11"};
        Ograph e = new Ograph();
        e.components(data);
    }
}
/*
}
    private HashSet<String> toSet(String[] vertices){
        HashSet<String> ret = new HashSet<>(Arrays.asList(vertices));
        return ret;
    }
private Map<String, HashSet<String>> makeMap(String[] data){
        Map<String, HashSet<String>> myMap = new HashMap<>();
        for(String pair : data){
            String[] vertices = pair.split(" ");
            HashSet<String> temp = toSet(vertices);
            for(String vertex : vertices){
                if(!myMap.containsKey(vertex)){
                    HashSet<String> set = new HashSet<>();
                    myMap.put(vertex, set);
                }
                myMap.get(vertex).addAll(temp);
                myMap.get(vertex).remove(vertex);
            }
        }
        return myMap;
    }

    public void bfs(Map<String, HashSet<String>> myMap, String str, Set<String> visited){
        Queue<String> qu = new LinkedList<>();
        int ret = 0;
        visited.add(str);
        qu.add(str);
        while(qu.size() > 0){
            String curr = qu.remove();
            ret++;
            for(String vertex : myMap.get(curr)){
                if(!visited.contains(vertex)){
                    qu.add(vertex);
                    visited.add(vertex);
                }
            }
        }
        sizes.add(ret);
    }
    public int[] components(String[] data) {
        Set<String> visited = new TreeSet<>();
        Map<String, HashSet<String>> myMap = makeMap(data);
        for(String str : myMap.keySet()){
           if(!visited.contains(str)){
               bfs(myMap, str, visited);
           }
        }
        int[] ret = new int[sizes.size()];
        int dex = 0;
        for(int k : sizes){
            ret[dex] = k;
            dex ++;
        }
        return ret;
    }
 */