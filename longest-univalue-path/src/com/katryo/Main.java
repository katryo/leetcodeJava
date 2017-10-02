package com.katryo;


public class Main {
    int max = 0;
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        Longest longest = search(root);
        return max;
    }

    private void update(Longest l) {
        if (l.length > max) {
            max = l.length;
        }
    }

    private Longest search(TreeNode node) {
        if (node.right != null && node.left != null) {
            Longest leftLongest = search(node.left);
            Longest rightLongest = search(node.right);

            if (leftLongest.val == rightLongest.val && leftLongest.val == node.val) {
                update(new Longest(node.val, rightLongest.length + leftLongest.length + 2));
                if (leftLongest.length > rightLongest.length) {
                    return new Longest(node.val, leftLongest.length + 1);
                } else {
                    return new Longest(node.val, rightLongest.length + 1);
                }
            }
            if (leftLongest.val == node.val) {
                Longest nl = new Longest(node.val, leftLongest.length + 1);
                update(nl);
                return nl;
            }
            if (rightLongest.val == node.val) {
                Longest nl = new Longest(node.val, rightLongest.length + 1);
                update(nl);
                return nl;
            }
            return new Longest(node.val, 0);
        }

        if (node.right == null && node.left == null) {
            return new Longest(node.val, 0);
        }

        Longest longest;
        if (node.right == null) {
            longest = search(node.left);
        } else {
            longest = search(node.right);
        }
        if (longest.val == node.val) {
            Longest nl = new Longest(node.val, longest.length + 1);
            update(nl);
            return nl;
        }
        return new Longest(node.val, 0);
    }

    private class Longest {
        private int val;
        private int length;
        private Longest(int val, int length) {
            this.val = val;
            this.length = length;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(5);
        Main m = new Main();
        int result = m.longestUnivaluePath(root);
        System.out.println(result);
    }
}

