class Solution {
    public int monotoneIncreasingDigits(int N) {
        String s = Integer.toString(N);
        int len = s.length();

        char chars[] = new char[len];
        int max = 0;
        boolean isMono = true;
        int firstNotMaxIndex = -1;
        for (int i = 0; i < len; i++) {
            chars[i] = s.charAt(i);
            if (Character.getNumericValue(chars[i]) < max) {
                isMono = false;
                if (firstNotMaxIndex == -1) {
                    firstNotMaxIndex = i;
                }
            }
            max = Math.max(Character.getNumericValue(s.charAt(i)), max);
        }

        if (isMono) {
            return N;
        }

        int newLen = len;
        int changedIndex = firstNotMaxIndex - 1;
        int digitToBeChanged = Character.getNumericValue(chars[firstNotMaxIndex - 1]);

        if (changedIndex != 0) {
            int leftDigit = Character.getNumericValue(chars[changedIndex - 1]);

            while (digitToBeChanged <= leftDigit) {
                changedIndex--;
                if (changedIndex == 0) {
                    break;
                }
                leftDigit = Character.getNumericValue(chars[changedIndex - 1]);
            }
        }

        int firstChangedDigit = digitToBeChanged - 1; // must not be 0


        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < changedIndex; i++) {
            sb.append(chars[i]);
        }
        sb.append(firstChangedDigit);

        for (int i = 0; i < len - changedIndex - 1; i++) {
            sb.append(9);
        }

        String ans = sb.toString();

        return Integer.parseInt(ans);

    }

    public static void main(String args[]) {
        Solution s = new Solution();
        int N = 120;
        int result = s.monotoneIncreasingDigits(N);
        System.out.println(result);
    }
}