package edu.alogirithms.chapter04;

import java.io.StringReader;
import java.util.Scanner;

/**
 * @author jhZhang
 * @date 2018/9/8
 */
public class Main {
    public static void main(String[] args) {
        String str = "bwbwb";
        StringReader sr = new StringReader(str);
        Scanner sc = new Scanner(sr);
        String line = sc.nextLine();

        if (line == null || line.length() <= 0) {
            System.out.println(0);
        }
        int W = 0;
        int B = 0;
        for (char c : line.toCharArray()) {
            if (c == 'w') {
                W++;
            } else {
                B++;
            }
        }
        System.out.println(Math.min(W, B) * 2 + 1);
    }
}
