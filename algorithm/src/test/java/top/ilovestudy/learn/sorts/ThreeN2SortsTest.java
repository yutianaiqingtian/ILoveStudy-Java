package top.ilovestudy.learn.sorts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static top.ilovestudy.learn.sorts.ThreeN2Sorts.bubbleSort;
import static top.ilovestudy.learn.sorts.ThreeN2Sorts.insertionSort;
import static top.ilovestudy.learn.sorts.ThreeN2Sorts.selectionSort;

class ThreeN2SortsTest {

  @Test
  void should_sorted_array_use_bubble_sort() {
    int[] arrays = new int[]{2, 9, 3, 4, 8, 3};

    bubbleSort(arrays);

    assertArrayEquals(new int[]{2, 3, 3, 4, 8, 9}, arrays);
  }

  @Test
  void should_sorted_array_use_insertion_sort() {
    int[] arrays = new int[]{2, 9, 3, 4, 8, 3};

    insertionSort(arrays);

    assertArrayEquals(new int[]{2, 3, 3, 4, 8, 9}, arrays);
  }

  @Test
  void should_sorted_array_use_select_sort() {
    int[] arrays = new int[]{2, 9, 3, 4, 8, 3};

    selectionSort(arrays);

    assertArrayEquals(new int[]{2, 3, 3, 4, 8, 9}, arrays);
  }
}