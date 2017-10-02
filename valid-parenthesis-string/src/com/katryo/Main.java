package com.katryo;

public class Main {
    public boolean checkValidString(String s) {
        if (s.equals("")) return true;
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;

        if (i == j) {
            if (chars[0] == '*') return true;
            return false;
        }

        if (chars[0] == ')' || chars[j] == '(') return false;

        int startLeftCount = 0;
        int endLeftCount = 0;
        int starLeftCount = 0;

        int startRightCount = 0;
        int endRightCount = 0;
        int starRightCount = 0;

        while (i < j) {
            char left = chars[i];
            char right = chars[j];

            if (left == '(') {
                startLeftCount++;
            }

            if (left == ')') {
                endLeftCount++;
            }

            if (left == '*') {
                starLeftCount++;
            }

            if (right == ')') {
                endRightCount++;
            }
            if (right == '(') {
                startRightCount++;
            }
            if (right == '*') {
                starRightCount++;
            }

            if (endLeftCount > starLeftCount + startLeftCount) return false;
            if (startRightCount > starRightCount + endRightCount) return false;

            i++;
            j--;
        }

        int sumStartCount = startLeftCount + startRightCount;
        int sumEndCount = endLeftCount + endRightCount;
        int sumStarCount = starLeftCount + starRightCount;

        if (i == j) {
            char mid = chars[i];
            if (mid == '(') sumStartCount++;
            if (mid == '*') sumStarCount++;
            if (mid == ')') sumEndCount++;
        }

        if (Math.abs(sumStartCount - sumEndCount) > sumStarCount) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Main m = new Main();
        System.out.println(m.checkValidString("((()*((*)*)((((()(())())((()()()*()())(()((*(()))*"));
//        System.out.println(m.checkValidString("((()))()(())(*()()())**(())()()()()((*()*))((*()*)"));
    }
}
