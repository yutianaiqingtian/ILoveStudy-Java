package offer.jianzhi.expend;

import org.junit.Test;

public class Main61Test {

    @Test
    public void print() {
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

        Main61 test = new Main61();
        test.Print(p1);
    }
}