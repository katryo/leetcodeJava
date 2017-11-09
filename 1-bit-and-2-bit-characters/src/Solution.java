public class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int i = 0;
        while (i < bits.length) {
            if (i == bits.length - 1) {
                return true;
            }
            int cur = bits[i];
            if (cur == 1) {
                i = i + 2;
            } else {
                i = i + 1;
            }
        }
        return false;

    }
    public static void main(String[] args) {
        int[] bits = {1, 1, 1, 0};
        Solution s = new Solution();
        System.out.println(s.isOneBitCharacter(bits));
    }
}
