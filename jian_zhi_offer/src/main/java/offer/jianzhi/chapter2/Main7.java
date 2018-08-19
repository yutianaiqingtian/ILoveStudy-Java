package offer.jianzhi.chapter2;

import java.util.Stack;

/**
 * @author jhZhang
 * @date 2018/5/17
 */
public class Main7 {
Stack<Integer> stack1 = new Stack<Integer>();
Stack<Integer> stack2 = new Stack<Integer>();

public void push(int node) {
stack2.add(node);
}

public int pop() {
if (stack1.isEmpty()) {
if (!stack2.isEmpty()) {
while (!stack2.isEmpty()) {
stack1.add(stack2.pop());
}
}
}
return stack1.pop();
}
}
