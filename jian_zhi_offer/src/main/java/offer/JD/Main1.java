package offer.JD;

import java.io.StringReader;
import java.util.Scanner;

/**
 * @author jhZhang
 * @date 2018/9/9
 */
public class Main1 {
    /**
     * 顶点数
     */
    int N;
    boolean[][] adjs;

    public Main1(int n) {
        N = n;
        this.adjs = new boolean[N][N];
    }

    public static void main(String[] args) {
        String str = "2\n" +
                "5 7\n" +
                "1 3\n" +
                "1 5\n" +
                "2 3\n" +
                "2 5\n" +
                "3 4\n" +
                "4 5\n" +
                "3 5\n" +
                "4 3\n" +
                "1 2\n" +
                "2 3\n" +
                "3 4";
        StringReader sr = new StringReader(str);
        Scanner sc = new Scanner(sr);

//        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            Main1 test = new Main1(N);
            for (int j = 0; j < M; j++) {
                test.addAdj(sc.nextInt(), sc.nextInt());
            }
            System.out.println(test.count());
        }
    }

    public int search() {
        int count = 0;
        boolean[][] status = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (!status[i][j] && !adjs[i][j]) {
                    count++;
                    dfs(i, j, status);
                }
            }
        }
        return count;
    }

    void dfs(int i, int j, boolean[][] status) {
        if (i < 0 || i >= N || j <= i || j >= N) {
            return;
        }
        if (!status[i][j] && !adjs[i][j]) {
            status[i][j] = true;
            dfs(i - 1, j, status);
            dfs(i + 1, j, status);
            dfs(i, j - 1, status);
            dfs(i, j + 1, status);
        }
    }

    private String count() {
        return search() <= 1 ? "No" : "Yes";
    }

    public void addAdj(int i, int j) {
        this.adjs[i - 1][j - 1] = true;
    }
}
