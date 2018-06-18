package offer.jianzhi.chapter4;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author jhZhang
 * @date 2018/6/17
 */
public class Main24Combine {
    public ArrayList<String> combine(String str) {
        ArrayList<String> result = new ArrayList<String>();
        if (str == null && str.isEmpty()) {
            return result;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= str.length(); i++) {
            combine(str.toCharArray(), sb, str.length(), i, result);
            sb.setLength(0);
        }
        Collections.sort(result);
        return result;
    }

    /**
     * @param m
     * @param result
     */
    private void combine(char[] chars, StringBuilder stacks, int n, int m, ArrayList<String> result) {
        if (stacks.length() == m) {
            String stack = String.valueOf(stacks);
            if (!result.contains(stack)) {
                System.out.println(stack);
                result.add(stack);
            }
            return;
        }
        // 第一种 m*C_{n}^{m-1}
        for (int i = 0; i < chars.length; i++) {
            swap(chars, 0, n - 1);
            char[] newChars = new char[n - 1];
            System.arraycopy(chars, 0, newChars, 0, n - 1);
            if (stacks.indexOf(String.valueOf(chars[i])) != -1) {
                combine(newChars, stacks, n - 1, m, result);
            } else {
                // 第二种 (n-m)*C_{n-1}^m
                stacks.append(chars[i]);
                combine(newChars, stacks, n - 1, m, result);
                // 回退一个
                stacks.setLength(stacks.length() - 1);
            }
        }
    }


    public void swap(char[] chars, int i, int j) {
        char tmp = chars[j];
        chars[j] = chars[i];
        chars[i] = tmp;
    }

    public static void main(String[] args) {
        String testStr = "abcd";
        new Main24Combine().combine(testStr);
    }
}
