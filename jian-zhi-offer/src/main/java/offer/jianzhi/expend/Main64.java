package offer.jianzhi.expend;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 数据流中的中位数
 *
 * @author jhZhang
 * @date 2018/10/24
 */
public class Main64 {

    int count;
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    //PriorityQueue默认是小顶堆，实现大顶堆，需要反转默认排序器
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(11, Comparator.reverseOrder());

    public void Insert(Integer num) {
        count++;
        // 判断偶数的高效写法
        if ((count & 1) == 0) {
            if (!maxHeap.isEmpty() && num < maxHeap.peek()) {
                maxHeap.offer(num);
                num = maxHeap.poll();
            }
            minHeap.offer(num);
        } else {
            if (!minHeap.isEmpty() && num > minHeap.peek()) {
                minHeap.offer(num);
                num = minHeap.poll();
            }
            maxHeap.offer(num);
        }
    }

    public Double GetMedian() {
        if (count == 0) {
            throw new RuntimeException("no available number!");
        }
        double result;
        //总数为奇数时，大顶堆堆顶就是中位数
        if ((count & 1) == 1) {
            result = maxHeap.peek();
        } else {
            result = (minHeap.peek() + maxHeap.peek()) / 2.0;
        }
        return result;
    }
}
