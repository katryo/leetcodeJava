import java.util.*;

public class Solution {
    public int minCostClimbingStairs(int[] cost) {
        if (cost.length <= 2) {
            return 0;
        }
        int[] dp = new int[cost.length];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]);
        }
        return Math.min(dp[cost.length - 1] + cost[cost.length - 1], dp[cost.length - 2] + cost[cost.length -2]);
    }

    public static void main(String[] args) {
        int[] input0 = {10, 15, 20};
        int[] input1 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        Solution s = new Solution();
        System.out.println(s.minCostClimbingStairs(input1));
    }
}
