package offer.jianzhi.chapter05;

import java.util.ArrayList;

/**
 * 和为s的两个数字
 *
 * @author jhZhang
 * @date 2018/8/19
 */
public class Main41_1 {

    static int[] findNumberWithSum(int[] arrays, int sum) {
        if (arrays == null || arrays.length <= 2) {
            return null;
        }
        int start = 0;
        int end = arrays.length - 1;
        while (start < end) {
            int tmp = arrays[start] + arrays[end];
            if (tmp == sum) {
                return new int[]{arrays[start], arrays[end]};
            } else if (tmp < sum) {
                start++;
            } else {
                end--;
            }
        }
        return null;
    }

    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList list = new ArrayList();
        if (array == null || array.length <= 2) {
            return list;
        }
        int start = 0;
        int end = array.length - 1;
        while (start < end) {
            int tmp = array[start] + array[end];
            if (tmp == sum) {
                list.add(array[start]);
                list.add(array[end]);
                break;
            } else if (tmp < sum) {
                start++;
            } else {
                end--;
            }
        }
        return list;
    }
}
