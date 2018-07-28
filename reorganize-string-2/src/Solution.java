import java.util.*;

class Solution {
    public String reorganizeString(String S) {
        int n = S.length();
        int[] count = new int[26];

        for (char c: S.toCharArray()) count[c - 'a']++;
        PriorityQueue<MultiChar> pq = new PriorityQueue<>((a, b) ->
                a.count == b.count ? a.letter - b.letter : b.count - a.count
        );

        for (int i = 0; i < 26; i++) if (count[i] > 0) {
            pq.add(new MultiChar(count[i], (char) (i + 'a')));
        }

        StringBuffer ans = new StringBuffer();

        while (pq.size() > 1) {
            MultiChar mc1 = pq.poll();

            MultiChar mc2 = pq.poll();

            char latestChar = ' ';

            if (ans.length() > 0) {
                latestChar = ans.charAt(ans.length() - 1);
            }

            if (ans.length() == 0 || mc1.letter != latestChar) {
                ans.append(mc1.letter);
                ans.append(mc2.letter);
                mc1.count--;
                mc2.count--;

                if (mc1.count > 0) pq.add(mc1);
                if (mc2.count > 0) pq.add(mc2);
            }

        }
        if (pq.size() != 0) {
            ans.append(pq.poll().letter);
        }

        return ans.toString();
    }
//    public String reorganizeString(String S) {
//        int N = S.length();
//        int[] count = new int[26];
//        for (char c: S.toCharArray()) count[c-'a']++;
//        PriorityQueue<MultiChar> pq = new PriorityQueue<>((a, b) ->
//                a.count == b.count ? a.letter - b.letter : b.count - a.count);
//
//        for (int i = 0; i < 26; ++i) if (count[i] > 0) {
//            if (count[i] > (N + 1) / 2) return "";
//            pq.add(new MultiChar(count[i], (char) ('a' + i)));
//        }
//
//        StringBuilder ans = new StringBuilder();
//        while (pq.size() >= 2) {
//            MultiChar mc1 = pq.poll();
//            mc1.count--;
//            MultiChar mc2 = pq.poll();
//            mc2.count--;
//            char latestChar = ' ';
//            if (ans.length() > 0) {
//                latestChar = ans.charAt(ans.length() - 1);
//            }
//            if (ans.length() == 0 || mc1.letter != latestChar) {
//                ans.append(mc1.letter);
//                ans.append(mc2.letter);
//                if (mc1.count > 0) pq.add(mc1);
//                if (mc2.count > 0) pq.add(mc2);
//            }
//        }
//
//        if (pq.size() > 0) ans.append(pq.poll().letter);
//        return ans.toString();
//    }

    public static void main(String args[]) {
        Solution s = new Solution();
        System.out.println(s.reorganizeString("aab"));
    }
}
//class MultiChar {
//    int count;
//    char letter;
//    MultiChar(int ct, char ch) {
//        count = ct;
//        letter = ch;
//    }
//}

class MultiChar {
    int count;
    char letter;
    MultiChar(int ct, char lt) {
        count = ct;
        letter = lt;
    }
}