import java.util.*;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[][] dp = new int[30001][101];

        for (int i = temperatures.length-2; i > -1; i--) {
            for (int j = 100; j > 29; j--) {
                if (temperatures[i+1] > j) {
                    dp[i][j] = 1;
                } else {
                    if (dp[i+1][j] == 0) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = dp[i+1][j] + 1;
                    }
                }
            }
        }

        int[] answer = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            int degree = temperatures[i];
            answer[i] = dp[i][degree];
        }

        return answer;
    }

    public static void main(String args[]) {
        Solution s = new Solution();
        int[] temps = {99, 100};
//        int[] temps = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = s.dailyTemperatures(temps);

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
