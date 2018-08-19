package offer.jianzhi.chapter05;

import java.util.Arrays;

/**
 * @author jhZhang
 * @date 2018/8/19
 */
public class Main40_1 {
    /**
     * 找出数组中只出现一次的两个数字
     *
     * @param num1 长度为1的数组，传出参数
     * @param num2 长度为1的数组，传出参数
     */
    static void FindNumsAppearOnce(int[] array, int[] num1, int[] num2) {
        for (int i = 0; i < array.length; i++) {
            num1[0] ^= array[i];
        }
        // 根据 num1[0] 来将两个数组分为两个不同的小数组
        int diffIndex = findFirstBitIs1(num1[0]);
        int diffValue = 1 << diffIndex;
        num1[0] = 0;
        for (int i = 0; i < array.length; i++) {
            if ((array[i] & diffValue) != diffValue) {
                num1[0] ^= array[i];
            } else {
                num2[0] ^= array[i];
            }
        }
    }

    /**
     * @param num 输入的数字
     * @return 返回数字中二进制表示的第一个1的位置
     */
    static int findFirstBitIs1(int num) {
        int indexBit = 0;
        while ((num & 1) != 1) {
            num = num >> 1;
            indexBit++;
        }
        return indexBit;
    }
}