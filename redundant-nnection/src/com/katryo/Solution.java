package com.katryo;

import java.util.*;

public class Solution {
    private static final int MAX_EDGES_COUNT = 1000;
    private ArrayList<Integer>[] graph = new ArrayList[MAX_EDGES_COUNT];
    private HashSet<Integer> explored = new HashSet<Integer>();

    public int[] findRedundantConnection(int[][] edges) {
        for (int i = 0; i < MAX_EDGES_COUNT; i++) {
           graph[i] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            if (doesEdgeCreateCycle(edge)) {
                return edge;
            } else {
                addEdgeToGraph(edge);
            }
        }
        throw new AssertionError();
    }

    private void addEdgeToGraph(int[] edge) {
        graph[edge[0]].add(edge[1]);
        graph[edge[1]].add(edge[0]);
    }

    private boolean doesEdgeCreateCycle(int[] edge) {
        if (graph[edge[0]].isEmpty()) return false;
        if (graph[edge[1]].isEmpty()) return false;

        explored.clear();
        return doesBFSReachesSeenNode(edge[0], edge[1]);
    }

    /**
     * Use BFS to detect if there is another path that connects the edge's head and tail.
     */
    private boolean doesBFSReachesSeenNode(int start, int goal) {
        if (!explored.contains(start)) {
            explored.add(start);
            if (start == goal) return true;
            for (int tail : graph[start]) {
                if (doesBFSReachesSeenNode(tail, goal)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] edges = {{1,2},{2,3},{2,4},{4,5},{1,5}};
        Solution s = new Solution();
        int[] result = s.findRedundantConnection(edges);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
