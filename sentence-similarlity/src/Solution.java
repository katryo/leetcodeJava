import java.util.*;

public class Solution {
    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        HashMap<String, HashSet<String>> table = new HashMap<String, HashSet<String>>();

        for (int i = 0; i < pairs.length; i++) {
            String a = pairs[i][0];
            String b = pairs[i][1];
            table.putIfAbsent(a, new HashSet<String>());
            table.putIfAbsent(b, new HashSet<String>());
            HashSet<String> rights = table.get(a);
            rights.add(b);

            HashSet<String> lefts = table.get(b);
            lefts.add(a);
        }

        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])) {
                continue;
            }
            HashSet<String> similars = table.get(words1[i]);
            if (similars == null) {
                return false;
            }
            if (!similars.contains(words2[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String[] word1 = {"a", "b", "c"};
        String[] word2 = {"a", "b", "e"};
        String[][] pairs = {{"a", "ckk"}, {"bee", "dbbb"}};
        System.out.println(s.areSentencesSimilar(word1, word2, pairs));
    }
}
