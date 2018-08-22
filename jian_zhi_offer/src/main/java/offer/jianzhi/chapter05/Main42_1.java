package offer.jianzhi.chapter05;

/**
 * @author jhZhang
 * @date 2018/8/19
 */
public class Main42_1 {


    static void swapArray(char[] chars, int start, int end) {
        if (start < 0 || end > chars.length || end < start) {
            return;
        }
        int count = (end - start) >> 1;
        for (int i = 0; i <= count; i++) {
            char tmp = chars[start + i];
            chars[start + i] = chars[end - i];
            chars[end - i] = tmp;
        }
    }

    public static void main(String[] args) {
        Main42_1 test = new Main42_1();
        System.out.println(test.LeftRotateString("abcdefg", 2));
    }

    /**
     * 左旋转字符串
     *3
     * @param str 原始字符串
     * @param n   旋转的位置
     * @return
     */
    public String LeftRotateString(String str, int n) {
        if (str == null || str.length() <= 0 || n > str.length()) {
            return "";
        }
        char[] chars = str.toCharArray();
        int len = chars.length;
        swapArray(chars, 0, len - 1);
        swapArray(chars, 0, len - n - 1);
        swapArray(chars, len - n, len - 1);
        return String.valueOf(chars);
    }
}
