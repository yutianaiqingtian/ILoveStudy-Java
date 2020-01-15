package top.ilovestudy.learn.sorts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static top.ilovestudy.learn.sorts.CountingSort.countingSort;

class CountingSortTest {

  @Test
  void should_sorted_arrays_by_use_counting_sort() {
    int[] elements = new int[]{2, 5, 3, 0, 2, 3, 0, 3};

    int[] sortedSorts = countingSort(elements);

    Assertions.assertArrayEquals(new int[]{0, 0, 2, 2, 3, 3, 3, 5}, sortedSorts);
  }
}