import java.util.ArrayList;

public class Solution {
    private class UFNode{
        private UFNode parent;
        private ArrayList<UFNode> children = new ArrayList<UFNode>();
        private int value = 1;

        private void union(UFNode node) {
            if (node.contain(this)) return;

            UFNode root = this.root();
            root.parent = node.root();
            node.root().children.add(root);

            node.root().value = node.root().value + root.value;
        }

        private boolean contain(UFNode node) {
            UFNode n = this;
            while (n.parent != null) {
                n = n.parent;
            }

            while (node.parent != null) {
                node = node.parent;
            }
            return n == node;
        }

        private UFNode root() {
            UFNode n = this;
            while (n.parent != null) {
                n = n.parent;
            }
            return n;
        }
    }

    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0) return 0;
        UFNode[][] nodes = new UFNode[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    nodes[i][j] = new UFNode();
                    if (i != 0 && nodes[i-1][j] != null) {
                        nodes[i][j].union(nodes[i-1][j]);
                    }

                    if (j != 0 && nodes[i][j-1] != null) {
                        nodes[i][j].union(nodes[i][j-1]);
                    }
                }
            }
        }
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                UFNode node = nodes[i][j];
                if (node == null) continue;
                UFNode loot = node.root();
                if (max < loot.value) max = loot.value;
            }
        }

        return max;
    }


    public static void main(String[] args) {
        int[][] input = {{1, 0, 1}, {1, 1, 1}};
        Solution s = new Solution();
        System.out.println(s.maxAreaOfIsland(input));
    }
}
