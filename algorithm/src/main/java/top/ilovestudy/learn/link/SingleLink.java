package top.ilovestudy.learn.link;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author jinghui.zhang
 */
@NoArgsConstructor
@Getter
public class SingleLink {

  SNode head;

  public SingleLink(SNode head) {
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
      if (tNode.getValue().equals(sNode.getValue())) {
        return tNode;
      }
      tNode = tNode.getNext();
    }
    return tNode.getValue().equals(sNode.getValue()) ? tNode : null;
  }

  public void delete(SNode sNode) {
    if (head == null) {
      throw new RuntimeException("you are try to delete element in a empty list");
    }
    if (head.getValue().equals(sNode.getValue())) {
      this.head = this.head.getNext();
      return;
    }

    SNode tNode = head;
    while (tNode.getNext() != null) {
      if (tNode.getNext().getValue().equals(sNode.getValue())) {
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
      sb.append(tNode.getValue()).append(" -> ");
      tNode = tNode.getNext();
    }
    sb.append(tNode.getValue());
    return sb.toString();
  }
}
