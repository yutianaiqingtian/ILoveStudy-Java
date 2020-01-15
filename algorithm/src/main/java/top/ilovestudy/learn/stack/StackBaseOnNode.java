package top.ilovestudy.learn.stack;

import top.ilovestudy.learn.linkedlist.SNode;

/**
 * @author jinghui.zhang
 */
public class StackBaseOnNode<T> {
  private static final SNode EMPTY_NODE = new SNode<>(null);

  SNode<T> top = EMPTY_NODE;

  SNode<T> pop() {
    if (top.equals(EMPTY_NODE)) {
      throw new RuntimeException("can't finish pop operation in stack");
    }
    SNode<T> tNode = top;
    top = top.getNext();
    return tNode;
  }

  void push(SNode<T> sNode) {
    if (sNode == null) {
      throw new RuntimeException("can't push null node to stack");
    }
    if (!top.equals(EMPTY_NODE)) {
      sNode.setNext(top);
    }
    top = sNode;
  }
}
