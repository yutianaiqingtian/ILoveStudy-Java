package top.ilovestudy.learn.link;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SingleLinkTest {

  @Test
  void should_add_head_node_when_given_in_empty_list() {
    SNode<Integer> sNode = new SNode<>(2);
    SingleLink singleLink = new SingleLink();

    singleLink.addNodeToTailed(sNode);

    assertEquals(1, singleLink.size());
    assertEquals(2, singleLink.getHead().getElement());
  }

  @Test
  void should_add_tail_node_when_add_a_new_node() {
    // 2->3->4->5
    SingleLink singleLink = getTestSingleLink();

    singleLink.addNodeToTailed(new SNode(6));

    assertEquals(5, singleLink.size());
    assertEquals(6, singleLink.getHead().getNext().getNext().getNext().getNext().getElement());
  }

  @Test
  void should_find_some_node() {
    // 2->3->4->5
    SingleLink singleLink = getTestSingleLink();

    SNode<Integer> result = singleLink.find(new SNode(3));

    assertEquals(3, result.getElement());
  }

  @Test
  void should_throw_exception_when_find_one_node_in_empty_list() {
    SingleLink singleLink = new SingleLink();
    SNode<Integer> sNode = new SNode<>(2);

    assertThrows(RuntimeException.class, () -> singleLink.find(sNode));
  }

  @Test
  void should_delete_some_node_when_delete_head_node() {
    // 2->3->4->5
    SingleLink singleLink = getTestSingleLink();
    System.out.println(singleLink.toString());

    singleLink.delete(new SNode<>(2));

    assertEquals(3, singleLink.size());
    assertEquals(3, singleLink.getHead().getElement());
  }

  @Test
  void should_delete_some_node_when_delete_middle_node() {
    // 2->3->4->5
    SingleLink singleLink = getTestSingleLink();

    singleLink.delete(new SNode<>(4));

    assertEquals(3, singleLink.size());
    assertEquals(2, singleLink.getHead().getElement());
    assertEquals(5, singleLink.getHead().getNext().getNext().getElement());
  }

  @Test
  void should_throw_exception_when_delete_node_in_empty_list() {
    // Empty List
    SingleLink singleLink = new SingleLink();

    assertThrows(RuntimeException.class, () -> singleLink.delete(new SNode<>(1)));
  }

  private SingleLink getTestSingleLink() {
    SingleLink singleLink = new SingleLink(new SNode<>(2));
    singleLink.addNodeToTailed(new SNode(3));
    singleLink.addNodeToTailed(new SNode(4));
    singleLink.addNodeToTailed(new SNode(5));
    return singleLink;
  }
}