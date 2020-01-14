package top.ilovestudy.learn.linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseLRULinkedListTest {


  private BaseLRULinkedList<Integer> baseLRULinkedList;

  @BeforeEach
  void setUp() {
    // 1 -> 2 -> 3 -> 4 -> 5
    baseLRULinkedList = new BaseLRULinkedList<>(new SNode<>(1));
    baseLRULinkedList.addNodeToTailed(new SNode<>(2));
    baseLRULinkedList.addNodeToTailed(new SNode<>(3));
    baseLRULinkedList.addNodeToTailed(new SNode<>(4));
    baseLRULinkedList.addNodeToTailed(new SNode<>(5));
  }

  @Test
  void should_insert_to_head_and_remove_old_when_update_linked_list_given_node_have_existed() {
    SNode<Integer> existedSNode = new SNode<>(3);

    baseLRULinkedList.findCache(existedSNode);

    assertEquals(3, baseLRULinkedList.getHead().getElement());
    assertEquals(5, baseLRULinkedList.size());
    assertEquals("3 -> 1 -> 2 -> 4 -> 5", baseLRULinkedList.toString());
  }

  @Test
  void should_insert_to_head_when_update_linked_list_given_node_not_existed_and_capacity_available() {
    SNode<Integer> unExistedSNode = new SNode<>(6);

    baseLRULinkedList.findCache(unExistedSNode);

    assertEquals(6, baseLRULinkedList.getHead().getElement());
    assertEquals(1, baseLRULinkedList.getHead().getNext().getElement());
    assertEquals(6, baseLRULinkedList.size());
  }

  @Test
  void should_insert_to_head_and_remove_last_node_when_update_linked_list_given_node_not_existed_and_capacity_unavailable() {
    // 1 -> 2 -> 3
    BaseLRULinkedList<Integer> baseLRULinkedList = new BaseLRULinkedList<>(3);
    baseLRULinkedList.addNodeToTailed(new SNode<>(1));
    baseLRULinkedList.addNodeToTailed(new SNode<>(2));
    baseLRULinkedList.addNodeToTailed(new SNode<>(3));
    SNode<Integer> unExistedSNode = new SNode<>(4);

    baseLRULinkedList.findCache(unExistedSNode);

    assertEquals(4, baseLRULinkedList.getHead().getElement());
    assertEquals(3, baseLRULinkedList.size());
    assertEquals(2, baseLRULinkedList.getHead().getNext().getNext().getElement());
    assertEquals("4 -> 1 -> 2", baseLRULinkedList.toString());
  }

}