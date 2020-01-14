package offer.jianzhi.chapter06;

import java.util.LinkedList;

/**
 * 经典的解法，用环形链表模拟圆圈
 *
 * @author jhZhang
 * @date 2018/8/22
 */
public class Main45_1 {
    static int removeList(LinkedList lists, int start, int m) {
        int index = start + m;
        while (index > lists.size()) {
            index = index - lists.size();
        }
        index = index - 1;
        lists.remove(index);
        return index < lists.size() ? index : 0;
    }

    static int LastRemaining_Solution(int n, int m) {
        if (n <= 0 || m <= 0) {
            return 0;
        }
        LinkedList<Integer> lists = new LinkedList();
        for (int i = 0; i < n; i++) {
            lists.add(i);
        }
        int start = 0;
        while (lists.size() > 1) {
            start = removeList(lists, start, m);
        }
        return lists.get(0);
    }

    public static void main(String[] args) {
        System.out.println(LastRemaining_Solution(6, 7));
    }

}
