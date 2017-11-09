import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

public class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        ListIterator<List<String>> iter = accounts.listIterator();
        while (iter.hasNext()) {
            List<String> account = iter.next();
            ListIterator<String> accountIter = account.listIterator();
            String name = accountIter.next();
            while (accountIter.hasNext()) {
                String email = accountIter.next();
            }
        }
        return accounts;
    }

    private boolean conflicts(List<String> l, List<String> r) {
        ListIterator<String> liter = l.listIterator();
        String lname = liter.next();

        ListIterator<String> riter = r.listIterator();
        String rname = riter.next();

        if (lname != rname) {
            return false;
        }

        while (liter.hasNext()) {
            String sl = liter.next();
            if (r.contains(sl)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
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

        List<List<String>> result = s.accountsMerge(inputs);
        System.out.println(result);
    }
}
