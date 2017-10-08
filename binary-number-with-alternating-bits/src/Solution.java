public class Solution {
    public boolean hasAlternatingBits(int n) {
        int[] bits = new int[n];

        for (int i = 0; i < n; i++) {
           bits[i] = n & 0x1;
            System.out.println(n & 0x1);
        }
        return false;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.hasAlternatingBits(10);
    }
}
