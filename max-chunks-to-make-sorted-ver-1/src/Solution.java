import java.util.*;

public class Solution {
    public int maxChunksToSorted2(int[] arr) {
        Map<Integer, Integer> count = new HashMap();
        int ans = 0, nonzero = 0;

        int[] expect = arr.clone();
        Arrays.sort(expect);

        for (int i = 0; i < arr.length; ++i) {
            int x = arr[i], y = expect[i];

            count.put(x, count.getOrDefault(x, 0) + 1);
            if (count.get(x) == 0) {
                nonzero--;
            }
            if (count.get(x) == 1) nonzero++;

            count.put(y, count.getOrDefault(y, 0) - 1);
            if (count.get(y) == -1) nonzero++;
            if (count.get(y) == 0) nonzero--;

            if (nonzero == 0) ans++;
        }

        return ans;
    }

    /**
     * Returns the runnning index starts from start.
     * @param table
     * @param start
     * @return
     */
    private int isRunning(boolean[] table, int start) {
        boolean trueAllMode = true;
        int anchor = start;
        for (int i = start; i < table.length; i++) {
            if (trueAllMode) {
                if (!table[i]) {
                    trueAllMode = false;
                } else {
                    anchor = i;
                }
            } else {
                if (table[i]) {
                    return start;
                }
            }
        }
        return anchor;
    }

    public int maxChunksToSorted(int[] arr) {
        if (arr.length == 1) {
            return 1;
        }
        boolean[] table = new boolean[arr.length];
        int start = 0;
        int counter = 0;
        if (arr[0] == 0) {
            counter++;
        }
        for (int i = 0; i < arr.length; i++) {
            table[arr[i]] = true;
            int isR = isRunning(table, start);

            if (isR > start) {
                counter++;
                start = isR;
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
//        int[] input = {1,4,0,3,2};
        int[] input = {1,0,2,3,4}; // 4
//        int[] input = {4,3,2,1,0}; // 4
//        int[] input = {2,1,0,3,4};
//        int[] input = {0};
//        int[] input = {1,0};
//        int[] input = {0,1};
//        int[] input = {0,1,2};
        System.out.println(s.maxChunksToSorted2(input));
    }
}
