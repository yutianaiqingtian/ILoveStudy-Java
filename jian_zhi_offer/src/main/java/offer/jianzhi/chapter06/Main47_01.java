package offer.jianzhi.chapter06;

import java.io.StringReader;
import java.util.Scanner;

/**
 * 把字符串转换成整数
 *
 * @author jhZhang
 * @date 2018/8/26
 */
public class Main47_01 {
    static int StrToInt(String str) {
        if (str == null || str.length() <= 0) {
            return 0;
        }
        int sum = 0;
        char[] chars = str.trim().toCharArray();
        int n = chars.length - 1;
        int times = 1;
        while (n >= 0 && chars[n] >= '0' && chars[n] <= '9') {
            sum += times * (chars[n] - '0');
            times *= 10;
            n--;
        }
        if (n == -1) {
            return sum;
        } else if (n == 0 && chars[0] == '+') {
            return sum;
        } else if (n == 0 && chars[0] == '-') {
            return -sum;
        }
        return 0;
    }

    public static void main(String[] args) {
        String str = "123\n+2147483647\n-2147483647\n" +
                "    1a33";
        StringReader sr = new StringReader(str);
        Scanner sc = new Scanner(sr);
        while (sc.hasNext()) {
            System.out.println(StrToInt(sc.next()));
        }
    }
}
