package top.ilovestudy.learn.linkedlist;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author jinghui.zhang
 */
@NoArgsConstructor
@Getter
public class SingleLinkedList {

  SNode head;

  public SingleLinkedList(SNode head) {
    addNodeToTailed(head);
  }

  public int size() {
    int length = 0;
    SNode sNode = head;
    while (sNode != null) {
      length++;
      sNode = sNode.getNext();
    }
    return length;
  }

  public void addNodeToTailed(SNode sNode) {
    if (head == null) {
      head = sNode;
    } else {
      SNode tNode = head;
      while (tNode.getNext() != null) {
        tNode = tNode.getNext();
      }
      tNode.setNext(sNode);
    }
  }

  public SNode find(SNode sNode) {
    if (head == null) {
      throw new RuntimeException("you are find in a empty list");
    }

    SNode tNode = head;
    while (tNode.getNext() != null) {
      if (tNode.getElement().equals(sNode.getElement())) {
        return tNode;
      }
      tNode = tNode.getNext();
    }
    return tNode.getElement().equals(sNode.getElement()) ? tNode : null;
  }

  public void delete(SNode sNode) {
    if (head == null) {
      throw new RuntimeException("you are try to delete element in a empty list");
    }
    if (head.getElement().equals(sNode.getElement())) {
      this.head = this.head.getNext();
      return;
    }

    SNode tNode = head;
    while (tNode.getNext() != null) {
      if (tNode.getNext().getElement().equals(sNode.getElement())) {
        tNode.setNext(tNode.getNext().getNext());
      }
      tNode = tNode.getNext();
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    SNode tNode = head;
    while (tNode.getNext() != null) {
      sb.append(tNode.getElement()).append(" -> ");
      tNode = tNode.getNext();
    }
    sb.append(tNode.getElement());
    return sb.toString();
  }
}
