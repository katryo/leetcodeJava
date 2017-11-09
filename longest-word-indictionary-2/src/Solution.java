import java.util.*;

public class Solution {
    public String longestWord(String[] words) {
        Trie trie = new Trie(words);
        for (int i = 0; i < words.length; i++) {
            trie.insert(words[i], i);
        }
        return trie.dfs();
    }

    class Node {
        int indexForLastChar;
        HashMap<Character, Node> children = new HashMap<>();
        char c;

        Node(char c) {
            this.c = c;
        }
        public String toString() {
            return "" + c;
        }
    }

    class Trie {
        Node root;
        String[] words;

        Trie(String[] words) {
            this.words = words;
            root = new Node('0');
        }

        public void insert(String word, int index) {
            Node cur = root;
            for (char c: word.toCharArray()) {
                cur.children.putIfAbsent(c, new Node(c));
                cur = cur.children.get(c);
            }
        }

        private String dfs() {
            String ans = "";
            Stack<Node> stack = new Stack();
            stack.push(root);
            while (!stack.empty()) {
                Node node = stack.pop();
                if (node.indexForLastChar > 0 || node == root) {
                    if (node != root) {
                        String word = words[node.indexForLastChar - 1];
                        if (word.length() > ans.length() || (word.length() == ans.length() && word.compareTo(ans) < 0)) {
                            ans = word;
                        }
                    }
                    for (Node nei: node.children.values()) {
                        System.out.println(nei);
                        stack.push(nei);
                    }
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String[] input = {"w", "wo", "wor", "worl", "world"};
        System.out.println(s.longestWord(input));
    }
}

