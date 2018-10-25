package offer.jianzhi.chapter2;

/**
 * @author jhZhang
 * @date 2018/5/17
 */
public class Main9 {
    public int Fibonacci(int n) {
        if (n <= 0) {
            return 0;
        }
        int a = 0;
        int b = 1;
        while ((--n) > 0) {
            b = a + b;
            a = b - a;
        }
        return b;
    }
}
