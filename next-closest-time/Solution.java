class Solution {
    public static void main(String[] args) {
        String input = "23:59";
        Solution s = new Solution();
        System.out.println(s.nextClosestTime(input));
    }
    public String nextClosestTime(String time) {
        char[] chars = time.toCharArray();
        int[] nums = {Character.getNumericValue(chars[0]), Character.getNumericValue(chars[1]), Character.getNumericValue(chars[3]), Character.getNumericValue(chars[4])};

        int original = nums[3] + nums[2] * 10 + nums[1] * 60 + nums[0] * 600;

        String result = "";
        int min = 10000000;
        int first = 0;
        int second = 0;
        int third = 0;
        int fourth = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 4; l++) {
                        if (nums[j] > 5) {
                            continue;
                        }
                        if (nums[k] + nums[l] * 10 > 24) {
                            continue;
                        }
                        int challenger = nums[i] + nums[j] * 10 + nums[k] * 60 + nums[l] * 600;
                        int diff = challenger - original;
                        if (diff == 0) {
                            continue;
                        }
                        if (diff < 0) {
                            diff += 3600 * 24;
                        }

                        if (min > diff) {
                            min = diff;
                            first = l;
                            second = k;
                            third = j;
                            fourth = i;
                        }
                    }
                }
            }
        }
        return String.format("%d%d:%d%d", nums[first], nums[second], nums[third], nums[fourth]);
    }
}