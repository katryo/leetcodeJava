// https://leetcode.com/problems/binary-number-with-alternating-bits/solution/
public class Solution2 {
    public boolean hasAlternatingBits(int n) {
        int remainder = n & 1;
        n >>= 1;

        while (n > 0) {
            if (remainder == (n & 1)) return false;
            remainder = n & 1;
            n >>= 1;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution2 s = new Solution2();
        System.out.println(s.hasAlternatingBits(15));
    }
}
