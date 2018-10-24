package offer.jianzhi.expend;

/**
 * 序列化二叉树
 *
 * @author jhZhang
 * @date 2018/10/19
 */
public class Main62 {

    public static void main(String[] args) {
        Main62 main = new Main62();
        TreeNode node = main.getTreeNode();
        String str = main.Serialize(node);
        System.out.println("序列化后的二叉树" + str);
        TreeNode root = main.Deserialize(str);
        System.out.println(root.val);
    }

    /**
     * 1                 <br>
     * /   \               <br>
     * 2    3               <br>
     * /     /\              <br>
     * 4     5  6             <br>
     * 该二叉树序列化后的结果 “1,2,4,$,$,$,3,5,$,$,6,$,$”
     *
     * @return 返回二叉树的根结点
     */
    TreeNode getTreeNode() {
        TreeNode p1 = new TreeNode(1);
        TreeNode p2 = new TreeNode(2);
        TreeNode p3 = new TreeNode(3);
        TreeNode p4 = new TreeNode(4);
        TreeNode p5 = new TreeNode(5);
        TreeNode p6 = new TreeNode(6);

        p1.left = p2;
        p1.right = p3;

        p2.left = p4;

        p3.left = p5;
        p3.right = p6;
        return p1;
    }

    /**
     * 序列化
     *
     * @param root
     * @return
     */
    String Serialize(TreeNode root) {
        if (root == null) {
            return "$";
        }
        String result = String.valueOf(root.val);
        result += "," + Serialize(root.left);
        result += "," + Serialize(root.right);
        return result;
    }

    /**
     * 反序列化
     *
     * @param str
     * @return
     */
    TreeNode Deserialize(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        String[] items = str.split(",");
        int[] index = {0};
        return Deserialize(items, index);
    }

    TreeNode Deserialize(String[] chars, int[] index) {
        if (index[0] >= chars.length || chars[index[0]].equals("$")) {
            index[0] += 1;
            return null;
        }

        int val = Integer.valueOf(chars[index[0]]);
        TreeNode node = new TreeNode(val);
        index[0] += 1;
        node.left = Deserialize(chars, index);
        node.right = Deserialize(chars, index);
        return node;
    }
}

