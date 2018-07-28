import java.util.*;

public class Solution {

    class UnionStructure {
        int[] parent;
        int[] score;

        public void print() {
            for (int i = 0; i < parent.length; i++) {
                System.out.println(parent[i]);
            }
        }
        public UnionStructure(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
            score = new int[size];
        }

        public int tparent(int ind) {
            while (parent[ind] != ind) {
                ind = parent[ind];
            }
            return parent[ind];
        }
        public void union(int a, int b) {
            if (tparent(a) == tparent(b)) {
                return;
            }
            if (score[tparent(a)] > score[tparent(b)]) {
                parent[tparent(b)] = tparent(a);
            } else if (score[tparent(b)] > score[tparent(a)]) {
                parent[tparent(a)] = tparent(b);
            } else {
                parent[tparent(a)] = tparent(b);
                score[tparent(b)]++;
            }
        }
    }
    public List<Integer> partitionLabels(String S) {
        UnionStructure us = new UnionStructure(S.length());

        int[] parentIndex = new int[S.length()];
        for (int i = 0; i < parentIndex.length; i++) {
            parentIndex[i] = i;
        }

        for (int l = 0; l < 26; l++) {
            char alphabet = (char)((int)'a' + l);
//            if (alphabet == 'e') {
//                break;
//            }
            int firstAlphabetIndex = -1;
            // Find the first a. first_a_ind
            for (int i = 0; i < S.length(); i++) {
                if (S.charAt(i) == alphabet) {
                    firstAlphabetIndex = i;
                    break;
                }
            }

            if (firstAlphabetIndex == -1) {
                continue;
            }

            // Find the last a. last_a_ind

            int lastAlphabetIndex = S.length();
            for (int i = S.length() - 1; i > -1; i--) {
                if (S.charAt(i) == alphabet) {
                    lastAlphabetIndex = i;
                    break;
                }
            }

            if (lastAlphabetIndex == firstAlphabetIndex) {
                continue;
            }

            // Union from first_a_ind through last_a_ind


//            System.out.println("first: " + firstAlphabetIndex + ", last: " + lastAlphabetIndex);

            for (int i = firstAlphabetIndex; i < lastAlphabetIndex; i++) {
                us.union(i, i + 1);
            }
        }

//        us.print();

        List<Integer> ans = new ArrayList<Integer>();

        int current = -1;
        int ind = -1;
        for (int i = 0; i < S.length(); i++) {
            if (current != us.tparent(i)) {
                current = us.tparent(i);
                ind++;
                ans.add(1);
            } else {
                ans.set(ind, ans.get(ind) + 1);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
//        String S = "ababcbacadefegdehijhklij";
//        String S = "ecckkkec";
        String S = "eccbbbbdec";
//        String S = "abcda";
        List<Integer> result = sol.partitionLabels(S);
        for (Integer inte: result) {
            System.out.print(inte);
            System.out.print(" ");
        }
        System.out.println("");
    }
}
