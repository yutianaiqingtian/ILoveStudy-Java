package offer.jianzhi.chapter4;

import java.util.Stack;

/**
 * @author jhZhang
 * @date 2018/5/16
 */
public class Main21 {
Stack<Integer> minStack = new Stack();
Stack<Integer> dataStack = new Stack();

public static void main(String[] args) {
Main21 main21 = new Main21();
main21.push(1);
main21.push(2);
main21.push(3);
main21.push(4);
while (!main21.minStack.isEmpty()) {
System.out.println(main21.minStack.pop());
}
}

Integer pop() {
if (dataStack.isEmpty()) {
return null;
}
minStack.pop();
return dataStack.pop();
}

void push(Integer item) {
minStack.push(item);
if (min() < item || minStack.isEmpty()) {
dataStack.push(min());
} else {
dataStack.push(item);
}
}

Integer min() {
if (minStack.isEmpty()) {
return null;
}
return dataStack.peek();
}
}

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的Min函数，
 * 在该栈中,min,push.pop的时间复杂度都是O（1）
 */
class StackWithMin extends MyStack<Integer> {
private MyStack<Integer> minStack = new MyStack<>();
private MyStack<Integer> dataStack = new MyStack<>();

@Override
public void push(Integer item) {
dataStack.push(item);
if (minStack.length == 0 || item <= minStack.head.data) {
minStack.push(item);
} else {
minStack.push(minStack.head.data);
}
}

@Override
public Integer pop() {
if (dataStack.length == 0 || minStack.length == 0) {
return null;
}
minStack.pop();
return dataStack.pop();

}

public Integer min() {
if (minStack.length == 0) {
return null;
}
return minStack.head.data;
}
}


/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的
 * 最小元素的Min函数，在该栈中,min,push.pop的时间复杂度都是O（1）
 *
 * @param <K>
 */
class MyStack<K> {

public ListNode<K> head;
public int length;

public void push(K item) {
ListNode<K> node = new ListNode<>();
node.data = item;
node.nextNode = head;
head = node;
length++;
}

public K pop() {
if (head == null) {
return null;
}
ListNode<K> temp = head;
head = head.nextNode;
length--;
return temp.data;
}

class ListNode<K> {
K data;
ListNode<K> nextNode;
}
}



