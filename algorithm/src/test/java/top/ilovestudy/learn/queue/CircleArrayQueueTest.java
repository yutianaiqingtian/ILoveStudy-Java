package top.ilovestudy.learn.queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CircleArrayQueueTest {

  @Test
  void should_insert_into_one_element_to_begin_when_enqueue_given_the_end_of_element_but_not_filled() {
    CircleArrayQueue circleArrayQueue = new CircleArrayQueue(5);
    circleArrayQueue.elements = new int[]{1, 2, 3, 4, 5};
    circleArrayQueue.tail = 4;
    circleArrayQueue.head = 3;

    circleArrayQueue.enqueue(6);

    assertEquals(0, circleArrayQueue.getTail());
    assertEquals(6, circleArrayQueue.getElements()[0]);
  }

  @Test
  void should_throw_exception_when_enqueue_given_the_queue_have_fulled() {
    CircleArrayQueue circleArrayQueue = new CircleArrayQueue(5);
    circleArrayQueue.enqueue(1);
    circleArrayQueue.enqueue(2);
    circleArrayQueue.enqueue(3);
    circleArrayQueue.enqueue(4);

    assertThrows(RuntimeException.class, () -> circleArrayQueue.enqueue(6));
  }

  @Test
  void should_insert_into_begin_when_dequeue_given_the_end_of_element_but_not_filled() {
    CircleArrayQueue circleArrayQueue = new CircleArrayQueue(5);
    circleArrayQueue.elements = new int[]{1, 2, 3, 4, 5};
    circleArrayQueue.tail = 3;
    circleArrayQueue.head = 4;

    int dequeue = circleArrayQueue.dequeue();

    assertEquals(5, dequeue);
    assertEquals(0, circleArrayQueue.getHead());
  }

  @Test
  void should_throw_exception_when_dequeue_given_empty_queue() {
    CircleArrayQueue circleArrayQueue = new CircleArrayQueue(5);

    assertThrows(RuntimeException.class, circleArrayQueue::dequeue);
  }
}