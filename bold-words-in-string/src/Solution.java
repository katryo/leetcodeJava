import java.util.*;

public class Solution {
    public String boldWords(String[] words, String S) {
        char[] chars = S.toCharArray();
        HashMap<Integer, Boolean> ans = new HashMap<Integer, Boolean>();
        for (int i = 0; i < chars.length; i++) {
            ans.put(i, false);
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < chars.length; j++) {
                if (word.charAt(0) == chars[j]) {
                    int wordI = 0;
                    for (int k = j; k < chars.length; k++) {
                        if (word.charAt(wordI) != chars[k]) {
                            break; // failure
                        }
                        if (wordI == word.length() - 1) {
                            // success
                            for (int l = j; l < k + 1; l++) {
                                ans.put(l, true);
                            }
                            break;
                        }
                        wordI++;
                    }
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        boolean running = false;
        for (int i = 0; i < chars.length; i++) {
            boolean isBold = ans.get(i);
            if (isBold) {
                if (running) {
                    sb.append(chars[i]);
                } else {
                    sb.append("<b>");
                    sb.append(chars[i]);
                    running = true;
                }

                // last char
                if (i == chars.length - 1) {
                    sb.append("</b>");
                }
            } else {
                if (running) {
                    sb.append("</b>");
                    sb.append(chars[i]);
                    running = false;
                } else { // notRunning, notBold
                    sb.append(chars[i]);
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String[] words = {"ccb","b","d","cba","dc"};
        String S = "eeaadadadc";
//        String[] words = {"dc"};
//        String S = "adc";
        String result = s.boldWords(words, S);
        System.out.println(result);
    }
}
