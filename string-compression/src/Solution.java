// https://leetcode.com/articles/string-compression/
public class Solution {
    public int compress(char[] chars) {
        int anchor = 0;
        int write = 0;
        for (int i = 0; i < chars.length; i++) {
            if (i == chars.length - 1 || chars[i] != chars[i + 1]) {
                chars[write] = chars[anchor];
                write++;
                if (anchor != i) {
                    int diff = i + 1 - anchor;
                    char[] numChars = ("" + diff).toCharArray();
                    for (int j = 0; j < numChars.length; j++) {
                        chars[write] = numChars[j];
                        write++;
                    }
                }
                anchor = i + 1;
                // write
            }
        }
        return write;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        char[] input = {'a', 'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c' };
        int result = s.compress(input);
        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i]);
        }
        System.out.println(result);
    }
}
