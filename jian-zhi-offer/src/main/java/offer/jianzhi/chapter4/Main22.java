package offer.jianzhi.chapter4;

import java.util.Stack;

/**
 * @author jhZhang
 * @date 2018/5/16
 */
public class Main22 {

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5};
//int[] b = new int[]{4, 5, 3, 2, 1};
        int[] b = new int[]{4, 3, 5, 1, 2};
        System.out.println(new Main22().IsPopOrder(a, b));
        System.out.println(new Main22().isRight(a, b));
    }

    boolean IsPopOrder(int[] pPush, int[] pPop) {
        if (pPop.length <= 0 || pPop.length <= 0) {
            return false;
        }
// 出栈顺序
        Stack<Integer> popOrder = new Stack<>();
        for (int i = pPop.length - 1; i >= 0; i--) {
            popOrder.push(pPop[i]);
        }
// 入栈顺序
        Stack<Integer> pushOrder = new Stack<>();
        for (int i = 0; i < pPush.length; i++) {
            pushOrder.push(pPush[i]);
            while (!popOrder.isEmpty() && !pushOrder.isEmpty()
                    && popOrder.peek().equals(pushOrder.peek())) {
                pushOrder.pop();
                popOrder.pop();
            }
        }
        if (pushOrder.isEmpty() && popOrder.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 输入两个整数序列，第一个序列表示压入顺序，判断第二个序列是否为弹出顺序.假设入栈所有数字均不相等
     */
    public boolean isPopOrder(int[] line1, int[] line2) {
        if (line1 == null || line2 == null) {
            return false;
        }
        int point1 = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < line2.length; i++) {
            if (!stack.isEmpty() && stack.peek() == line2[i]) {
                stack.pop();
            } else {
                if (point1 == line1.length) {
                    return false;
                } else {
                    do {
                        stack.push(line1[point1++]);
                    } while (stack.peek() != line2[i]
                            && point1 != line1.length);
                    if (stack.peek() == line2[i]) {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    boolean isRight(int[] line1, int[] line2) {
        if (line1 == null || line2 == null) {
            return false;
        }
        int index = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < line2.length && index < line1.length; ) {
// ①
            if (!stack.isEmpty() && stack.peek() == line2[i]) {
                stack.pop();
                i++;
            } else {
// ②
                stack.push(line1[index++]);
            }
        }
// ③
        if (index == line1.length && stack.isEmpty()) {
            return true;
        }
        return false;
    }
}
