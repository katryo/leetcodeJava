import java.util.*;

public class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        String ans = "answer";
        char[] chars = licensePlate.toCharArray();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < chars.length; i++) {
            char c = Character.toLowerCase(chars[i]);
            int diff = c - 'a';
            if (0 <= diff && diff <= 25) {
                if (map.containsKey(c)) {
                    int num = map.get(c);
                    map.put(c, num + 1);
                } else {
                    map.put(c, 1);
                }
            }
        }

        int minLengthValid = 1000;
        for (String word : words) {
            HashMap<Character, Integer> wordMap = new HashMap<>();
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (wordMap.containsKey(c)) {
                    int n = wordMap.get(c);
                    wordMap.put(c, n + 1);
                } else {
                    wordMap.put(c, 1);
                }
            }

            boolean isValid = true;
            for (Map.Entry<Character, Integer> e : map.entrySet()) {
                Integer licenseCharCount = e.getValue();
                Integer wordCount = wordMap.get(e.getKey());
                if (wordCount == null || wordCount < licenseCharCount) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                if (word.length() < minLengthValid) {
                    ans = word;
                    minLengthValid = word.length();
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String licensePlate0 = "1s3 PSt";
        String[] words0 = {"step", "steps", "stripe", "stepple"};
        Solution s = new Solution();
        System.out.println(s.shortestCompletingWord(licensePlate0, words0));

        String licensePlate1 = "1s3 456";
        String[] words1 = {"looks", "pest", "stew", "show"};
        System.out.println(s.shortestCompletingWord(licensePlate1, words1));
    }
}
