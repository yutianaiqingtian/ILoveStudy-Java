package offer.jianzhi.chapter4;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 * <p><p/>
 *
 * @author jhZhang
 * @date 2018/6/10
 */
public class Main23 {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
// 存放结果
        ArrayList<Integer> result = new ArrayList();
        if (root == null) {
            return result;
        }
// 存放结点
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        PrintFromTopToBottom(result, queue);
        return result;
    }

    /**
     * 使用递归来实现，关键的解题思路就是利用队列来存储结点信息
     *
     * @param result
     * @param queue
     */
    public void PrintFromTopToBottom(ArrayList<Integer> result, LinkedList<TreeNode> queue) {
        if (queue.isEmpty()) {
            return;
        }
        TreeNode node = queue.pop();
        if (node == null) {
            return;
        }
        if (node.val != 0) {
            result.add(node.val);
        }
        if (node.left != null) {
            queue.add(node.left);
        }
        if (node.right != null) {
            queue.add(node.right);
        }
        PrintFromTopToBottom(result, queue);
    }

    /**
     * 别人解法，使用循环来实现
     *
     * @param root
     * @return
     */
    public ArrayList<Integer> PrintFromTopToBottom1(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Deque<TreeNode> deque = new LinkedList<>();

        deque.add(root);
        while (!deque.isEmpty()) {
            TreeNode t = deque.pop();
            list.add(t.val);
            if (t.left != null) deque.add(t.left);
            if (t.right != null) deque.add(t.right);
        }
        return list;
    }
}

