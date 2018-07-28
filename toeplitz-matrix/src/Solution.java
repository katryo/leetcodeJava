public class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int height = matrix[0].length;
        int cur = -1;
        for (int i = 0 - height; i < matrix.length; i++) {
            cur = -1;

            for (int j = 0; j < 3; j++) {
                int target_c = i + j;
                int target_r = j;
                if (target_c < 0 || target_c >= height || target_r < 0 || target_r >= matrix.length) {
                    continue;
                }
                int target = matrix[target_r][target_c];
                if (cur == -1) {
                    cur = target;
                } else {
                    if (cur != target) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] m = {{1,2},{2,2}};
//        int[][] m = {{1,2,3,4},{5,1,2,3},{9,5,1,2}};
//        int[][] m = {{1,1,1,1},{2,2,2,2},{3,3,3,3}};
        System.out.println(s.isToeplitzMatrix(m));
    }
}
