import java.util.*;

public class Solution {
    boolean[][] explored;
    Stack<Coord> stack;
    ArrayList<Coord> spread;
    Coord startCoord;
    int startColor;
    int[][] image;

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        this.image = image;
        startCoord = new Coord(sr, sc);
        startColor = image[sr][sc];

        stack = new Stack<Coord>();
        spread = new ArrayList<Coord>();

        explored = new boolean[image.length][image[0].length];

        explored[sr][sc] = true;
        spread.add(startCoord);
        stack.push(startCoord);

        while (!stack.isEmpty()) {
            Coord c = stack.pop();
            if (c.row != 0) {
                search(c, -1, 0);
            }
            if (c.row != image.length -1) {
                search(c, 1, 0);
            }
            if (c.col != 0) {
                search(c, 0, -1);
            }
            if (c.col != image[0].length - 1) {
                search(c, 0, 1);
            }
        }

        for (Coord coord: spread) {
            image[coord.row][coord.col] = newColor;
        }
        return image;
    }

    private void search(Coord c, int rowDiff, int colDiff) {
        if (!explored[c.row + rowDiff][c.col + colDiff]) {
            if (image[c.row + rowDiff][c.col + colDiff] == startColor) {
                Coord newC = new Coord(c.row + rowDiff, c.col + colDiff);
                stack.push(newC);
                spread.add(newC);
            }
            explored[c.row + rowDiff][c.col + colDiff] = true;
        }
    }

    class Coord {
        public int row, col;
        public Coord(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) {
        int[][] input = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};

        Solution s = new Solution();
        int[][] result = s.floodFill(input, 1, 1, 2);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j]);
            }
            System.out.println("");
        }
    }
}
