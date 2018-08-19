package offer.jianzhi.chapter4;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 字符串的排列与组合
 * <p>一个很经典的问题，就是给你，a，b，c是三个字符串，输出，它所有可能的排列，比如，abc，acb，bac等。
 * 根据排列组合我们知道，总的排列个数是 C_3^3 = 6，如何用编程来实现打印所有字符的需求呢？21 </p>
 *
 * @author jhZhang
 * @date 2018/6/15
 */
public class Main24 {
/**
 * 把字符串拆分成两部分，第一部分不变，第二部分开始进行交换
 *
 * @param str
 */
public ArrayList<String> Permutation(String str) {
ArrayList<String> result = new ArrayList<>();
if (str == null || str.length() <= 0) {
return result;
}
char[] chars = str.toCharArray();
Premutation(chars, 0, result);
Collections.sort(result);
return result;
}

private void Premutation(char[] chars, int offset, ArrayList<String> result) {
int len = chars.length;
if (offset < 0 || offset > len) {
return;
}
if (offset == len) {
String str = String.valueOf(chars);
if (!result.contains(str)) {
result.add(String.valueOf(chars));
}
}
for (int i = offset; i < len; i++) {
swap(chars, offset, i);
// 保存第offset元素，进行交换
Premutation(chars, offset + 1, result);
swap(chars, offset, i);
}

}

public void swap(char[] chars, int i, int j) {
char tmp = chars[j];
chars[j] = chars[i];
chars[i] = tmp;
}

public static void main(String[] args) {
String str = "abc";
new Main24().Permutation(str);
}

}
