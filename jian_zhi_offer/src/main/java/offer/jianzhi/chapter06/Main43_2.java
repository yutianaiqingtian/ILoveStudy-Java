package offer.jianzhi.chapter06;

import java.util.Arrays;

/**
 * 解法二：基于循环求骰子点数，时间性能好
 *
 * @author jhZhang
 * @date 2018/8/22
 */
public class Main43_2 {

    static final int g_maxValue = 6;

    static void printProbability(int n, int number) {
        int[] values = new int[n * g_maxValue + 1];
        int[] counts = new int[n * g_maxValue + 1];
        Arrays.fill(values, 0);
        Arrays.fill(counts, 0);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < g_maxValue; j++) {
                int base = g_maxValue * i + j;
                for (int k = 1; k <= g_maxValue; k++) {
                    counts[base + k] += 1;
                }
            }
        }

        int totalTime = g_maxValue;
        while (n-- > 1) {
            totalTime *= g_maxValue;
        }
        System.out.printf("%s/%s", counts[number], totalTime);
    }

    public static void main(String[] args) {
//        printProbability(2, 6);
        PrintProbability(4);

    }

    static void PrintProbability(int number) {
        if (number < 1) {
            return;
        }
        int[][] pProbabilities = new int[2][];
        pProbabilities[0] = new int[g_maxValue * number + 1];
        pProbabilities[1] = new int[g_maxValue * number + 1];
        for (int i = 0; i < g_maxValue * number + 1; ++i) {
            pProbabilities[0][i] = 0;
            pProbabilities[1][i] = 0;
        }

        int flag = 0;
        // 初始化为1的情况
        for (int i = 1; i <= g_maxValue; ++i) {
            pProbabilities[flag][i] = 1;
        }

        for (int k = 2; k <= number; ++k) {
            for (int i = 0; i < k; ++i) {
                pProbabilities[1 - flag][i] = 0;
            }
            for (int i = k; i <= g_maxValue * k; ++i) {
                pProbabilities[1 - flag][i] = 0;
                for (int j = 1; j <= i && j <= g_maxValue; ++j) {
                    pProbabilities[1 - flag][i] += pProbabilities[flag][i - j];
                }
            }
            flag = 1 - flag;
        }

        int total = (int) Math.pow(g_maxValue, number);
        for (int i = number; i <= g_maxValue * number; ++i) {
            System.out.printf("%s/%s\n", pProbabilities[flag][i], total);
        }
    }
}
