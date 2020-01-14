package offer.jianzhi.chapter05;

import java.util.ArrayList;

/**
 * 和为s的连续正数序列
 *
 * @author jhZhang
 * @date 2018/8/19
 */
public class Main41_2 {

    static ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList();
        int small = 1;
        int big = 2;
        int tmp = small + big;
        while (big < sum) {
            if (tmp == sum) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = small; i <= big; i++) {
                    list.add(i);
                }
                result.add(list);
                ++big;
                tmp += big;
            } else if (tmp > sum) {
                tmp -= small;
                ++small;
            } else {
                ++big;
                tmp += big;
            }
        }
        return result;
    }
}
