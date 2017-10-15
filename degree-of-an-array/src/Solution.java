import javax.swing.text.MutableAttributeSet;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findShortestSubArray(int[] nums) {
        HashMap<Integer, ArrayList<Integer>> table = new HashMap<Integer, ArrayList<Integer>>();
        for (int i = 0; i < nums.length; i++) {
            if (table.containsKey(nums[i])) {
                ArrayList<Integer> cell = table.get(nums[i]);
                cell.set(0, cell.get(0) + 1);
                cell.set(2, i);
            } else {
                ArrayList<Integer> cell = new ArrayList<Integer>();
                cell.add(1); // count
                cell.add(i); // start
                cell.add(i); // end
                table.put(nums[i], cell);
            }
        }

        int answer = 50000;
        int maxDegree = 1;
        for(Map.Entry<Integer, ArrayList<Integer>> e : table.entrySet()) {
            ArrayList<Integer> cell = e.getValue();

            int degree = cell.get(0);
            if (degree > maxDegree) {
                int length = cell.get(2) - cell.get(1) + 1;
                answer = length;
                maxDegree = degree;
            } else if (degree == maxDegree) {
                int length = cell.get(2) - cell.get(1) + 1;
                answer = Math.min(answer, length);
            }
        }
        return answer;
    }
    public static void main(String[] args) {
        int[] input = {1, 2, 2, 3, 1}; // [1,2,2,3,1,4,2]
        Solution s = new Solution();
        System.out.println(s.findShortestSubArray(input));
    }
}

