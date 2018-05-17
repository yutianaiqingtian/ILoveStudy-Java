package offer.jianzhi.chapter3;

/**
 * 给定单向链表的头指针和一个节点，定义一个函数在O(1)时间删除该节点
 *
 * @author jhZhang
 * @date 2018/5/14
 */
public class Main13 {
    public static void main(String[] args) {
        ListNode head = new ListNode();
        ListNode second = new ListNode();
        ListNode third = new ListNode();
        head.nextNode = second;
        second.nextNode = third;
        head.data = 1;
        second.data = 2;
        third.data = 3;
        Main13 testDeleteNode = new Main13();
        testDeleteNode.deleteNode(head, third);
        System.out.println(head.nextNode.nextNode);
    }

    public void deleteNode(ListNode head, ListNode deListNode) {
        if (deListNode == null || head == null) {
            return;
        }
        // 如果删除头结点
        if (head == deListNode) {
            head = null;
        } else {
            // 如果删除尾节点
            if (deListNode.nextNode == null) {
                ListNode pointListNode = head;
                while (pointListNode.nextNode.nextNode != null) {
                    pointListNode = pointListNode.nextNode;
                }
                pointListNode.nextNode = null;
            } else {
                deListNode.data = deListNode.nextNode.data;
                deListNode.nextNode = deListNode.nextNode.nextNode;
            }
        }
    }
}

class ListNode {
    int data;
    ListNode nextNode;
}