package top.ilovestudy.learn.linkedlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static top.ilovestudy.learn.linkedlist.SingleLinkedListPractice.checkCircle;
import static top.ilovestudy.learn.linkedlist.SingleLinkedListPractice.deleteLastKth;
import static top.ilovestudy.learn.linkedlist.SingleLinkedListPractice.findMiddleSNode;
import static top.ilovestudy.learn.linkedlist.SingleLinkedListPractice.reverse;

class SingleLinkedListPracticeTest {

  @Test
  void should_reverse_listed_list() {
    // 1  -> 2  -> 3 -> 4
    SingleLinkedList<Integer> linkedList = getIntegerSingleLinkedList();

    SNode<Integer> reverseSNode = reverse(linkedList.getHead());
    SingleLinkedList<Integer> reverseLinkedList = new SingleLinkedList<>(reverseSNode);

    assertEquals("4 -> 3 -> 2 -> 1", reverseLinkedList.toString());
  }

  @Test
  void should_return_true_when_contain_circle_in_linked_list() {
    //    	    ↗️ 2 ↘️
    //    0 ->1 	    3
    //	        ↖️ 4 ↙️
    SNode<Integer> node0 = new SNode<>(0);
    SNode<Integer> node1 = new SNode<>(1);
    SNode<Integer> node2 = new SNode<>(2);
    SNode<Integer> node3 = new SNode<>(3);
    SNode<Integer> node4 = new SNode<>(4);
    node0.setNext(node1);
    node1.setNext(node2);
    node2.setNext(node3);
    node3.setNext(node4);
    node4.setNext(node1);

    assertTrue(checkCircle(node0));
  }

  @Test
  void should_delete_Last_Kth_node_when_k() {
    // 1  -> 2  -> 3 -> 4
    SingleLinkedList<Integer> linkedList = getIntegerSingleLinkedList();

    deleteLastKth(linkedList.getHead(), 2);

    assertEquals("4 -> 3", linkedList.toString());
  }

  @Test
  void should_find_middle_node_when_linked_list_is_even() {
    // 1  -> 2  -> 3 -> 4
    SingleLinkedList<Integer> linkedList = getIntegerSingleLinkedList();

    SNode middleSNode = findMiddleSNode(linkedList.getHead());

    assertEquals(3, middleSNode.getElement());
  }

  @Test
  void should_find_middle_node_when_linked_list_is_odd() {
    // 1  -> 2  -> 3 -> 4 -> 5
    SingleLinkedList<Integer> linkedList = getIntegerSingleLinkedList();
    linkedList.addNodeToTailed(new SNode<>(5));

    SNode middleSNode = findMiddleSNode(linkedList.getHead());

    assertEquals(3, middleSNode.getElement());
  }

  private SingleLinkedList<Integer> getIntegerSingleLinkedList() {
    SingleLinkedList<Integer> linkedList = new SingleLinkedList<>();
    linkedList.addNodeToTailed(new SNode<>(1));
    linkedList.addNodeToTailed(new SNode<>(2));
    linkedList.addNodeToTailed(new SNode<>(3));
    linkedList.addNodeToTailed(new SNode<>(4));
    return linkedList;
  }
}