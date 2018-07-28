import java.util.*;

public class Solution {
    public int countCornerRectangles(int[][] grid) {
        List<List<Integer>> rows = new ArrayList();
        int N = 0;
        for (int r = 0; r < grid.length; ++r) {
            rows.add(new ArrayList());
            for (int c = 0; c < grid[r].length; ++c) {
                if (grid[r][c] == 1) {
                    rows.get(r).add(c);
                    N++;
                }
            }
        }

        int sqrtN = (int) Math.sqrt(N);
        int ans = 0;
        Map<Integer, Integer> count = new HashMap();

        for (int r = 0; r < grid.length; ++r) {
            if (rows.get(r).size() >= sqrtN) {
                Set<Integer> target = new HashSet(rows.get(r));

                for (int r2 = 0; r2 < grid.length; ++r2) {
                    if (r2 <= r && rows.get(r2).size() >= sqrtN) {
                        continue;
                    }
                    int found = 0;
                    for (int c2: rows.get(r2)) {
                        if (target.contains(c2)) {
                            found++;
                        }
                    }
                    ans += found * (found - 1) / 2;
                }
            } else {
                for (int i1 = 0; i1 < rows.get(r).size(); ++i1) {
                    int c1 = rows.get(r).get(i1);
                    for (int i2 = i1 + 1; i2 < rows.get(r).size(); ++i2) {
                        int c2 = rows.get(r).get(i2);
                        int ct = count.getOrDefault(200*c1 + c2, 0);
                        ans += ct;
                        count.put(200*c1 + c2, ct + 1);
                    }
                }
            }
        }
        return ans;
    }
//    public int countCornerRectangles(int[][] grid) {
//        Map<Integer, Integer> count = new HashMap();
//        int ans = 0;
//        for (int[] row: grid) {
//            for (int c1 = 0; c1 < row.length; ++c1) {
//                if (row[c1] == 1) {
//                    for (int c2 = c1 + 1; c2 < row.length; ++c2) {
//                        if (row[c2] == 1) {
//                            int pos = c1 + 200 + c2;
//                            int c = count.getOrDefault(pos, 0);
//                            ans += c;
//                            count.put(pos, c + 1);
//                        }
//                    }
//                }
//            }
//        }
//        return ans;
//    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] row0 = {1, 1, 1};
        int[] row1 = {1, 1, 1};
        int[] row2 = {1, 1, 1};
        int[][] input0 = {row0, row1, row2};
        System.out.println(s.countCornerRectangles(input0));
    }
}

//class Solution3 {
//    public int countCornerRectangles(int[][] grid) {
//        List<List<Integer>> rows = new ArrayList();
//        int N = 0;
//        for (int r = 0; r < grid.length; ++r) {
//            rows.add(new ArrayList());
//            for (int c = 0; c < grid[r].length; ++c) {
//                if (grid[r][c] == 1) {
//                    rows.get(r).add(c);
//                    N++;
//                }
//            }
//        }
//
//        int sqrtN = (int) Math.sqrt(N);
//        int ans = 0;
//        Map<Integer, Integer> count = new HashMap();
//
//        for (int r = 0; r < grid.length; ++r) {
//            if (rows.get(r).size() >= sqrtN) {
//                Set<Integer> target = new HashSet(rows.get(r));
//
//                for (int r2 = 0; r2 < grid.length; ++r2) {
//                    if (r2 <= r && rows.get(r2).size() >= sqrtN) {
//                        continue;
//                    }
//                    int found = 0;
//                    for (int c2: rows.get(r2)) {
//                        if (target.contains(c2)) {
//                            found++;
//                        }
//                    }
//                    ans += found * (found - 1) / 2;
//                }
//            } else {
//                for (int i1 = 0; i1 < rows.get(r).size(); ++i1) {
//                    int c1 = rows.get(r).get(i1);
//                    for (int i2 = i1 + 1; i2 < rows.get(r).size(); ++i2) {
//                        int c2 = rows.get(r).get(i2);
//                        int ct = count.getOrDefault(200*c1 + c2, 0);
//                        ans += ct;
//                        count.put(200*c1 + c2, ct + 1);
//                    }
//                }
//            }
//        }
//        return ans;
//    }
//}