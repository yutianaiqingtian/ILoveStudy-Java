package edu.alogirithms.chapter04;

import java.io.StringReader;
import java.util.Scanner;

/**
 * @author jhZhang
 * @date 2018/9/8
 */
public class Main3 {
    public static void main(String[] args) {
        String str = "5 5\n" +
                "2 5\n" +
                "3 5\n" +
                "4 5\n" +
                "5 6\n" +
                "5 1";
        StringReader sr = new StringReader(str);
        Scanner sc = new Scanner(sr);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] weight = new int[n][2];
        for (int i = 0; i < n; i++) {
            weight[i][0] = sc.nextInt();
            weight[i][1] = sc.nextInt();
        }
    }
}
