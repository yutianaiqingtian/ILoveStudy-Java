package offer.JD;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author jhZhang
 * @date 2018/9/9
 */
public class Main {

    int N;
    ArrayList<Point> points;

    public Main(int n) {
        N = n;
        this.points = new ArrayList<>();
    }

    public static void main(String[] args) {
        String str = "3\n" +
                "1 4 2\n" +
                "4 3 2\n" +
                "2 5 3";
        StringReader sr = new StringReader(str);

        Scanner sc = new Scanner(sr);

        int N = sc.nextInt();
        Main test = new Main(N);
        for (int i = 0; i < N; i++) {
            test.add(sc.nextInt(), sc.nextInt(), sc.nextInt());
        }

        System.out.println(test.count());
    }

    private int count() {
        if (N <= 1) {
            return 0;
        }
        Collections.sort(this.points, (o1, o2) -> {
            if (o1.a > o2.a) {
                return 1;
            } else if (o1.a == o2.a) {
                return 0;
            }
            return -1;
        });
        int maxBindex = 0;
        int maxBValue = Integer.MIN_VALUE;
        int maxCValue = Integer.MIN_VALUE;
        int maxCindex = 0;

        for (int i = 0; i < N; i++) {
            Point p = points.get(i);
            // 找到最小的 b
            if (p.b > maxBValue) {
                maxBValue = p.b;
                maxBindex = i;
            }
            // 找到最小的c
            if (p.c > maxCValue) {
                maxCValue = p.c;
                maxCindex = i;
            }
        }

        return Math.min(maxBindex, maxCindex);
    }

    void add(int a, int b, int c) {
        points.add(new Point(a, b, c));
    }

    static class Point {
        int a;
        int b;
        int c;

        public Point(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}
