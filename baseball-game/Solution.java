import java.util.ArrayDeque;

class Solution {
    private ArrayDeque<Integer> stack = new ArrayDeque<Integer>();

    public static void main(String[] args) {
        String[] input = {"5","-2","4","C","D","9","+","+"};
        Solution s = new Solution();
        System.out.println(s.calPoints(input));
    }
    public int calPoints(String[] ops) {
        int result = 0;
        for (int i = 0; i < ops.length; i++) {
            System.out.println(result);
            String point = ops[i];
            if (point.equals("C")) {
                int lastPlus = stack.removeFirst();
                result -= lastPlus;
                continue;
            }
            if (point.equals("D")) {
                int lastPlus = stack.getFirst();
                int doublePlus = 2 * lastPlus;

                result += doublePlus;
                stack.addFirst(doublePlus);
                continue;
            }
            if (point.equals("+")) {
                int lastPlus = stack.removeFirst();
                int lastLastPlus = stack.getFirst();
                stack.addFirst(lastPlus);
                int sum = lastLastPlus + lastPlus;
                result += sum;
                stack.addFirst(sum);
                continue;
            }
            int num = Integer.parseInt(point);
            result += num;
            stack.addFirst(num);
        }
        return result;
    }
}