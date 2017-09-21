/* -----------------------------------
 *  WARNING:
 * -----------------------------------
 *  Your code may fail to compile
 *  because it contains public class
 *  declarations.
 *  To fix this, please remove the
 *  "public" keyword from your class
 *  declarations.
 */
import java.util.Arrays;

class Solution {
    public boolean validPalindrome(String s) {
        String[] a = s.split("");
        return isValid(a, 0);
    }

    private boolean isValid(String[] a, int strike) {
        int len = a.length;
        int left = 0;
        int right = len - 1;

        while (left != right) {
            if (left > right) {
                return true;
            }
            if (!a[left].equals(a[right])) {
                if (strike == 1) {
                    return false;
                }
                String[] newAA = Arrays.copyOfRange(a, left + 1, right + 1);
                String[] newAB = Arrays.copyOfRange(a, left, right);
                boolean leftTry = isValid(newAA, 1);
                boolean rightTry = isValid(newAB, 1);
                return leftTry || rightTry;
            }
            left++;
            right--;
        }
        return true;
    }
    public static void main(String args[]) {
        Solution solution = new Solution();
        System.out.println(solution.validPalindrome("abccba"));
        System.out.println(solution.validPalindrome("abcxca"));
        System.out.println(solution.validPalindrome("abbcxca"));
        System.out.println(solution.validPalindrome("abcdcba"));
        System.out.println(solution.validPalindrome("abcdcbna"));
    }
}