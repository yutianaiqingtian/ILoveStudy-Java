package offer.jianzhi.chapter05;

/**
 * @author jhZhang
 * @date 2018/8/15
 */
public class Main39_1 {

    public int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int nLeft = TreeDepth(root.left);
        int nRight = TreeDepth(root.right);
        return nLeft > nRight ? nLeft + 1 : nRight + 1;
    }
}