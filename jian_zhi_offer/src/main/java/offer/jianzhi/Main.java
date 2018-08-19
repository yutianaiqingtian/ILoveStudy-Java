package offer.jianzhi;

import java.io.StringReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * [Description]
 *
 * @author : jhzhang (you@example.org)
 * @date : 2018-07-23 19:31:06
 */
public class Main {
    private final int M;
    private int[][][] wrongSen;

    Main(int M, int[][][] wrongSen) {
        this.M = M;
        this.wrongSen = wrongSen;
    }

    /**
     * 获得内容并且排序
     *
     * @param line
     * @return
     */
    static int[][] valueOf(String line) {
        String[] items = line.split(";");
        int[][] edits = new int[items.length][2];
        for (int i = 0; i < items.length; i++) {
            String[] strs = items[i].split(",");
            if (strs.length == 2) {
                edits[i][0] = Integer.valueOf(strs[0]);
                edits[i][1] = Integer.valueOf(strs[1]);
            }
        }
        Arrays.sort(edits, (o1, o2) -> (o1[0] - o2[0]));
        return edits;
    }

    public static void main(String[] args) {
        StringReader sr = new StringReader("3\n" +
                "1,10;32,45\n" +
                "78,94;5,16\n" +
                "80,100;200,220;16,32");
        Scanner sc = new Scanner(sr);
//        Scanner sc = new Scanner(System.in);
        // M 个编辑
        int M = Integer.valueOf(sc.nextLine());
        int[][][] pages = new int[M][][];
        try {
            for (int i = 0; i < M; i++) {
                pages[i] = valueOf(sc.nextLine());
            }
        } catch (Exception e) {
            return;
        }
        new Main(M, pages).allSentence();
    }

    private void allSentence() {
        int maxValue = findMax();
        boolean[] wrong = new boolean[maxValue + 1];
        Arrays.fill(wrong, false);
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < wrongSen[i].length; j++) {
                for (int k = wrongSen[i][j][0]; k < wrongSen[i][j][1]; k++) {
                    wrong[k] = true;
                }
            }
        }
        boolean flag = false;
        for (int i = 0; i < maxValue + 1; i++) {
            if (!flag && wrong[i]) {
                flag = true;
                System.out.print(i + ",");
            }
            if (flag && !wrong[i]) {
                flag = false;
                System.out.println(i+";");
            }
        }
    }

    int findMax() {
        int M = Integer.MIN_VALUE;
        for (int i = 0, tmpLen; i < M; i++) {
            tmpLen = wrongSen[i].length;
            if (wrongSen[i][tmpLen - 1][2] > M) {
                M = wrongSen[i][tmpLen - 1][2];
            }
        }
        return M;
    }

    int findMin() {
        int M = Integer.MAX_VALUE;
        for (int i = 0, tmpLen; i < M; i++) {
            tmpLen = wrongSen[i].length;
            if (wrongSen[i][0][1] < M) {
                M = wrongSen[i][0][1];
            }
        }
        return M;
    }
}