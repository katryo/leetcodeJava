import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;

class MapSum {
    private HashMap<String, Integer> valueHash;
    private TriNode root;
    /** Initialize your data structure here. */
    public MapSum() {
        valueHash = new HashMap<String, Integer>();
        root = new TriNode();
    }

    public void insert(String key, int val) {
        int diff = val - valueHash.getOrDefault(key, 0);
        valueHash.put(key, val);
        TriNode cur = root;
        cur.score = val;

        for (char c: key.toCharArray()) {
            cur.children.putIfAbsent(c, new TriNode());
            cur = cur.children.get(c);
            cur.score += diff;
        }
    }

    public int sum(String prefix) {
        TriNode cur = root;
        for (char c: prefix.toCharArray()) {
            cur = cur.children.get(c);
            if (cur == null) return 0;
        }
        return cur.score;
    }

    private class TriNode {
        public int score;
        public HashMap<Character, TriNode> children = new HashMap<Character, TriNode>();
    }

    public static void main(String[] args) {
        MapSum obj = new MapSum();
        obj.insert("aa", 3);
        obj.sum("a");
        obj.insert("a", 2);
        System.out.println(obj.sum("a"));
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */