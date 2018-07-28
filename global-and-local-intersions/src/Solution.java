import java.util.*;

public class Solution {
    public boolean isIdealPermutation(int[] A) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < A.length; i++) {
            map.put(A[i], i);
        }

        int loc = map.get(0);
        int next;
        boolean oneOut = false;
        for (int i = 1; i < A.length; i++) {
            next = map.get(i);
            if (next - loc < -1) {
                return false;
            }
            if (next - loc == -1) {
                if (oneOut) {
                    return false;
                }
                oneOut = true;
            } else {
                oneOut = false;
            }
            loc = next;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] A = {1, 2, 0};
        System.out.println(s.isIdealPermutation(A));

        int[] B = {2, 1, 0};
        System.out.println(s.isIdealPermutation(B));

        int[] C = {1, 0, 2, 3};
        System.out.println(s.isIdealPermutation(C));

        int[] D = {1, 2, 0, 3};
        System.out.println(s.isIdealPermutation(D));

        int[] E = {1, 0, 3, 2};
        System.out.println(s.isIdealPermutation(E));
    }
}
