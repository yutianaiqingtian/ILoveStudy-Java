package offer.jianzhi.expend;

/**
 * 表示数值的字符串
 *
 * @author jhZhang
 * @date 2018/8/30
 */
public class Main54 {
    public static void main(String[] args) {
        Main54 test = new Main54();
        System.out.println(test.isNumeric("12e".toCharArray()));
        System.out.println(test.isNumeric("123.45e+6".toCharArray()));
        System.out.println(test.isNumeric("100".toCharArray()));
        System.out.println(test.isNumeric("3.1416".toCharArray()));
        System.out.println(test.isNumeric("-.123".toCharArray()));
    }

    /**
     * 符合模式串为 [符号]整数数字[小数点及小数点数字部分][e|E [符号]指数数字部分]
     * <p>
     * [sign]integral-digits[.[fractional-digits][e|E[sign]exponential-digits] 其中[]表示可选部分
     * </p>
     *
     * @param str 输入的字符串
     */
    public boolean isNumeric(char[] str) {
        if (str == null || str.length == 0) {
            return false;
        }
        int[] index = new int[1];

        // ① 第一个符位
        if (str[0] == '+' || str[0] == '-') {
            index[0] += 1;
            if (str.length == 1) {
                return false;
            }
        }

        scanDigits(str, index);
        boolean numeric = true;

        if (index[0] != str.length) {
            if (str[index[0]] == '.') {
                index[0] += 1;
                scanDigits(str, index);
                if (index[0] < str.length && (str[index[0]] == 'e' || str[index[0]] == 'E')) {
                    numeric = isExponential(str, index);
                }
            } else if (index[0] < str.length && (str[index[0]] == 'e' || str[index[0]] == 'E')) {
                numeric = isExponential(str, index);
            } else {
                numeric = false;
            }
        }

        return numeric && index[0] == str.length;
    }

    /**
     * @return 扫描数字字母
     */
    void scanDigits(char[] str, int[] i) {
        if (i[0] > str.length - 1) {
            return;
        }
        while (i[0] <= str.length - 1 && str[i[0]] >= '0' && str[i[0]] <= '9') {
            i[0] += 1;
        }
    }

    /**
     * @param str 输入的字符串
     * @param i   表示起始扫描的位置
     * @return 匹配科学计数法的结尾部分
     */
    boolean isExponential(char[] str, int[] i) {
        if (i[0] > str.length - 1 || i[0] < 0) {
            return false;
        }
        if (str[i[0]] != 'E' && str[i[0]] != 'e') {
            return false;
        }
        i[0] += 1;
        if (i[0] == str.length) {
            return false;
        }
        if (str[i[0]] == '+' || str[i[0]] == '-') {
            i[0] += 1;
        }
        if (i[0] > str.length - 1) {
            return false;
        }
        scanDigits(str, i);
        return i[0] == str.length ? true : false;
    }
}
