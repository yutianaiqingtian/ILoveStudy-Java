package offer.jianzhi.expend;

/**
 * 二叉搜索树的第 K 个结点
 *
 * @author jhZhang
 * @date 2018/10/24
 */
public class Main63 {

    public static void main(String[] args) {
        Main63 main63 = new Main63();
        TreeNode pRoot = main63.getTreeNode();
        System.out.println(main63.KthNode(pRoot, 3).val);
    }

    /**
     * 5                 <br>
     * /   \               <br>
     * 3    7               <br>
     * /\     /\              <br>
     * 2 4    6  8             <br>
     *
     * @return 返回二叉树的根结点
     */
    TreeNode getTreeNode() {
        TreeNode p1 = new TreeNode(5);
        TreeNode p2 = new TreeNode(3);
        TreeNode p3 = new TreeNode(7);
        TreeNode p4 = new TreeNode(2);
        TreeNode p5 = new TreeNode(4);
        TreeNode p6 = new TreeNode(6);
        TreeNode p7 = new TreeNode(8);

        p1.left = p2;
        p1.right = p3;

        p2.left = p4;
        p2.right = p5;

        p3.left = p6;
        p3.right = p7;
        return p1;
    }

    int index = 0; //计数器

    TreeNode KthNode(TreeNode root, int k) {
        //中序遍历寻找第k个
        if (root != null) {
            TreeNode node = KthNode(root.left, k);
            if (node != null) {
                return node;
            }
            index++;
            if (index == k) {
                return root;
            }
            node = KthNode(root.right, k);
            if (node != null) {
                return node;
            }
        }
        return null;
    }
}
