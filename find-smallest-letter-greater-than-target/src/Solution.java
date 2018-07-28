class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int targetI = (int) target;
        for (int i = 0; i < letters.length; i++) {
            int letterI = (int) letters[i];
            if (letterI > targetI) {
                return (char) letterI;
            }
        }
        return letters[0];
    }

    public static void main(String args[]) {
        Solution s = new Solution();
        char[] letters = {'c', 'f', 'j'};
        System.out.println(s.nextGreatestLetter(letters, 'k'));
    }
}