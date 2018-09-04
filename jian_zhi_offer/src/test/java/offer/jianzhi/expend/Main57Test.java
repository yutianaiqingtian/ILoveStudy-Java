package offer.jianzhi.expend;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class Main57Test {

    private static Main57 test;

    @BeforeClass
    public static void setUp() {
        test = new Main57();
    }


    @Test
    public void deleteDuplication1() {
        // 链表1->2->3->3->4->4->5 处理后为 1->2->5

        ListNode p1 = new ListNode(1);
        ListNode p2 = new ListNode(2);
        ListNode p3 = new ListNode(3);
        ListNode p4 = new ListNode(3);
        ListNode p5 = new ListNode(4);
        ListNode p6 = new ListNode(4);
        ListNode p7 = new ListNode(5);

        p1.next = p2;
        p2.next = p3;
        p3.next = p4;
        p4.next = p5;
        p5.next = p6;
        p6.next = p7;

        ListNode node = test.deleteDuplication(p1);

        Assert.assertEquals("1->2->5", node.toAllNodeString());
    }

    @Test
    public void deleteDuplication2() {
        // 链表{1,1,1,1,1,1,2} 处理后为 2

        ListNode p1 = new ListNode(1);
        ListNode p2 = new ListNode(1);
        ListNode p3 = new ListNode(1);
        ListNode p4 = new ListNode(1);
        ListNode p5 = new ListNode(1);
        ListNode p6 = new ListNode(1);
        ListNode p7 = new ListNode(2);

        p1.next = p2;
        p2.next = p3;
        p3.next = p4;
        p4.next = p5;
        p5.next = p6;
        p6.next = p7;

        ListNode node = test.deleteDuplication(p1);

        Assert.assertEquals("2", node.toAllNodeString());
    }

    @Test
    public void deleteDuplication3() {
        // 链表{1,1,1,1,1,1,1} 处理后为 {}

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

        ListNode node = test.deleteDuplication(p1);

        Assert.assertNull(node);
    }
}