//https://leetcode.com/articles/max-area-of-island/

public class Solution2 {
    boolean[][] seen;
    int[][] grid;

    private int area(int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length) {
            return 0;
        }
        if (grid[r][c] == 0) return 0;
        if (seen[r][c]) return 0;

        seen[r][c] = true;

        return 1 + area(r-1, c) + area(r, c-1) + area(r, c+1) + area(r+1, c);
    }
    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0) return 0;
        this.grid = grid;
        seen = new boolean[grid.length][grid[0].length];

        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                max = Math.max(max, area(i, j));
            }
        }
        return max;
    }


    public static void main(String[] args) {
        int[][] input = {{1, 0, 1}, {1, 1, 1}};
        Solution2 s = new Solution2();
        System.out.println(s.maxAreaOfIsland(input));
    }
}
