package offer.jianzhi.chapter3;

import java.util.Arrays;

/**
 * 输入一个整数数组，实现一个函数来调整数组中的数字的顺序，使得所有奇数位于数组的前半部分，偶数位于后半部分
 *
 * @author jhZhang
 * @date 2018/5/14
 */
public class Main14 {
public static void main(String[] args) {
int[] array = {1, 2, 3, 4, 5};
//int[] array = {1};
Main14 test = new Main14();
test.ReorderOddEven(array);
// 结果应该为 [1, 5, 3, 4, 2]
System.out.println(Arrays.toString(array));
}


public void ReorderOddEven(int[] array) {
if (array == null || array.length == 0) {
return;
}
int pStart = 0;
int pEnd = array.length - 1;
while (pEnd > pStart) {
// 向后移动，直到它指向偶数
while (!isEven(array[pStart])) {
pStart++;
}
// 向前移动，直到它指向奇数
while (isEven(array[pEnd])) {
pEnd--;
}
if (pEnd > pStart) {
// 交换
swap(array, pStart, pEnd);
}
}
}

public void swap(int[] array, int i, int j) {
int tmp = array[i];
array[i] = array[j];
array[j] = tmp;
}

private boolean isEven(int n) {
// return n % 2 == 0;
return (n & 0x1) == 0;
}
}
