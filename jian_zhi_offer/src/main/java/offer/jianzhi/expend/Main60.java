package offer.jianzhi.expend;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 按行打印二叉树，并且显示每一层的行号信息
 *
 * @author jhZhang
 * @date 2018/9/8
 */
public class Main60 {

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

    ArrayList<ArrayList<Integer>> Print2(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList();
        if (pRoot == null) {
            return lists;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();

        ArrayList<Integer> sublists = new ArrayList();
        queue.add(pRoot);
        // 下一层需要打印的结点数
        int nextLevel = 0;
        // 当前层未打印完的结点数
        int toBePrinted = 1;

        while (!queue.isEmpty()) {
            TreeNode node = queue.pop();
            sublists.add(node.val);
            --toBePrinted;

            if (node.left != null) {
                queue.add(node.left);
                nextLevel++;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextLevel++;
            }

            if (toBePrinted == 0) {
                lists.add(sublists);
                sublists = new ArrayList();
                toBePrinted = nextLevel;
                nextLevel = 0;
            }
        }
        return lists;
    }
}
