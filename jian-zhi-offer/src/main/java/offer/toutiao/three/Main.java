package offer.toutiao.three;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author jhZhang
 * @date 2018/9/9
 */
public class Main {
    /**
     * 用户数
     */
    int N;
    /**
     * 关系数
     */
    int M;
    LinkedList<Integer>[] adjs;
    int[] degree;

    public Main(int n, int m) {
        N = n;
        M = m;
        adjs = new LinkedList[N];
        for (int i = 0; i < N; i++) {
            adjs[i] = new LinkedList();
        }
        degree = new int[N];
    }

    public static void main(String[] args) {
        String str = "4 3\n" +
                "2 1\n" +
                "3 2\n" +
                "4 3";
        StringReader sr = new StringReader(str);
        Scanner sc = new Scanner(sr);

        int N = sc.nextInt();
        int M = sc.nextInt();
        Main test1 = new Main(N, M);
        Main test2 = new Main(N, M);
        int result = 0;
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            test1.addAdj(a, b);
            test2.addAdj(b, a);
        }
        test1.getAllDegee();
        test2.getAllDegee();
        for (int i = 0; i < N; i++) {
            if (test2.degree[i] > test1.degree[i]) {
                result++;
            }
        }
        System.out.println(result);
    }


    /**
     * 查找某个点的入度
     *
     * @param status
     * @param i
     */
    void search(boolean[] status, int i) {
        for (Integer k : adjs[i]) {
            if (!status[k]) {
                status[k] = true;
                search(status, k);
            }
        }
    }

    /**
     * 查找某个点的suo
     *
     * @param i
     */
    boolean search(int i) {
        boolean[] status = new boolean[N];
        status[i] = true;
        search(status, i);
        int count = 0;
        for (boolean b : status) {
            if (b) {
                count++;
            }
        }
        degree[i] = count != 0 ? count - 1 : 0;
        return true;
    }

    int[] getAllDegee() {
        for (int i = 0; i < N; i++) {
            search(i);
        }
        return degree;
    }

    /**
     * 增加有向边（重换自环路）
     *
     * @param i
     * @param j
     */
    void addAdj(int i, int j) {
        if (i <= 0 || i > N || j <= 0 || j > N) {
            return;
        }
        // 去除自环
        if (i == j) {
            return;
        }
        // 去除重环
        for (Integer obj : adjs[i - 1]) {
            if (obj.equals(j - 1)) {
                return;
            }
        }
        adjs[i - 1].add(j - 1);
    }
}
