package top.ilovestudy.learn.linkedlist;

public class BaseLRULinkedList<E> extends SingleLinkedList<E> {

  private final static Integer DEFAULT_CAPACITY = 10;

  private final int capacity;

  private int length = 0;

  public BaseLRULinkedList(int capacity) {
    this.capacity = capacity;
  }

  public BaseLRULinkedList(SNode<E> head) {
    super(head);
    length += 1;
    capacity = DEFAULT_CAPACITY;
  }

  private void addNodeToHead(SNode<E> sNode) {
    sNode.setNext(head);
    head = sNode;
    length += 1;
  }

  @Override
  public void addNodeToTailed(SNode<E> sNode) {
    super.addNodeToTailed(sNode);
    length += 1;
  }

  @Override
  public int size() {
    return length;
  }

  @Override
  public void delete(SNode<E> sNode) {
    super.delete(sNode);
    length -= 1;
  }

  public void findCache(SNode<E> sNode) {
    SNode<E> esNode = find(sNode);
    if (esNode != null) {
      delete(sNode);
      addNodeToHead(sNode);
    } else {
      if (length >= capacity) {
        deleteLastNode();
      }
      addNodeToHead(sNode);
    }
  }

  private void deleteLastNode() {
    if (head == null) {
      throw new RuntimeException("can't delete a empty linked list");
    }
    SNode<E> tNode = head;
    int i = 0;
    while (i++ < length - 2) {
      tNode = tNode.getNext();
    }
    tNode.setNext(null);
    length -= 1;
  }

}
