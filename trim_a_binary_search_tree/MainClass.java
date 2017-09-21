/* -----------------------------------
 *  WARNING:
 * -----------------------------------
 *  Your code may fail to compile
 *  because it contains public class
 *  declarations.
 *  To fix this, please remove the
 *  "public" keyword from your class
 *  declarations.
 */
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.Queue;
import java.io.IOException;


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }
}

class Solution {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        while (root.val < L || root.val > R) {
            if (root.val < L) {
                if (root.right != null) {
                    root = root.right;
                } else {
                    return null;
                }
            }
            if (root.val > R) {
                if (root.left != null) {
                    root = root.left;
                } else {
                    return null;
                }
            }
        }

        // suppose that root.val is in the boundary.
        while (root.left != null && root.left.val < L) {
            if (root.left.right == null) {
                root.left = null;
            } else {
                root.left = root.left.right;
            }
        }

        while (root.right != null && root.right.val > R) {
            if (root.right.left == null) {
                root.right = null;
            } else {
                root.right = root.right.left;
            }
        }

        if (root.left != null) {
            trimBST(root.left, L, R);
        }
        if (root.right != null) {
            trimBST(root.right, L, R);
        }

        return root;
    }
}

public class MainClass {
    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static String treeNodeToString(TreeNode root) {
        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (node == null) {
                output += "null, ";
                continue;
            }

            output += String.valueOf(node.val) + ", ";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);
            line = in.readLine();
            int L = Integer.parseInt(line);
            line = in.readLine();
            int R = Integer.parseInt(line);

            TreeNode ret = new Solution().trimBST(root, L, R);

            String out = treeNodeToString(ret);

            System.out.print(out);
        }
    }
}