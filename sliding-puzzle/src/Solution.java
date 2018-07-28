import java.util.*;

public class Solution {
    HashSet<Node> explored = new HashSet<Node>();

    class Node {
        int[][] board;
        String boardString;
        int zeroR;
        int zeroC;
        int depth;
        int heuristic;

        public Node(int[][] board, int zr, int zc, int depth) {
            this.board = board;
            boardString = Arrays.deepToString(board);
            zeroR = zr;
            zeroC = zc;
            this.depth = depth;
            this.heuristic = penalty();
        }

        public int penalty() {
            int s = 0;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    s += penaltyAt(i, j);
                }
            }
            return s;
        }

        private int penaltyAt(int i, int j) {
            int num = this.board[i][j];
            switch (num) {
                case 1: return i + j;
                case 2: return i + Math.abs(j - 1);
                case 3: return i + Math.abs(j - 2);
                case 4: return Math.abs(i - 1) + Math.abs(j);
                case 5: return Math.abs(i - 1) + Math.abs(j - 1);
                default: return Math.abs(i - 1) + Math.abs(j - 2);
            }
        }
    }

    public int slidingPuzzle(int[][] board) {
        int i = -1;
        int j = -1;
        search: for (i = 0; i < 2; i++) {
            for (j = 0; j < 3; j++) {
                if (board[i][j] == 0) break search;
            }
        }
        Node root = new Node(board, i, j, 0);

        PriorityQueue<Node> heap = new PriorityQueue<Node>(Comparator.comparingInt(a -> (a.heuristic + a.depth))
        );

        heap.add(root);

        HashMap<String, Integer> cost = new HashMap<>();
        cost.put(root.boardString, 9999);

        String target = Arrays.deepToString(new int[][]{{1, 2, 3}, {4, 5, 0}});
        String targetWrong = Arrays.deepToString(new int[][]{{1, 2, 3}, {5, 4, 0}});

        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!heap.isEmpty()) {
            Node node = heap.poll();
            if (node.boardString.equals(target)) return node.depth;
            if (node.boardString.equals(targetWrong)) return -1;
            if (node.heuristic + node.depth > cost.get(node.boardString)) continue;

            for (int[] direction: directions) {
                int newZeroR = node.zeroR + direction[0];
                int newZeroC = node.zeroC + direction[1];

                if (newZeroR < 0 || newZeroR > 1 || newZeroC < 0 || newZeroC > 2) continue;

                int[][] newBoard = new int[2][3];
                for (int k = 0; k < 2; k++) {
                    System.arraycopy(node.board[k], 0, newBoard[k], 0, 3);
                }

                newBoard[newZeroR][newZeroC] = 0;
                newBoard[node.zeroR][node.zeroC] = node.board[newZeroR][newZeroC];

                Node newNode = new Node(newBoard, newZeroR, newZeroC, node.depth + 1);

                if (cost.getOrDefault(Arrays.deepToString(newBoard), 9999) <= newNode.heuristic + newNode.depth) {
                    continue;
                }

                cost.put(newNode.boardString, newNode.heuristic + newNode.depth);
                heap.add(newNode);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] board = {{4, 1, 2}, {5, 0, 3}};
        System.out.println(s.slidingPuzzle(board));
    }
}
