package offer.jianzhi.chapter3;

/**
 * @author jhZhang
 * @date 2018/5/15
 */
public class Main19 {
    public static void main(String[] args) {
        BinaryTreeNode root = getBinaryTreeNode();
        BinaryTreeNode rootBinaryTreeNode = new Main19().MirrorRecursively(root);
// 空指针测试
        new Main19().MirrorRecursively(null);
        System.out.println(root);
    }

    static BinaryTreeNode getBinaryTreeNode() {
        BinaryTreeNode root1 = new BinaryTreeNode();
        BinaryTreeNode node1 = new BinaryTreeNode();
        BinaryTreeNode node2 = new BinaryTreeNode();
        BinaryTreeNode node3 = new BinaryTreeNode();
        BinaryTreeNode node4 = new BinaryTreeNode();
        BinaryTreeNode node5 = new BinaryTreeNode();
        BinaryTreeNode node6 = new BinaryTreeNode();
        root1.leftNode = node1;
        root1.rightNode = node2;

        node1.leftNode = node3;
        node1.rightNode = node4;

        node2.leftNode = node5;
        node2.rightNode = node6;

        root1.data = 8;

        node1.data = 6;
        node2.data = 10;

        node3.data = 5;
        node4.data = 7;
        node5.data = 9;
        node6.data = 11;
        return root1;
    }

    BinaryTreeNode MirrorRecursively(BinaryTreeNode pNode) {
        if (pNode != null) {
            BinaryTreeNode node = pNode.leftNode;
            pNode.leftNode = pNode.rightNode;
            pNode.rightNode = node;
            if (pNode.leftNode != null) {
                MirrorRecursively(pNode.leftNode);
            }
            if (pNode.rightNode != null) {
                MirrorRecursively(pNode.rightNode);
            }
        }
        return pNode;
    }
}
