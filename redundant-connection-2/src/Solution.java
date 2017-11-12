// https://leetcode.com/articles/redundant-connection/

public class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        DSU dsu = new DSU(10000);
        for (int i = 0; i < edges.length; i++) {
            boolean result = dsu.union(edges[i][0], edges[i][1]);
            if (!result) {
                return edges[i];
            }
        }
        throw new AssertionError();
    }

    class DSU {
        int[] parent;
        int[] height;

        public DSU(int size) {
            parent = new int[size];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
            height = new int[size];
        }

        public int find(int target) {
            if (parent[target] != target) {
                parent[target] = find(parent[target]);
            }
            return parent[target];
        }

        /**
         * Returns true if union step was successful
         * Returns false if union step failed
         * @param a
         * @param b
         * @return
         */
        public boolean union(int a, int b) {
            int ar = find(a);
            int br = find(b);
            if (ar == br) {
                return false;
            }
            if (height[ar] > height[br]) {
                parent[br] = ar;
                return true;
            }
            if (height[ar] < height[br]) {
                parent[ar] = br;
                return true;
            }
            parent[br] = ar;
            height[ar]++;
            return true;
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] edge1 = {0, 1};
        int[] edge2 = {1, 2};
        int[] edge3 = {2, 1};
        int[][] input = {edge1, edge2, edge3};
        int[] result = s.findRedundantConnection(input);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
        }
    }
}
