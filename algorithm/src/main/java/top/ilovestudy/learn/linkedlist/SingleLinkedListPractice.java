package top.ilovestudy.learn.linkedlist;

/**
 * 1) 单链表反转
 * 2) 链表中环的检测
 * 3) 两个有序的链表合并
 * 4) 删除链表倒数第n个结点
 * 5) 求链表的中间结点
 */
public class SingleLinkedListPractice {

  static SNode reverse(SNode head) {
    SNode curr = head, pre = null;
    while (curr != null) {
      // 暂存下个节点信息
      SNode next = curr.next;
      // 更改指向
      curr.next = pre;
      // 遍历链表往 → 移动
      pre = curr;
      curr = next;
    }
    return pre;
  }

  static boolean checkCircle(SNode sNode) {
    if (sNode == null) {
      return false;
    }

    SNode fast = sNode.next;
    SNode slow = sNode;

    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;

      if (slow == fast) {
        return true;
      }
    }

    return false;
  }

  // 有序链表合并
  // public static SNode mergeSortedLists(SNode la, SNode lb) {
  // if (la == null) return lb;
  // if (lb == null) return la;

  // SNode p = la;
  // SNode q = lb;
  // SNode head;
  // if (p.data < q.data) {
  //   head = p;
  //   p = p.next;
  // } else {
  //   head = q;
  //   q = q.next;
  // }
  // SNode r = head;

  // while (p != null && q != null) {
  //   if (p.data < q.data) {
  //     r.next = p;
  //     p = p.next;
  //   } else {
  //     r.next = q;
  //     q = q.next;
  //   }
  //   r = r.next;
  // }

  // if (p != null) {
  //   r.next = p;
  // } else {
  //   r.next = q;
  // }

  // return head;
  //}


  static SNode deleteLastKth(SNode headNode, int k) {
    // 一个节点先走k步
    SNode fast = headNode;
    int i = 1;
    while (fast != null && i < k) {
      fast = fast.next;
      ++i;
    }

    if (fast == null) {
      return headNode;
    }

    SNode slow = headNode;
    SNode prev = null;
    while (fast.next != null) {
      fast = fast.next;
      prev = slow;
      slow = slow.next;
    }

    if (prev == null) {
      headNode = headNode.next;
    } else {
      prev.next = prev.next.next;
    }
    return headNode;
  }

  static SNode findMiddleSNode(SNode sNode) {
    if (sNode == null) {
      return null;
    }

    SNode fast = sNode;
    SNode slow = sNode;

    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }

    return slow;
  }
}
