package com.katryo;

import java.util.HashMap;

public class Main {
    public double[][][] memo;
    public double knightProbability(int N, int K, int r, int c) {
        Board board = new Board(N, r, c);
        memo = new double[K + 1][N][N];
        double numOfBoards = board.numOfSafeBoardsAfterK(K);
        return numOfBoards;
    }

    private class Board {
        private int n;
        private int r;
        private int c;
        private Board(int N, int R, int C) {
            n = N;
            r = R;
            c = C;
        }

        private boolean isValid() {
            return (0 <= r && r <= n - 1 && 0 <= c && c <= n - 1);
        }

        private double numOfSafeBoardsAfterK(int k) {
            if (k == 0) return 1;
            double sum = 0.0;

            sum += createBoardAndIfValidGetNum(k, r + 1, c + 2);
            sum += createBoardAndIfValidGetNum(k, r + 2, c + 1);
            sum += createBoardAndIfValidGetNum(k, r -1, c - 2);
            sum += createBoardAndIfValidGetNum(k, r -2, c - 1);

            sum += createBoardAndIfValidGetNum(k, r + 1, c - 2);
            sum += createBoardAndIfValidGetNum(k, r - 2, c + 1);

            sum += createBoardAndIfValidGetNum(k, r - 1, c + 2);
            sum += createBoardAndIfValidGetNum(k, r + 2, c - 1);

            return sum / 8;
        }

        private double createBoardAndIfValidGetNum(int k, int R, int C) {
            Board b1 = new Board(n, R, C);
            if (b1.isValid()) {
                if (Main.this.memo[k][R][C] != 0) return Main.this.memo[k][R][C];
                Main.this.memo[k][R][C] = b1.numOfSafeBoardsAfterK(k - 1);
                return Main.this.memo[k][R][C];
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        double result = m.knightProbability(8, 30, 6, 4);
        System.out.println(result);
    }
}
