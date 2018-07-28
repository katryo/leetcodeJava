import java.util.*;

public class Solution {
    public int slidingPuzzle(int[][] board) {
        int R = board.length;
        int C = board[0].length;
        int sr = 0;
        int sc = 0;
        search:
        for (sr = 0; sr < R; sr++) {
            for (sc = 0; sc < C; sc++) {
                if (board[sr][sc] == 0) {
                    break search;
                }
            }
        }

        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        PriorityQueue<Node> heap = new PriorityQueue<Node>(
                (a, b) -> (a.heuristic + a.depth) - (b.heuristic + b.depth)
        );

        Node start = new Node(board, sr, sc, 0);
        heap.add(start);
        Map<String, Integer> cost = new HashMap<>();
        cost.put(start.boardString, 9999999);

        String target = Arrays.deepToString(new int[][]{{1, 2, 3}, {4, 5, 0}});
        String targetWrong = Arrays.deepToString(new int[][]{{1, 2, 3}, {5, 4, 0}});

        while (!heap.isEmpty()) {
            Node node = heap.poll();
            if (node.boardString.equals(target)) return node.depth;
            if (node.boardString.equals(targetWrong)) return -1;
            if (node.depth + node.heuristic > cost.get(node.boardString)) continue;
            for (int[] di: directions) {
                int neiR = di[0] + node.zeroRow;
                int neiC = di[1] + node.zeroCol;

                if (neiR < 0 || neiC < 0 || neiC >= C || neiR >= R)
                    continue;

                int[][] newBoard = new int[R][C];

                int t = 0;
                for (int[] row: node.board) {
                    newBoard[t++] = row.clone();
                }
                newBoard[node.zeroRow][node.zeroCol] = newBoard[neiR][neiC];
                newBoard[neiR][neiC] = 0;

                Node nei = new Node(newBoard, neiR, neiC, node.depth + 1);
                if (nei.depth + nei.heuristic >= cost.getOrDefault(nei.boardString, 999999))
                    continue;
                heap.add(nei);
                cost.put(nei.boardString, nei.depth + nei.heuristic);
            }
        }

        return -1;
    }

    class Node {
        int[][] board;
        String boardString;
        int heuristic;
        int zeroRow;
        int zeroCol;
        int depth;
        Node (int[][] board, int r, int c, int d) {
            this.board = board;
            boardString = Arrays.deepToString(board);
            zeroRow = r;
            zeroCol = c;
            depth = d;

            heuristic = 0;
            int R = board.length;
            int C = board[0].length;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (board[i][j] == 0) continue;
                    int v = (board[i][j] + R * C - 1) % (R * C); // 1 => 0, 2 => 1, 3 => 2, 4 => 3, 5 => 4
                    heuristic += Math.abs(i - v / C) + Math.abs(j - v % C);
                }
            }
            heuristic /= 2;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] board = {{3, 2, 4}, {1, 5, 0}};
        System.out.println(s.slidingPuzzle(board));
    }
}
