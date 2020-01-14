package offer.jianzhi.chapter05;

/**
 * @author jhZhang
 * @date 2018/8/21
 */
public class Main42_2 {


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
        Main42_2 test = new Main42_2();
        String str = "Wonderful";
        // expect "student. a am I"
        System.out.println(test.ReverseSentence(str));
    }

    public String ReverseSentence(String str) {
        if (str == null || str.length() <= 0) {
            return "";
        }
        char[] chars = str.toCharArray();
        int len = chars.length;
        swapArray(chars, 0, len - 1);
        System.out.println(String.valueOf(chars));

        for (int i = 0, start = 0; i <= len - 1; i++) {
            if (chars[i] == ' ') {
                swapArray(chars, start, i - 1);
                start = i + 1;
            }
            if (i == len - 1) {
                swapArray(chars, start, len - 1);
            }
        }
        return String.valueOf(chars);
    }
}
