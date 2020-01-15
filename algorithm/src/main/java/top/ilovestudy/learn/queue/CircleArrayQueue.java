package top.ilovestudy.learn.queue;

import lombok.Getter;

/**
 * @author jinghui.zhang
 */
@Getter
public class CircleArrayQueue {
  int[] elements;
  int capacity, head, tail = 0;

  public CircleArrayQueue(int capacity) {
    elements = new int[capacity];
    this.capacity = capacity;
  }

  private int getNextIndex(int curIndex) {
    return (curIndex + 1) % capacity;
  }

  void enqueue(int item) {
    int moveIndex = getNextIndex(tail);
    if (moveIndex == head) {
      throw new RuntimeException("the queues had fulled");
    }
    elements[moveIndex] = item;
    tail = moveIndex;
  }

  int dequeue() {
    if (head == tail) {
      throw new RuntimeException("can't dequeue in empty queues");
    }
    int dequeueElement = elements[head];
    head = getNextIndex(head);
    return dequeueElement;
  }
}
