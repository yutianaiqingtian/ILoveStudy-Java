package offer.jianzhi.chapter3;

/**
 * @author jhZhang
 * @date 2018/5/15
 */
public class Main17 {
    ListNode mergerOrderListNode(ListNode node1, ListNode node2) {
        // 鲁棒性考虑
        if (node1 == null) {
            return node2;
        } else if (node2 == null) {
            return node1;
        }
        // 迭代查找每个最小值
        if (node1.data <= node2.data) {
            node1.nextNode = mergerOrderListNode(node1.nextNode, node2);
            return node1;
        } else {
            node2.nextNode = mergerOrderListNode(node1, node2.nextNode);
            return node2;
        }
    }
}
