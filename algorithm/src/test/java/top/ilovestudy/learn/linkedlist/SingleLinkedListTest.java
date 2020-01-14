package top.ilovestudy.learn.linkedlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SingleLinkedListTest {

  @Test
  void should_add_head_node_when_given_in_empty_list() {
    SNode<Integer> sNode = new SNode<>(2);
    SingleLinkedList singleLinkedList = new SingleLinkedList();

    singleLinkedList.addNodeToTailed(sNode);

    assertEquals(1, singleLinkedList.size());
    assertEquals(2, singleLinkedList.getHead().getElement());
  }

  @Test
  void should_add_tail_node_when_add_a_new_node() {
    // 2->3->4->5
    SingleLinkedList singleLinkedList = getTestSingleLink();

    singleLinkedList.addNodeToTailed(new SNode<>(6));

    assertEquals(5, singleLinkedList.size());
    assertEquals(6, singleLinkedList.getHead().getNext().getNext().getNext().getNext().getElement());
  }

  @Test
  void should_find_some_node() {
    // 2->3->4->5
    SingleLinkedList singleLinkedList = getTestSingleLink();

    SNode result = singleLinkedList.find(new SNode<>(3));

    assertEquals(3, result.getElement());
  }

  @Test
  void should_throw_exception_when_find_one_node_in_empty_list() {
    SingleLinkedList singleLinkedList = new SingleLinkedList();
    SNode<Integer> sNode = new SNode<>(2);

    assertThrows(RuntimeException.class, () -> singleLinkedList.find(sNode));
  }

  @Test
  void should_delete_some_node_when_delete_head_node() {
    // 2->3->4->5
    SingleLinkedList singleLinkedList = getTestSingleLink();
    System.out.println(singleLinkedList.toString());

    singleLinkedList.delete(new SNode<>(2));

    assertEquals(3, singleLinkedList.size());
    assertEquals(3, singleLinkedList.getHead().getElement());
  }

  @Test
  void should_delete_some_node_when_delete_middle_node() {
    // 2->3->4->5
    SingleLinkedList singleLinkedList = getTestSingleLink();

    singleLinkedList.delete(new SNode<>(4));

    assertEquals(3, singleLinkedList.size());
    assertEquals(2, singleLinkedList.getHead().getElement());
    assertEquals(5, singleLinkedList.getHead().getNext().getNext().getElement());
  }

  @Test
  void should_throw_exception_when_delete_node_in_empty_list() {
    // Empty List
    SingleLinkedList singleLinkedList = new SingleLinkedList();

    assertThrows(RuntimeException.class, () -> singleLinkedList.delete(new SNode<>(1)));
  }

  private SingleLinkedList getTestSingleLink() {
    SingleLinkedList singleLinkedList = new SingleLinkedList(new SNode<>(2));
    singleLinkedList.addNodeToTailed(new SNode<>(3));
    singleLinkedList.addNodeToTailed(new SNode<>(4));
    singleLinkedList.addNodeToTailed(new SNode<>(5));
    return singleLinkedList;
  }
}