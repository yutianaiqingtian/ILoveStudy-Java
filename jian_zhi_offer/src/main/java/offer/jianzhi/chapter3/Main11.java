package offer.jianzhi.chapter3;

/**
 * @author jhZhang
 * @date 2018/5/9
 */
class Main11 {
    /**
     * 输入的exponnet认为是无符号的数字
     *
     * @param base
     * @param exponent
     * @return
     */
    private Double PowerWithUnsignedExponent(Double base, int exponent) {
        if (base == 1) {
            return base;
        }
        if (exponent == 0) {
            return 1.0;
        }
        // 用右移运算符代替除以2
        Double result = PowerWithUnsignedExponent(base, exponent >> 1);
        result *= result;
        // 用位&运算符来判断一个数是否为奇数还是偶数
        if ((exponent & 0x01) != 0) {
            result *= base;
        }
        return result;
    }

    private double Power(Double base, int exponent) throws Exception {
        if (equal(base, 0.0) && exponent < 0) {
            throw new Exception("０的负数次幂无意义");
        }
        if (equal(exponent, 0)) {
            return 1.0;
        }
        double result = 0.0;
        if (exponent < 0) {
            result = PowerWithUnsignedExponent(1.0 / base, -exponent);
        } else {
            result = PowerWithUnsignedExponent(base, exponent);
        }
        return result;
    }


    public static void main(String[] args) throws Exception {
        Double base = -3.0;
        int exponent = -3;
        System.out.println(Math.pow(base, exponent));
        System.out.println(new Main11().Power(base, exponent));
    }

    /* 判断两个double型数据，计算机有误差 */
    private boolean equal(double num1, double num2) {
        return ((num1 - num2 > -0.0000001) && num1 - num2 < 0.0000001);
    }
}
