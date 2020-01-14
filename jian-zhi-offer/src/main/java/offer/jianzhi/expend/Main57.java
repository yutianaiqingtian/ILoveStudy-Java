package offer.jianzhi.expend;

/**
 * 删除链表中重复的结点
 *
 * @author jhZhang
 * @date 2018/9/2
 */
public class Main57 {
    public static void main(String[] args) {
        ListNode p1 = new ListNode(1);
        ListNode p2 = new ListNode(1);
        ListNode p3 = new ListNode(1);
        ListNode p4 = new ListNode(1);
        ListNode p5 = new ListNode(1);
        ListNode p6 = new ListNode(1);
        ListNode p7 = new ListNode(1);

        p1.next = p2;
        p2.next = p3;
        p3.next = p4;
        p4.next = p5;
        p5.next = p6;
        p6.next = p7;

        Main57 test = new Main57();
        ListNode node = test.deleteDuplication(p1);
        if (node != null) {
            System.out.println(node.toAllNodeString());
        } else {
            System.out.println("输出为空");
        }
    }

    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        pHead = getNextDiffNode(pHead);
        ListNode preNode = pHead;
        while (preNode != null) {
            ListNode diffNode = getNextDiffNode(preNode.next);
            preNode.next = diffNode;
            preNode = diffNode;
        }

        return pHead;
    }

    public ListNode getNextDiffNode(ListNode node) {
        if (node == null) {
            return null;
        }
        ListNode nextNode = node.next;
        if (nextNode == null) {
            return node;
        } else {
            if (node.val != nextNode.val) {
                return node;
            } else {
                while (nextNode != null && nextNode.val == node.val) {
                    nextNode = nextNode.next;
                }
                return getNextDiffNode(nextNode);
            }
        }
    }
}
