package top.ilovestudy.learn.sorts;

/**
 * @author jinghui.zhang
 */
public class ThreeN2Sorts {

  static void bubbleSort(int[] elements) {
    for (int k = 0; k < elements.length; k++) {
      // flag 当某一趟冒泡排序发现没有数据交换，则可以提前结束冒泡
      boolean flag = false;
      for (int i = 0; i < elements.length - 1 - k; i++) {
        if (elements[i] > elements[i + 1]) {
          int temp = elements[i + 1];
          elements[i + 1] = elements[i];
          elements[i] = temp;
          flag = true;
        }
      }
      if (!flag) {
        break;
      }
    }
  }

  static void insertionSort(int[] elements) {
    for (int k = 1; k < elements.length; k++) {
      int insertValue = elements[k];
      for (int j = k - 1; j >= 0; j--) {
        if (insertValue > elements[j]) {
          // 插入数据
          elements[j + 1] = insertValue;
          break;
        } else {
          // 移动数据
          elements[j + 1] = elements[j];
        }
      }
    }
  }

  static void selectionSort(int[] elements) {
    for (int k = 0; k < elements.length; k++) {
      // 查找最小的元素的 Index
      int minElementIndex = k;
      for (int i = k + 1; i < elements.length; i++) {
        if (elements[i] < elements[minElementIndex]) {
          minElementIndex = i;
        }
      }

      int temp = elements[minElementIndex];
      elements[minElementIndex] = elements[k];
      elements[k] = temp;
    }
  }
}
