package offer.jianzhi.expend;

/**
 * 对称的二叉树
 *
 * @author jhZhang
 * @date 2018/9/8
 */
public class Main59 {
    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        return isSymmetrical(pRoot.left, pRoot.right);
    }

    boolean isSymmetrical(TreeNode p1, TreeNode p2) {
        if (p1 == null && p2 == null) {
            return true;
        }
        if (p1 != null && p2 != null) {
            if (p1.val == p2.val) {
                return isSymmetrical(p1.left, p2.right) && isSymmetrical(p1.right, p2.left);
            }
        }
        return false;
    }
}
