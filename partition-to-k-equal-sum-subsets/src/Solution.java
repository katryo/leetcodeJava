import java.util.Arrays;

public class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        if (sum % k != 0) return false;
        int avg = sum / k;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int bucket = nums[i];
            if ()

            for (int j = i + 1; j < nums.length; j++) {
                bucket =
            }
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        System.out.println(s.canPartitionKSubsets(nums, 4));
    }
}
