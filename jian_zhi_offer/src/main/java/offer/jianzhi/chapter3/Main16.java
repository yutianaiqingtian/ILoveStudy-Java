package offer.jianzhi.chapter3;

/**
 * 输入一个链表的头结点，翻转改链表并输出翻转后的头结点
 *
 * @author jhZhang
 * @date 2018/5/15
 */
public class Main16 {
public ListNode reverseList(ListNode head) {
if (head == null) {
return null;
}
ListNode preListNode = null;
ListNode nowListNode = head;
while (nowListNode.nextNode != null) {
ListNode nextListNode = nowListNode.nextNode;
nowListNode.nextNode = preListNode;
preListNode = nowListNode;
nowListNode = nextListNode;
}
return nowListNode;
}
}
