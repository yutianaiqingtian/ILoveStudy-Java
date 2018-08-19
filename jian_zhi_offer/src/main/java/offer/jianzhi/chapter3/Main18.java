package offer.jianzhi.chapter3;

/**
 * @author jhZhang
 * @date 2018/5/15
 */
public class Main18 {
boolean HasSubTree(BinaryTreeNode pRoot1, BinaryTreeNode pRoot2) {
boolean result = false;
// 遍历
if (pRoot1 != null && pRoot2 != null) {
if (pRoot1.data == pRoot2.data) {
result = DoesTree1HaveTree2(pRoot1, pRoot2);
}
if (!result) {
result = HasSubTree(pRoot1.leftNode, pRoot2);
}
if (!result) {
result = HasSubTree(pRoot1.rightNode, pRoot2);
}
}
return result;
}

boolean DoesTree1HaveTree2(BinaryTreeNode pRoot1, BinaryTreeNode pRoot2) {
if (pRoot2 == null) {
return true;
}
if (pRoot1 == null) {
return false;
}
if (pRoot1.data != pRoot2.data) {
return false;
}
return DoesTree1HaveTree2(pRoot1.leftNode, pRoot2.leftNode)
&& DoesTree1HaveTree2(pRoot1.rightNode, pRoot2.rightNode);
}
}

class BinaryTreeNode {
int data;
BinaryTreeNode leftNode;
BinaryTreeNode rightNode;

@Override
public String toString() {
return "" + data;
}
}
