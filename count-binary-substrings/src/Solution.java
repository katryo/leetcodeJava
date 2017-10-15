public class Solution {
    char[] a;
    int[] opt = new int[50000];
    public int countBinarySubstrings(String s) {
        a = s.toCharArray();
        opt[0] = 0;

        for (int i = 1; i < a.length; i++) {
            if (doesLastValid(i)) {
                opt[i] = opt[i-1] + 1;
            } else {
                opt[i] = opt[i-1];
            }
        }
        return opt[a.length-1];
    }

    private boolean doesLastValid(int last) {
        if (last == 0) return false;
        int k = last;
        int diff = 1;
        while (a[k-1] == a[k]) {
            diff++;
            k--;
            if (k-1 == -1) return false;
        }

        // a[k-1] != a[k]

        int diffToWall = 0;
        for (int j = k-1; j >= 0; j--) {
            if (a[last] == a[j]) {
                return false;
            }
            diffToWall++;
            if (diffToWall == diff) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
//        System.out.println(s.countBinarySubstrings("0011"));
        System.out.println(s.countBinarySubstrings("00110011"));
    }
}
