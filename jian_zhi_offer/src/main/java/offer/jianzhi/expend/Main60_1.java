package offer.jianzhi.expend;

import java.util.LinkedList;

/**
 * 按行打印二叉树，并且显示每一层的行号信息
 *
 * @author jhZhang
 * @date 2018/9/8
 */
public class Main60_1 {
    public static void main(String[] args) {
        TreeNode p1 = new TreeNode(8);
        TreeNode p2 = new TreeNode(6);
        TreeNode p3 = new TreeNode(10);
        TreeNode p4 = new TreeNode(5);
        TreeNode p5 = new TreeNode(7);
        TreeNode p6 = new TreeNode(9);
        TreeNode p7 = new TreeNode(11);

        p1.left = p2;
        p1.right = p3;

        p2.left = p4;
        p2.right = p5;

        p3.left = p6;
        p3.right = p7;

        Main60_1 test = new Main60_1();
        test.Print(p1);
    }

    public void Print(TreeNode pRoot) {
        if (pRoot == null) {
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        // 下一层需要打印的结点数
        int nextLevel = 0;
        // 当前层未打印完的结点数
        int toBePrinted = 1;

        while (!queue.isEmpty()) {
            TreeNode node = queue.pop();
            --toBePrinted;

            System.out.print(node.val + "\t");
            if (node.left != null) {
                queue.add(node.left);
                nextLevel++;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextLevel++;
            }

            if (toBePrinted == 0) {
                System.out.println();
                toBePrinted = nextLevel;
                nextLevel = 0;
            }
        }

    }
}
