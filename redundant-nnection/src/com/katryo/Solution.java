package com.katryo;

import java.util.*;

public class Solution {
    private HashMap<Integer, LinkedList<Integer>> nodes = new HashMap<Integer, LinkedList<Integer>>();
    private HashSet<Node> explored = new HashSet<Node>();
    private Node deepest;
    private int[] edgeOfCycle = new int[2];

    private void createAdjacentList(int[][] edges) {
        for (int i = 0; i < edges.length; i++) {
            addEdgesToAL(edges[i]);
        }
    }

    /**
     *
     * @param edge Example: [1, 4]
     */
    private void addEdgesToAL(int[] edge) {
        addEdgeToAL(edge[0], edge[1]);
        addEdgeToAL(edge[1], edge[0]);
    }

    private void addEdgeToAL(int head, int tail) {
        LinkedList<Integer> node = nodes.get(head);
        if (node == null) {
            LinkedList newList = new LinkedList<Integer>();
            newList.add(tail);
            nodes.put(head, newList);
        } else {
            node.push(tail);
        }
    }

    public int[] findRedundantConnection(int[][] edges) {
        createAdjacentList(edges);
        Node sourceNode = new Node(edges[0][0], 0, null);
        try {
            bsd(sourceNode, 1);
        } catch (CycleFound e) {
        }

//        HashSet<HashSet<Integer>> resultEdges = new HashSet<HashSet<Integer>>();
        ArrayList<Integer[]> resultEdges = new ArrayList<Integer[]>();
        Node cycleNode = deepest.cycleChild;
        Integer[] firstEdge = toEdgeIncreasingOrder(cycleNode.key, deepest.key);
        resultEdges.add(firstEdge);

        while (deepest.layer != cycleNode.layer) {
            Integer[] result = toEdgeIncreasingOrder(deepest.key, deepest.parent.key);
            resultEdges.add(result);
            deepest = deepest.parent;
        }

        while (deepest.parent != cycleNode.parent) {
            Integer[] result = toEdgeIncreasingOrder(deepest.key, deepest.parent.key);
            resultEdges.add(result);
            deepest = deepest.parent;

            Integer[] resultCycleNode = toEdgeIncreasingOrder(cycleNode.key, cycleNode.parent.key);
            resultEdges.add(resultCycleNode);
            cycleNode = cycleNode.parent;
        }

        resultEdges.add(toEdgeIncreasingOrder(deepest.key, deepest.parent.key));
        resultEdges.add(toEdgeIncreasingOrder(cycleNode.key, cycleNode.parent.key));



        int answerIndex = edges.length;
        for (int i = edges.length - 1; i > -1; i--) {
            int[] edge = edges[i];
            for (Integer[] resultEdge: resultEdges) {
                if (resultEdge[0] == edge[0] && resultEdge[1] == edge[1]) {
                    answerIndex = i;
                    break;
                }
            }
            if (answerIndex != edges.length) {
                break;
            }
        }

        return edges[answerIndex];
    }

    private Integer[] toEdgeIncreasingOrder(int keyA, int keyB) {
        Integer[] result = {Math.min(keyA, keyB), Math.max(keyA, keyB)};
        return result;
    }

    private Node searchFromExplored(int key) {
        for (Node n : explored) {
            if (n.key == key) return n;
        }
        return null;
    }

    private Node bsd(Node parent, int layer) throws CycleFound {
        LinkedList<Integer> tails =  nodes.get(parent.key);
        for (int tail: tails) {
            if (parent.parent != null && parent.parent.key == tail) continue;
            Node exploredNode = searchFromExplored(tail);
            if (exploredNode != null) {
                System.out.println(exploredNode.key);
                // head, tail is an edge consists of a cycle
                parent.cycleChild = exploredNode;
                deepest = parent;
                throw new CycleFound();
            } else {
                Node child = new Node(tail, layer, parent);
                parent.addChild(child);
                explored.add(child);
            }
        }

        for (Node child: parent.children) {
            return bsd(child, layer + 1);
        }
        System.out.println("saa");
        return null;
    }

    private class CycleFound extends RuntimeException {
    }

    private class Node {
        private int key;
        private int layer;
        private LinkedList<Node> children;
        private Node parent;
        private Node cycleChild;
        private Node(int key, int layer, Node parent) {
            this.key = key;
            this.layer = layer;
            this.parent = parent;
            this.children = new LinkedList<Node>();
        }
        private void addChild(Node child) {
            this.children.push(child);
        }
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
