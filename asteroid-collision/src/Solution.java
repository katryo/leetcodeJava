import java.util.*;

public class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        boolean hasConflict = true;
        while (hasConflict) {
            hasConflict = false;
            for (int i = 0; i < asteroids.length - 1; i++) {
                if (asteroids[i] > 0 && asteroids[i + 1] < 0) {
                    hasConflict = true;
                    int diff = asteroids[i] + asteroids[i + 1];
                    if (diff == 0) {
                        asteroids[i] = 0;
                        asteroids[i + 1] = 0;
                    } else if (diff > 0) {
                        asteroids[i + 1] = 0;
                    } else {
                        asteroids[i] = 0;
                    }
                }
            }

            int noZeroCount = 0;
            for (int i = 0; i < asteroids.length; i++) {
                if (asteroids[i] != 0) {
                    noZeroCount++;
                }
            }

            int[] newAsteroids = new int[noZeroCount];
            int index = 0;
            for (int i = 0; i < asteroids.length; i++) {
                if (asteroids[i] != 0) {
                    newAsteroids[index] = asteroids[i];
                    index++;
                }
            }
            asteroids = newAsteroids;
        }
        return asteroids;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] result;
        int[] input = {-10, 10};
        result = s.asteroidCollision(input);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
