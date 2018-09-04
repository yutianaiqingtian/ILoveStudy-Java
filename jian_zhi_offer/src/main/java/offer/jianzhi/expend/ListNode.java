package offer.jianzhi.expend;

/**
 * @author jhZhang
 * @date 2018/9/2
 */
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }


    public String toAllNodeString() {
        StringBuilder sb = new StringBuilder();
        ListNode node = this;
        while (node != null) {
            sb.append(node);
            node = node.next;
        }
        if (sb.length() >= 2) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();

    }

    @Override
    public String toString() {
        return val + "->";
    }
}