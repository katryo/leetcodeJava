/**
 * Created by katryo on 10/29/17.
 */
public class Solution2 {
    public int compress(char[] chars) {
        int anchor = 0;
        int write = 0;

        for (int read = 0; read < chars.length; read++) {
            if (read + 1 == chars.length || chars[read + 1] != chars[read]) {
                chars[write] = chars[anchor];
                write++;
                if (read > anchor) {
                    char[] cs = ("" + (read - anchor + 1)).toCharArray();
                    for (char c: cs) {
                        chars[write] = c;
                        write++;
                    }
                }
                anchor = read + 1;
            }
        }
        return write;
    }


}
