import java.util.*;

public class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new LinkedList<Integer>();
        for (int i = left; i < right+1; i++) {
            if (isSN(i)) {
                result.add(i);
            }
        }
        return result;
    }

    private boolean isSN(int n) {
        String numS = Integer.toString(n);
        char[] digits = numS.toCharArray();
        for (int j = 0; j < digits.length; j++) {
            int digit =  Character.getNumericValue(digits[j]);
            if (digit == 0) {
                return false;
            }
            if (n % digit != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        List<Integer> result = s.selfDividingNumbers(1, 22);
        for (Integer i: result) {
            System.out.println(i);
        }

    }
}
