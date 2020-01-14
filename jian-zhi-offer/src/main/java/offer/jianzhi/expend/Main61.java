package offer.jianzhi.expend;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 按之字形顺序打印二叉树
 *
 * @author jhZhang
 * @date 2018/9/9
 */
public class Main61 {
    public static void main(String[] args) {

    }

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        if (pRoot == null) {
            return results;
        }

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.add(pRoot);

        // 转换标记
        boolean flag = true;

        ArrayList<Integer> subList = new ArrayList<>();

        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            subList.add(node.val);

            if (flag) {
                if (node.left != null) {
                    stack2.add(node.left);
                }
                if (node.right != null) {
                    stack2.add(node.right);
                }
            } else {
                if (node.right != null) {
                    stack2.add(node.right);
                }
                if (node.left != null) {
                    stack2.add(node.left);
                }
            }

            if (stack1.isEmpty()) {
                Stack stack = stack1;
                stack1 = stack2;
                stack2 = stack;
                results.add(subList);
                subList = new ArrayList<>();
                flag = !flag;
            }
        }

        return results;
    }

}
