import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] inputs = {-1, -2, -3, -4, -5};
        int[] result = solution.twoSum(inputs, -8);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    public Solution() {}
    public int[] twoSum(int[] nums, int target) {
        int[] sortedNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sortedNums);

        int length = sortedNums.length;
        int leftI = 0;
        int rightI = length - 1;

        int answerLeftValue;
        int answerRightValue;

        while (true) {

            int leftValue = sortedNums[leftI];
            int rightValue = sortedNums[rightI];

            int sumValue = leftValue + rightValue;

            if (sumValue == target) {
                answerLeftValue = leftValue;
                answerRightValue = rightValue;
                break;
            } else if (sumValue > target) {
                rightI--;
            } else if (sumValue < target) {
                leftI++;
            }
        }

        int answerLeftI = 0;
        int answerRightI = 0;

        if (answerLeftValue == answerRightValue) {
            boolean answerLeftFound = false;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == answerLeftValue) {
                    if (answerLeftFound == true) {
                        answerRightI = i;
                        int[] answer = {answerLeftI, answerRightI};
                        return answer;
                    } else {
                        answerLeftI = i;
                        answerLeftFound = true;
                    }
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == answerLeftValue) {
                answerLeftI = i;
            }

            if (nums[i] == answerRightValue) {
                answerRightI = i;
            }
        }
        int[] answer = {answerLeftI, answerRightI};
        Arrays.sort(answer);
        return answer;
    }
}