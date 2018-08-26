package offer.jianzhi.chapter06;

/**
 * 面试题47：不用加减乘除做加法
 *
 * @author jhZhang
 * @date 2018/8/26
 */
public class Main47 {

    static int Add(int num1, int num2) {
        int xor = num1 ^ num2;
        int and = (num1 & num2) << 1;
        return and != 0 ? Add(xor, and) : xor;
    }

    static int AddByWhile(int a, int b) {
        int xor, and;
        do {
            xor = a ^ b;
            and = (a & b) << 1;

            a = xor;
            b = and;
        } while (and != 0);
        return xor;
    }

    static void swapByAdd(int a, int b) {
        System.out.printf("+ 交换前：%s - %s \t", a, b);
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.printf("+ 交换后：%s - %s \n", a, b);
    }

    static void swapByAnd(int a, int b) {
        System.out.printf("^ 交换前：%s - %s \t", a, b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.printf("^ 交换后：%s - %s \n", a, b);
    }

    public static void main(String[] args) {
        swapByAdd(12, 10);
        swapByAnd(12, 10);
    }
}
