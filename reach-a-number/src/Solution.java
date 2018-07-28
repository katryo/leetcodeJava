class Solution {
    public int reachNumber(int target) {
        if (target < 0) {
            target = - target;
        }
        System.out.println(target);
        long sum = 0;
        for (int i = 1; i < 100000; i++) {
            sum = sum + i;
            if (sum == target) {
                return i;
            }
            if (sum > target) {
                if (sum % 2 == target % 2) {
                    return i;
                }
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
//        System.out.println(s.reachNumber(3));
//        System.out.println(s.reachNumber(2));
//        System.out.println(s.reachNumber(-3));
//        System.out.println(s.reachNumber(-2));
//
//        System.out.println(s.reachNumber(10));
//        System.out.println(s.reachNumber(- 10));
//
//        System.out.println(s.reachNumber(9));
//        System.out.println(s.reachNumber(- 9));

//        System.out.println(s.reachNumber(1));
//        System.out.println(s.reachNumber(2));
//        System.out.println(s.reachNumber(3));
//        System.out.println(s.reachNumber(4));
//        System.out.println(s.reachNumber(5));
//        System.out.println(s.reachNumber(6));

        System.out.println(s.reachNumber(1000000000));
        System.out.println(s.reachNumber(-1000000000));
    }
}