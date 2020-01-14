package offer.jianzhi.chapter4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * @author jhZhang
 * @date 2018/6/17
 */
public class Main24Combine {
    public static void main(String[] args) {
        String testStr = "1234";
        new Main24Combine().combine(testStr);
    }

    public ArrayList<String> combine(String str) {
        ArrayList<String> result = new ArrayList<String>();
        if (str == null && str.isEmpty()) {
            return result;
        }
        // 组合  C_n^1+C_n^2 + …… + C_n^n
        for (int i = 1; i <= str.length(); i++) {
            combine(str.toCharArray(), new HashSet(i), i, result);
        }
        Collections.sort(result);
        return result;
    }

    /**
     * 计算 C_n^m 的组合次数
     *
     * @param pres   选中的元素
     * @param m      选中的个数
     * @param result 返回的结果
     */
    private void combine(char[] chars, HashSet pres, int m, ArrayList<String> result) {
        if (pres.size() == m) {
// 将容器转换为字符串
            String stack = String.valueOf(pres);
// 去除重复元素
            if (!result.contains(stack)) {
                System.out.println(stack);
                result.add(stack);
            }
            return;
        }
        for (int i = 0, len = chars.length; i < len; i++) {
            swap(chars, 0, i);
            char[] newChars = new char[len - 1];
// 复制未选中的剩余元素
            System.arraycopy(chars, 1, newChars, 0, len - 1);
            pres.add(chars[0]);
            combine(newChars, pres, m, result);
// 回退一格选中的元素
            pres.remove(chars[0]);
            swap(chars, 0, i);
        }
    }

    public void swap(char[] chars, int i, int j) {
        char tmp = chars[j];
        chars[j] = chars[i];
        chars[i] = tmp;
    }
}
