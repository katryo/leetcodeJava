public class Solution {
    public int pivotIndex(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int right = 0;
        for (int i = 0; i < nums.length; i++) {
            right += nums[i];
        }

        right -= nums[0];

        int left = 0;
        if (left == right) {
            return 0;
        }
        for (int i = 1; i < nums.length; i++) {
            // i is the index
            left += nums[i - 1];
            right -= nums[i];

            if (right == left) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {1, 7, 3, 6, 5, 6};
        System.out.println(s.pivotIndex(nums));
    }
}
