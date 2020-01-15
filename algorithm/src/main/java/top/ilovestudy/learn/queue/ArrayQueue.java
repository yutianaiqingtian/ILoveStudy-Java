package top.ilovestudy.learn.queue;

/**
 * @author jinghui.zhang
 */
public class ArrayQueue {
  int[] elements;
  int capacity, head, tail = 0;

  public ArrayQueue(int capacity) {
    elements = new int[capacity];
    this.capacity = capacity;
  }

  boolean enqueue(int item) {
    if (tail == capacity) {
      return false;
    }
    elements[tail++] = item;
    return true;
  }

  int dequeue() {
    if (head == tail) {
      throw new RuntimeException("can't dequeue in empty queues");
    }
    return elements[head++];
  }
}
