import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

public class Solution {
    public String longestWord(String[] words) {
        HashMap<Integer, ArrayList<String>> wordsMap = new HashMap<Integer, ArrayList<String>>();

        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            ArrayList<String> wordsInMap = wordsMap.get(w.length());
            if (wordsInMap == null) {
                ArrayList newWords = new ArrayList<String>();
                newWords.add(words[i]);
                wordsMap.put(w.length(), newWords);
            } else {
                wordsInMap.add(words[i]);
                wordsMap.put(w.length(), wordsInMap);
            }
        }

        String ans = new String();

        HashSet<String> validMaxStrings = new HashSet<String>();

        ArrayList<String> ss1 = wordsMap.get(1);
        ans = ss1.get(0);
        for (String s: ss1) {
            validMaxStrings.add(s);
            if (ans.toCharArray()[0] < s.toCharArray()[0]) {
                continue;
            } else if (ans.toCharArray()[0] > s.toCharArray()[0]) {
                ans = s;
            }
        }

        for (int i = 2; i < 31 ; i++) {
            if (!wordsMap.containsKey(i)) {
                break;
            }
            ArrayList<String> ss = wordsMap.get(i);
            HashSet<String> newValidMaxStrings = new HashSet<String>();
            for (String s: ss) {
                String smaller = s.substring(0, s.length()-1);
                if (validMaxStrings.contains(smaller)) {
                    if (ans != null) {
                        if (ans.length() == s.length()) {
                            for (int j = 0; j < ans.length(); j++) {
                                if (ans.charAt(j) < s.charAt(j)) {
                                    break;
                                } else if (ans.charAt(j) > s.charAt(j)) {
                                    ans = s;
                                    break;
                                }
                            }
                        } else {
                            ans = s;
                        }
                    } else {
                        ans = s;
                    }
                    newValidMaxStrings.add(s);
                }
            }
            validMaxStrings = newValidMaxStrings;
        }

        return ans;
    }


    public static void main(String[] args) {
        String[] words = {"ts","e","x","pbhj","opto","xhigy","erikz","pbh","opt","erikzb","eri","erik","xlye","xhig","optoj","optoje","xly","pb","xhi","x","o"};
//        String[] words = {"a"};
        Solution s = new Solution();
        System.out.println(s.longestWord(words));
    }
}
