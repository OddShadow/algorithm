package leetcode.s0002;

// 20:00
// 链表题用递归也没问题，业务写多了，习惯不用递归
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        } else if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        
        ListNode point1 = l1;
        ListNode point2 = l2;
        int plus = 0;
        ListNode head = new ListNode();
        ListNode point3 = head;
        int sum = 0;
        
        while (point1 != null || point2 != null) {
            if (point1 != null && point2 != null) {
                sum = point1.val + point2.val + plus;
                point1 = point1.next;
                point2 = point2.next;
            } else if (point1 == null) {
                sum = point2.val + plus;
                point2 = point2.next;
            } else if (point2 == null) {
                sum = point1.val + plus;
                point1 = point1.next;
            }
            ListNode temp = new ListNode(sum % 10);
            plus = sum / 10;
            point3.next = temp;
            point3 = temp;
        }
        
        if (plus == 1) {
            ListNode temp = new ListNode(plus);
            point3.next = temp;
            point3 = temp;
        }
        
        return head.next;
    }
}