package offer.jianzhi.chapter06;

/**
 * 约瑟夫环 采用循环解法
 *
 * @author jhZhang
 * @date 2018/8/26
 */
public class Main45_2 {
    static int LastRemaining_Solution(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }
        int last = 0;
        for (int i = 2; i <= n; i++) {
            last = (last + m) % i;
        }
        return last;
    }
}
