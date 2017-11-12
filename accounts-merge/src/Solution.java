// https://leetcode.com/articles/accounts-merge/

import java.util.*;

public class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        DSU dsu = new DSU(1001);

        HashMap<String, Integer> emailToId = new HashMap<>();
        HashMap<String, String> emailToName = new HashMap<>();

        int id = 0;
        for (List<String> account: accounts) {
            boolean hasSeeName = false;
            String name = "";

            for (String emailOrName : account) {
                if (!hasSeeName) {
                    name = emailOrName;
                    hasSeeName = true;
                } else {
                    emailToName.put(emailOrName, name);

                    if (!emailToId.containsKey(emailOrName)) {
                        emailToId.put(emailOrName, id);
                        id++;
                    }

                    dsu.union(emailToId.get(account.get(1)), emailToId.get(emailOrName));
                }
            }
        }


        HashMap<Integer, List<String>> ans = new HashMap<>();

        for (String email: emailToName.keySet()) {
            int newId = dsu.find(emailToId.get(email));
            ans.putIfAbsent(newId, new LinkedList<>());
            ans.get(newId).add(email);
        }

        // Create ans, which is HashMap<Integer, List<String>> that is <ID, email>.

        List<List<String>> finalAns = new LinkedList<>();
        for (List<String> emails: ans.values()) {
            Collections.sort(emails);
            String name = emailToName.get(emails.get(0));
            emails.add(0, name);
            finalAns.add(emails);
        }

        return finalAns;
    }

    class DSU {
        int[] parent;

        DSU(int size) {
            parent = new int[size];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int yr = find(y);
            int xr = find(x);
            if (yr == xr) {
                return false;
            }

            parent[yr] = xr;
            return true;
        }

    }

    public static void main(String[] args) {
        List<List<String>> inputs = new LinkedList<List<String>>();
        List<String> input = new LinkedList<String>();
        input.add("david");
        input.add("a@email.com");
        input.add("b@email.com");
        inputs.add(input);

        List<String> input2 = new LinkedList<String>();
        input2.add("david");
        input2.add("a@email.com");
        inputs.add(input2);

        List<String> input3 = new LinkedList<String>();
        input3.add("jack");
        input3.add("jack@email.com");
        input3.add("jill@email.com");
        inputs.add(input3);

        List<List<String>> result = s.accountsMerge(inputs);
        System.out.println(result);
    }
}
