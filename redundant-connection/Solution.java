public class Solution {
    public static void main(String[] args) {
        int[] v = {5, 8};
        System.out.println(hasDouble5or8a(v));
    }

    public static boolean hasDouble5or8a(int[] vals) {

        boolean two5s = false;
        boolean two8s = false;

        for (int i = 0; i < vals.length-1; i++) {
            if (vals[i-1] == vals[i]) {
                if (vals[i] == 5) { two5s = true; }
                else if (vals[i] == 8) { two8s = true; }
                if (two5s && two8s) { return false; } // don't need to look at the rest
                // of the array for this case
            }
        }

        return (two5s || two8s);  // true if only one is true, since we already returned
        // false if both were true
    }


    public static boolean hasDouble5or8(int[] vals) {

        boolean two5s = false;
        boolean two8s = false;

        for (int i = 0; i < vals.length-1; i++) {
            if (vals[i-1] == vals[i]) {
                if (vals[i] == 5) { two5s = true; }
                else if (vals[i] == 8) { two8s = true; }
            }
        }

        return (two5s || two8s) && !(two5s && two8s); //*

    }
}
