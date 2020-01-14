package offer.jianzhi.expend;

import java.util.HashSet;
import java.util.Set;

/**
 * 链表中环的入口结点
 *
 * @author jhZhang
 * @date 2018/9/1
 */
public class Main56 {
    public static void main(String[] args) {
        ListNode p1 = new ListNode(1);
        ListNode p2 = new ListNode(2);
        ListNode p3 = new ListNode(3);
        ListNode p4 = new ListNode(4);
        ListNode p5 = new ListNode(5);
        ListNode p6 = new ListNode(6);

        p1.next = p2;
        p2.next = p3;
        p3.next = p4;
        p4.next = p5;
        p5.next = p6;
        p6.next = p3;

        Main56 test = new Main56();
        System.out.println(test.EntryNodeOfLoop2(p1));

    }

    /**
     * 方法 1 ，使用 O(n) 的空间效率+ O（n）的时间效率
     *
     * @param pHead
     * @return
     */
    public ListNode EntryNodeOfLoop1(ListNode pHead) {
        Set<ListNode> sets = new HashSet<>();
        while (pHead != null) {
            if (sets.contains(pHead)) {
                return pHead;
            } else {
                sets.add(pHead);
            }
            pHead = pHead.next;
        }
        return null;
    }

    public int getAnnularLegth(ListNode pHead) {
        // 表示不存在环
        int result = -1;
        if (pHead == null) {
            return result;
        }
        // 寻找第一个相遇的结点
        ListNode p1 = pHead, p2 = pHead;
        do {
            if (p1.next == null || p2.next == null) {
                return result;
            }
            p1 = p1.next;
            p2 = p2.next.next;
        } while (p1 != null && p2 != null && p1 != p2);

        // 第一个结点
        ListNode sameNode = p1;
        result = 0;
        do {
            p1 = p1.next;
            result++;
        } while (p1 != sameNode);
        return result;
    }

    public ListNode EntryNodeOfLoop2(ListNode pHead) {
        int K = getAnnularLegth(pHead);
        if (K == -1) {
            return null;
        }

        ListNode p1 = pHead;
        while (K > 0) {
            p1 = p1.next;
            K--;
        }

        ListNode p2 = pHead;
        while (p1 != null && p2 != null && p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
}