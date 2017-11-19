import java.util.*;

public class Solution {
    private ArrayList<Integer> as = new ArrayList<Integer>();
    private ArrayList<Integer> bs = new ArrayList<Integer>();
    private ArrayList<Integer> cs = new ArrayList<Integer>();
    private ArrayList<Integer> ds = new ArrayList<Integer>();
    public int countPalindromicSubsequences(String S) {
        int limit = S.length();

        for (int i = 0; i < limit; i++) {
            char c = S.charAt(i);
            if (c == 'a') {
                as.add(i);
            }

            if (c == 'b') {
                bs.add(i);
            }

            if (c == 'c') {
                cs.add(i);
            }

            if (c == 'd') {
                ds.add(i);
            }
        }

        int[][] dp = new int[limit][limit];
        for (int i = 0; i < limit; i++) {
            dp[i][i] = 1;
        }

        for (int i = 0; i < limit; i++) {
            for (int j = 0; j < limit; j++) {
                char c = S.charAt(j);
                ArrayList<Integer> indexes = indexesBeforeJ(j, S);
                int sum = 0;
                for (Integer index: indexes) {
                    sum += 
                }
                dp[i][j] = dp[i][j-1] +
            }

        }
    }

    private ArrayList<Integer> indexesBeforeJ(int j, String S) {
        char c = S.charAt(j);
        ArrayList<Integer> l;
        if (c == 'a') {
            l = as;
        }

        if (c == 'b') {
            l = bs;
        }

        if (c == 'c') {
            l = cs;
        }

        if (c == 'd') {
            l = ds;
        }

        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i) == j) {
                return result;
            }
            result.add(l.get(i));
        }
        return result; //
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int result = s.countPalindromicSubsequences("bccb");
        System.out.println(result);
    }
}
