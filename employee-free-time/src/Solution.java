import java.util.*;

public class Solution {
    public List<Interval> employeeFreeTimee(List<List<Interval>> avails) {
        int OPEN = 0, CLOSE = 1;

        List<int[]> events = new ArrayList();
        for (List<Interval> employee: avails)
            for (Interval iv: employee) {
                events.add(new int[]{iv.start, OPEN});
                events.add(new int[]{iv.end, CLOSE});
            }

        Collections.sort(events, (a, b) -> a[0] != b[0] ? a[0]-b[0] : a[1]-b[1]);
        List<Interval> ans = new ArrayList();

        int prev = -1, bal = 0;
        for (int[] event: events) {
            // event[0] = time, event[1] = command
            if (bal == 0 && prev >= 0)
                ans.add(new Interval(prev, event[0]));
            bal += event[1] == OPEN ? 1 : -1;
            prev = event[0];
        }

        return ans;
    }
    public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
        int OPEN = 0;
        int CLOSE = 1;
        List<int[]> events = new ArrayList<>();

        for (List<Interval> employee: avails) {
            for (Interval interval: employee) {
                events.add(new int[]{interval.start, OPEN});
                events.add(new int[]{interval.end, CLOSE});
            }
        }

        events.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        List<Interval> ans = new ArrayList<>();

        int prev = -1;
        int balance = 0;
        for (int[] event : events) {
            if (balance == 0 && prev >= 0) {
                ans.add(new Interval(prev, event[0]));
            }
            balance += event[1] == OPEN ? 1 : -1;
            prev = event[0];
        }
        return ans;
    }

//        int OPEN = 0;
//        int CLOSE = 1;
//        List<int[]> events = new ArrayList<>();
//        for (List<Interval> employee: avails) {
//            for (Interval iv: employee) {a[1]
//                events.add(new int[]{iv.start, OPEN});
//                events.add(new int[]{iv.end, CLOSE});
//            }
//        }
//
//        events.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
//        List<Interval> ans = new ArrayList<>();
//
//        int prev = -1;
//        int bal = 0;
//        for (int[] event: events) {
//            if (bal == 0 && prev >= 0) {
//                ans.add(new Interval(prev, event[0]));
//            }
//            bal += event[1] == OPEN? 1 : -1;
//            prev = event[0];
//        }
//        return ans;

    public static void main(String[] args) {
        Solution s = new Solution();
        List<List<Interval>> input = new ArrayList<List<Interval>>();

        List<Interval> input0 = new ArrayList<Interval>();
        input0.add(new Interval(1, 2));

        List<Interval> input1 = new ArrayList<Interval>();
        input1.add(new Interval(5, 6));

        List<Interval> input2 = new ArrayList<Interval>();
        input2.add(new Interval(1,3));

        List<Interval> input3 = new ArrayList<Interval>();
        input2.add(new Interval(4,10));

        input.add(input0);
        input.add(input1);
        input.add(input2);
        input.add(input3);

        List<Interval> result = s.employeeFreeTime(input);

        for (Interval interval: result) {
            System.out.print(interval.start);
            System.out.print(", ");
            System.out.print(interval.end);
            System.out.println("");
        }
    }
}

class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
}