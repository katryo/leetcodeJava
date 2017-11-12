import java.util.*;


public class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        HashMap<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>();
        HashMap<String, String> emailToName = new HashMap<>();

        for (List<String> account: accounts) {
            String name = "";

            for (String emailOrName: account) {
                if (name == "") {
                    name = emailOrName;
                    continue;
                }
                graph.computeIfAbsent(emailOrName, x-> new ArrayList<>()).add(account.get(1));
                graph.computeIfAbsent(account.get(1), x-> new ArrayList<>()).add(emailOrName);
                emailToName.put(emailOrName, name);
            }
        }

        List<List<String>> ans = new LinkedList<>();
        HashSet<String> seen = new HashSet<>();
        for (String email: graph.keySet()) {
            Stack<String> stack = new Stack<>();
            stack.push(email);

            List<String> ansElem = new LinkedList<>();

            while (!stack.isEmpty()) {
                String target = stack.pop();
                ArrayList<String> children = graph.get(target);
                for (String em: children) {
                    if (!seen.contains(em)) {
                        ansElem.add(em);
                        stack.push(em);
                        seen.add(em);
                    }
                }
            }
            if (!ansElem.isEmpty()) {
                Collections.sort(ansElem);
                ansElem.add(0, emailToName.get(ansElem.get(0)));
                ans.add(ansElem);
            }
        }
        return ans;
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

        Solution s = new Solution();
        List<List<String>> result = s.accountsMerge(inputs);
        System.out.println(result);
    }
}
