import java.util.*;

public class Solution {

    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<Integer>();
        int numLen = nums.length;

        for (int i = nums.length * 2 -1; i > -1 ; i--) {
            int newI = i % numLen;
            if (stack.empty()) {
                stack.push(newI);
                res[newI] = -1;
                continue;
            }
            if (nums[stack.peek()] > nums[newI]) {
                res[newI] = nums[stack.peek()];
                stack.push(newI);
                continue;
            }
            if (nums[stack.peek()] <= nums[newI]) {
                while (nums[stack.peek()] <= nums[newI]) {
                    stack.pop();
                    if (stack.empty()) {
                        break;
                    }
                }
                if (stack.empty()) {
                    res[newI] = -1;
                } else {
                    res[newI] = nums[stack.peek()];
                }
                stack.push(newI);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] input = {1, 2, 1};
        int[] ans = s.nextGreaterElements(input);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }
}
