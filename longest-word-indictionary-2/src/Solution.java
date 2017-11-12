// https://leetcode.com/articles/longest-word-in-dictionary/

import java.util.*;

public class Solution {
    public String longestWord(String[] words) {
        Trie t = new Trie(words);
        return t.dfs();
    }

    private class Node {
        char c;
        HashMap<Character, Node> children = new HashMap<>();
        int end = 0;
        Node(char c) {
            this.c = c;
        }
    }

    private class Trie {
        private String[] words;
        private Node root;

        Trie(String[] words) {
            this.words = words;
            root = new Node('0');

            // Create a trie tree
            for (int i = 0; i < words.length; i++) {
                insert(words[i], i + 1);
            }
        }

        public String dfs() {
            Stack<Node> stack = new Stack<>();
            stack.push(root);

            String ans = "";

            while (!stack.isEmpty()) {
                Node cur = stack.pop();
                if (cur != root && cur.end != 0) {
                    String word = words[cur.end - 1];
                    if (ans.length() < word.length() || ans.length() == word.length() && word.compareTo(ans) < 0) {
                        ans = word;
                    }
                }
                for (Node node: cur.children.values()) {
                    if (node.end > 0) {
                        stack.push(node);
                    }
                }
            }
            return ans;
        }

        private void insert(String word, int index) {
            Node cur = root;
            for (char c: word.toCharArray()) {
                cur.children.putIfAbsent(c, new Node(c));
                cur = cur.children.get(c);
            }
            cur.end = index;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String[] input = {"b", "banana", "ba", "ban", "app", "ap", "a"};
        System.out.println(s.longestWord(input));
    }
}

