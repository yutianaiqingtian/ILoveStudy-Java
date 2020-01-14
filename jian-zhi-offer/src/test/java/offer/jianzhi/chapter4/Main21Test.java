package offer.jianzhi.chapter4;

import org.junit.Test;

import static org.junit.Assert.*;

public class Main21Test {

@Test
public void pop() {
MyStack<Integer> stack = new MyStack<>();
stack.push(2);
assertEquals(stack.pop(), Integer.valueOf(2));
}

@Test
public void push() {
}

@Test
public void min() {
}
}