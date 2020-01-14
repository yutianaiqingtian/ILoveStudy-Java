package offer.jianzhi.expend;

/**
 * @author jhZhang
 * @date 2018/9/8
 */
public class Main58 {
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) {
            return null;
        }
        // ① 第一种情况
        if (pNode.right != null) {
            pNode = pNode.right;
            while ((pNode != null && pNode.left != null)) {
                pNode = pNode.left;
            }
            return pNode;
        }


        // ② 第二种情况 和 第三种情况
        TreeLinkNode pParent = pNode.next;
        while (pParent != null && pParent.right == pNode) {
            pNode = pNode.next;
            pParent = pNode.next;
        }

        return pParent != null ? pParent : null;
    }
}
