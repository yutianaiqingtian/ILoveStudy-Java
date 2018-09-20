package edu.string.single;

import java.util.Arrays;

/**
 * KMP 算法的JAVA实现
 *
 * @author jhZhang
 * @date 2018/9/16
 */
public class KMP {
    /**
     * @param str  文本串
     * @param dest 模式串
     * @return
     */
    public static int kmp(String str, String dest) {
        int[] next = kmpnext(dest);
        for (int i = 0, j = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (str.charAt(i) == dest.charAt(j)) {
                j++;
            }
            if (j == dest.length()) {
                return i - j + 1;
            }
        }
        return 0;
    }

    /**
     * 获得目标字符串的next数组
     */
    public static int[] kmpnext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(j) != dest.charAt(i)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    public static void main(String[] args) {
        String a = "ababc";
        String b = "abcdefg";
        System.out.println(Arrays.toString(kmpnext(a)));
        System.out.println(Arrays.toString(kmpnext(b)));
    }
}