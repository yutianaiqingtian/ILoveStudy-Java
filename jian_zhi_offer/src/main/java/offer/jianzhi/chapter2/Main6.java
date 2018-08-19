package offer.jianzhi.chapter2;

/**
 * @author jhZhang
 * @date 2018/5/2
 */
public class Main6 {
public static void main(String[] args) {
int[] a = new int[]{1, 2, 4, 7, 3, 5, 6, 8};
int[] b = new int[]{4, 7, 2, 1, 5, 3, 8, 6};
BinaryTreeNode root = new Main6().contactCore(a, 0, a.length - 1, b, 0, b.length - 1);
System.out.println(root);
}

public BinaryTreeNode contactCore(int[] pre, int p1, int p2, int[] mid, int m1, int m2) {
if (p1 > p2 || m1 > m2) {
return null;
}
BinaryTreeNode root = new BinaryTreeNode(pre[p1]);
int splitPoint = m1;
for (int i = m1; i <= m2; i++) {
if (pre[p1] == mid[i]) {
splitPoint = i;
break;
}
}
root.left = contactCore(pre, p1 + 1, p1 + (splitPoint - m1), mid, m1, splitPoint - 1);
root.right = contactCore(pre, p1 + (splitPoint - m1) + 1, p2, mid, splitPoint + 1, m2);
return root;
}

class BinaryTreeNode {
int date;
BinaryTreeNode right;
BinaryTreeNode left;

public BinaryTreeNode(int date) {
this.date = date;
this.right = null;
this.left = null;
}

@Override
public String toString() {
return String.valueOf(this.date);
}
}
}
