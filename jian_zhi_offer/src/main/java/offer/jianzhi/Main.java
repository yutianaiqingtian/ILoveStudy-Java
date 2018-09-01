package offer.jianzhi;

import java.io.StringReader;
import java.util.Scanner;

/**
 * [Description]
 *
 * @author : jhzhang (you@example.org)
 * @date : 2018-07-23 19:31:06
 */
public class Main {
    public static void main(String[] args) {
        String str = "5\n" +
                "2 3 3 3";
        StringReader sr = new StringReader(str);
        Scanner sc = new Scanner(sr);
        int k = sc.nextInt();
        int A = sc.nextInt();
        int x = sc.nextInt();
        int B = sc.nextInt();
        int y = sc.nextInt();

        System.out.println(getValue(k, A, x, B, y));
    }

    private static int getValue(int k, int a, int x, int b, int y) {
        int count = 0;
        if (k < a || k < b || x < 0 || y < 0) {
            return 0;
        }
        if (k == a) {
            return y;
        } else if (k == b) {
            return x;
        }
        for (int i = 0; i <= x; i++) {
            count += getValue(k - a, a, x - 1, b, y);
        }
        for (int i = 0; i <= y; i++) {
            count += getValue(k - b, a, x, b, y - 1);
        }
        return count;
    }
}