public class Solution {
    public int countBinarySubstrings(String s) {
        int ans = 0;
        int prev = 0;
        int cur = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i-1) != s.charAt(i)) {
                ans += Math.min(prev, cur);
                prev = cur;
                cur = 1;
            } else {
                cur++;
            }
        }
        return ans+ Math.min(prev, cur);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.countBinarySubstrings("00110011"));
    }
}
