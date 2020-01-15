package top.ilovestudy.learn.queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayQueueTest {

  @Test
  void should_keen_order() {
    ArrayQueue arrayQueue = new ArrayQueue(4);

    arrayQueue.enqueue(1);
    arrayQueue.enqueue(2);

    assertEquals(1, arrayQueue.dequeue());
    assertEquals(2, arrayQueue.dequeue());
  }
}