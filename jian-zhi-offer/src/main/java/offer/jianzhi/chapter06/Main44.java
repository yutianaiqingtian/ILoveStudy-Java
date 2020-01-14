package offer.jianzhi.chapter06;

import java.util.Arrays;

/**
 * 面试题44：扑克牌的顺子
 *
 * @author jhZhang
 * @date 2018/8/22
 */
public class Main44 {
    public static void main(String[] args) {
        int[] numbers = new int[]{0, 1, 3, 4, 5};
        System.out.println(isContinuous(numbers));
    }

    static boolean isContinuous(int[] numbers) {
        if (numbers == null || numbers.length <= 0) {
            return false;
        }
        Arrays.sort(numbers);
        int gap = 0;
        int zeroTimes = 0;
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] == 0) {
                zeroTimes++;
                continue;
            }
            // 如果为顺子，直接返回false
            if (numbers[i + 1] == numbers[i]) {
                return false;
            } else if (numbers[i + 1] - numbers[i] != 1) {
                gap += numbers[i + 1] - numbers[i] - 1;
            }
        }
        return zeroTimes >= gap ? true : false;
    }
}
