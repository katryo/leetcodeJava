import java.util.*;

public class Solution {
    public int numJewelsInStones(String J, String S) {
        HashSet<Character> set = new HashSet<Character>();
        for (Character c: J.toCharArray()) {
            set.add(c);
        }

        int count = 0;
        for (Character cha: S.toCharArray()) {
            if (set.contains(cha)) count++;
        }

        return count;
    }

    public static void main(String[] args) {
        String J = "z";
        String S = "ZZ";
        Solution s = new Solution();
        System.out.println(s.numJewelsInStones(J, S));
    }
}
