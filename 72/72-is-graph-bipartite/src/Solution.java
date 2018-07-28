import java.util.*;

public class Solution {

    public boolean isBWithS(int[][] graph, int start, HashSet<Integer> explored, HashMap<Integer, Integer> evenOdd) {
        Deque<Integer> queue = new LinkedList<>();
        queue.addLast(start);

        int EVEN = 0;
        int ODD = 1;
        evenOdd.put(start, EVEN);

        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            explored.add(node);

            for (Integer child: graph[node]) {
                if (explored.contains(child)) {
                    if (evenOdd.get(child).equals(evenOdd.get(node))) {
                        return false;
                    }
                    continue;
                }
                queue.addLast(child);
                evenOdd.put(child, evenOdd.get(node) == EVEN ? ODD : EVEN);
            }
        }
        return true;
    }

    public boolean isBipartite(int[][] graph) {
        HashSet<Integer> explored = new HashSet<>();
        HashMap<Integer, Integer> evenOdd = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            if (explored.contains(i)) continue;
            if (!isBWithS(graph, i, explored, evenOdd)) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int input0[][] = {{1,3}, {0,2}, {1,3}, {0,2}};
        System.out.println(s.isBipartite(input0));
        int input1[][] = {{1,2,3}, {0,2}, {0,1,3}, {0,2}};
        System.out.println(s.isBipartite(input1));
        int input2[][] = {{4},{},{4},{4},{0,2,3}};
        System.out.println(s.isBipartite(input2));

        int input3[][] = {{},{2,4,6},{1,4,8,9},{7,8},{1,2,8,9},{6,9},{1,5,7,8,9},{3,6,9},{2,3,4,6,9},{2,4,5,6,7,8}};
        System.out.println(s.isBipartite(input3));

        int input4[][] = {{1},{0},{4},{4},{2,3}};
        System.out.println(s.isBipartite(input2));


    }
}
