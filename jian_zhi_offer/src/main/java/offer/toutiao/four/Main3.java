package offer.toutiao.four;

import java.io.StringReader;
import java.util.Scanner;

/**
 * @author jhZhang
 * @date 2018/9/20
 */
public class Main3 {
    int M;
    int N;
    int K;
    String[] strings;
    String[][] dicts;
    // 总的元素查找状态
    boolean[][] totalStatus;

    public Main3(int m, int n, int k, String[] strings, String[][] chars) {
        this.M = m;
        this.N = n;
        this.K = k;
        this.strings = strings;
        this.dicts = chars;
        totalStatus = new boolean[this.N][this.M];
    }

    public static void main(String[] args) {
        String str = "5 5 3\n" +
                "hello help high\n" +
                "p a b h m\n" +
                "f h e c p\n" +
                "o i l l h\n" +
                "b g h o n\n" +
                "h x c m l\n";
        StringReader sr = new StringReader(str);
        Scanner sc = new Scanner(sr);

//        Scanner sc = new Scanner(System.in);

        int M = sc.nextInt();
        int N = sc.nextInt();
        int K = sc.nextInt();
        String[] strings = new String[K];
        for (int i = 0; i < K; i++) {
            strings[i] = sc.next();
        }
        String[][] chars = new String[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                chars[i][j] = sc.next();
            }
        }

        Main3 test = new Main3(M, N, K, strings, chars);
    }

    private void findWords() {
        for (String string : strings) {
            if (findWord(string)) {
                System.out.println(string);
            }
        }
    }

    private boolean findWord(String string) {
        boolean[] strStatus = new boolean[string.length()];
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.M; j++) {
                if (!totalStatus[i][j]) {
                    int index = string.indexOf(dicts[i][j]);
                    if (!strStatus[index] && index != -1) {
                        strStatus[index] = true;
                    }
                }
            }
        }
        return false;
    }

    private boolean findWord(int i, int j, String string, boolean[] strStatus) {
        if (i < 0 || i >= this.N || j < 0 || j > this.M) {
            return false;
        }
        // 如果已经被使用，则退出
        if (totalStatus[i][j]) {
            return false;
        }
        // 如果同一个字符串已经被使用，则退出

        return false;
    }

}
