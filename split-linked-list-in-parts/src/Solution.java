// https://leetcode.com/articles/split-linked-list-in-parts/

public class Solution {
    public ListNode[] splitListToParts(ListNode root, int k) {
        int size = 1;
        ListNode head = root;
        while (head.next != null) {
            head = head.next;
            size++;
        }

        int quo = size / k;
        int rem = size % k;

        ListNode cur = root;
        ListNode[] ans = new ListNode[k];

        for (int i = 0; i < k; i++) {
            head = new ListNode(0);
            ListNode write = head;

            for (int j = 0; j < quo + (i < rem ? 1 : 0); j++) {
                write.next = new ListNode(cur.val); // head.next = new ListNode(cur.val)
                write = write.next;
                if (cur.next != null) {
                    cur = cur.next;
                }
            }
            ans[i] = head.next;
        }

        return ans;
    }

    public static void main(String[] args) {
        ListNode ln1 = new ListNode(1);
        ListNode ln2 = new ListNode(2);
        ListNode ln3 = new ListNode(3);
        ListNode ln4 = new ListNode(4);
        ListNode ln5 = new ListNode(5);

        ln1.next = ln2;
        ln2.next = ln3;
        ln3.next = ln4;
        ln4.next = ln5;

        Solution s = new Solution();
        ListNode[] result = s.splitListToParts(ln1, 3);

        for (ListNode ln: result) {
            if (ln == null) {
                return;
            }
            ListNode lln = ln;
            while (lln.next != null) {
                System.out.print(lln.val);
                System.out.print(" ");
                lln = lln.next;
            }
            System.out.println(lln.val);
        }

    }
}

class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) {
        val = x;
    }
}
