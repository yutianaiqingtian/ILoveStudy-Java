package offer.jianzhi.chapter06;

/**
 * @author jhZhang
 * @date 2018/8/22
 */
public class Main43_1 {

    /**
     * 最大的点数
     */
    static final int gMaxValue = 6;

    /**
     * @param n         循环的次数
     * @param preSumsum 前一个值
     * @param s         预期值
     * @param times     传递次数
     */
    static void sumCountNumber(int n, int preSumsum, int s, int[] times) {
        if (n == 0 && preSumsum == s) {
            times[0] += 1;
            return;
        } else if (n <= 0) {
            return;
        }
        for (int i = 1; i <= gMaxValue; i++) {
            sumCountNumber(n - 1, preSumsum + i, s, times);
        }
    }

    static String properties(int n, int s) {
        double prop = 0.0;
        int min = n * 1;
        int max = n * gMaxValue;
        if (n <= 0 || s < min || s > max) {
            return "0/0";
        }
        int j = n;
        int maxTimes = gMaxValue;
        while (j-- > 1) {
            maxTimes *= gMaxValue;
        }
        int[] times = new int[]{0};
        sumCountNumber(n, 0, s, times);
        return String.format("%s/%s", times[0], maxTimes);
    }

}
