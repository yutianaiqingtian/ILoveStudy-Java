package top.ilovestudy.learn.sorts;

/**
 * @author jinghui.zhang
 * @see <a href="https://time.geekbang.org/column/article/42038">GeekBang Link</a>
 */
public class CountingSort {
  static int[] countingSort(int[] elements) {
    // 确定划分出桶的最大值
    int maxValue = elements[0];
    for (int i = 1; i < elements.length; i++) {
      if (maxValue < elements[i]) {
        maxValue = elements[i];
      }
    }

    // 计算各个桶内的元素个数
    int[] buckets = new int[maxValue + 1];
    for (int i = 0; i < elements.length; i++) {
      buckets[elements[i]] += 1;
    }

    // 将桶内的元素，转换成历史累加值
    for (int i = 1; i < buckets.length; i++) {
      buckets[i] += buckets[i - 1];
    }

    // 新建一个数组存放将要排序的元素
    int[] sortedArrays = new int[elements.length];
    for (int i = elements.length - 1; i >= 0; i--) {
      // ① 获得累计值，代表了数据在排序后数组中的索引
      int sumCount = buckets[elements[i]];
      // ② 将元素插入数据中
      sortedArrays[sumCount - 1] = elements[i];
      // ③ 更新剩余元素个数
      buckets[elements[i]] -= 1;
    }
    return sortedArrays;
  }
}
