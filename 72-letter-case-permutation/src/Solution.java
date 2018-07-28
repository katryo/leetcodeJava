import com.sun.deploy.util.ArrayUtil;

import java.util.*;

public class Solution {
    public List<String> letterCasePermutation(String S) {
        StringBuilder sb0 = new StringBuilder();
        ArrayList<StringBuilder> sbs = new ArrayList<StringBuilder>();
        sbs.add(sb0);
        char[] chars = S.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int diff = chars[i] - '0';
            if (diff <= 9 && diff >= 0 ) {
                for (StringBuilder sb: sbs) {
                    sb.append(chars[i]);
                }
            } else {
                ArrayList<StringBuilder> sbs2 = new ArrayList<StringBuilder>();
                for (StringBuilder sb: sbs) {
                    sbs2.add(new StringBuilder(sb.toString()));
                    sb.append(Character.toLowerCase(chars[i]));
                }
                for (StringBuilder sb2: sbs2) {
                    sb2.append(Character.toUpperCase(chars[i]));
                }
                sbs.addAll(sbs2);
            }
        }
        List<String> ans = new ArrayList<String>();
        for (StringBuilder sb: sbs) {
            ans.add(sb.toString());
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String input0 = "312345";
        List<String> res = s.letterCasePermutation(input0);
        for (String r:res) {
            System.out.println(r);
        }
    }
}
