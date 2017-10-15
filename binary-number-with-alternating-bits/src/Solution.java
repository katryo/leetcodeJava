// https://leetcode.com/problems/binary-number-with-alternating-bits/solution/

public class Solution {
    public boolean hasAlternatingBits(int n) {
        String bs = Integer.toBinaryString(n);
        if (bs.length() == 0) return false;
        if (bs.length() == 1) return true;

        char pre = bs.charAt(0);
        for (int i = 1; i < bs.length(); i++) {
            if (bs.charAt(i) != pre) return false;
            pre = bs.charAt(i);
        }
        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.hasAlternatingBits(5));
    }
}
