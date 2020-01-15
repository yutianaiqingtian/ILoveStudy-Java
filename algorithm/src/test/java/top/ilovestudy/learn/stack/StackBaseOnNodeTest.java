package top.ilovestudy.learn.stack;

import org.junit.jupiter.api.Test;
import top.ilovestudy.learn.linkedlist.SNode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StackBaseOnNodeTest {

  @Test
  void should_push_value_into_empty_stack() {
    StackBaseOnNode<Integer> stackBaseOnNode = new StackBaseOnNode<>();

    stackBaseOnNode.push(new SNode<>(1));
    stackBaseOnNode.push(new SNode<>(2));
    stackBaseOnNode.push(new SNode<>(3));

    assertEquals(3, stackBaseOnNode.top.getElement());
    assertEquals(2, stackBaseOnNode.top.getNext().getElement());
    assertEquals(1, stackBaseOnNode.top.getNext().getNext().getElement());
  }

  @Test
  void should_throw_exception_when_try_to_push_empty_node_to_stack() {
    StackBaseOnNode<Integer> stackBaseOnNode = new StackBaseOnNode<>();

    assertThrows(RuntimeException.class, () -> stackBaseOnNode.push(null));
  }

  @Test
  void should_pop_top_value_in_the_stack() {
    StackBaseOnNode<Integer> stackBaseOnNode = new StackBaseOnNode<>();
    stackBaseOnNode.push(new SNode<>(1));
    stackBaseOnNode.push(new SNode<>(2));
    stackBaseOnNode.push(new SNode<>(3));

    SNode<Integer> popNode = stackBaseOnNode.pop();

    assertEquals(3, popNode.getElement());
  }

  @Test
  void should_throw_exception_when_try_to_pop_in_empty_stack() {
    StackBaseOnNode<Integer> stackBaseOnNode = new StackBaseOnNode<>();

    assertThrows(RuntimeException.class, stackBaseOnNode::pop);
  }
}