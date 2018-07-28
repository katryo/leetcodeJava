public class Solution {
    public int[] pourWater(int[] heights, int V, int K) {
        for (int i = 0; i < V; i++) {
            int leftDropI = -1;
            int rightDropI = -1;

            int l = K - 1;
            while (l >= 0 && heights[l] <= heights[l + 1]) {
                if (heights[l] < heights[l + 1]) {
                    leftDropI = l;
                }
                l--;
            }
            if (leftDropI != -1) {
                heights[leftDropI] = heights[leftDropI] + 1;
                continue;
            }

            int r = K + 1;
            while (r < heights.length && heights[r] <= heights[r - 1]) {
                if (heights[r] < heights[r - 1]) {
                    rightDropI = r;
                }
                r++;
            }
            if (rightDropI != -1) {
                heights[rightDropI] = heights[rightDropI] + 1;
                continue;
            }

            heights[K] = heights[K] + 1;
        }
        return heights;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] heights = {2,1,1,2,1,2,2};
        int[] result = s.pourWater(heights, 4, 3);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
        }
        System.out.println("");

        int[] heights2 = {1,2,3,4};
        int[] result2 = s.pourWater(heights2, 2, 2);
        for (int i = 0; i < result2.length; i++) {
            System.out.print(result2[i]);
        }
        System.out.println("");

        int[] heights3 = {3, 1, 3};
        int[] result3 = s.pourWater(heights3, 5, 1);
        for (int i = 0; i < result3.length; i++) {
            System.out.print(result3[i]);
        }
        System.out.println("");
    }
}
