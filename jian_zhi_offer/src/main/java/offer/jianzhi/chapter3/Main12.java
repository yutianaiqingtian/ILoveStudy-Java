package offer.jianzhi.chapter3;

/**
 * @author jhZhang
 * @date 2018/5/9
 */
class Main12 {
/**
 * 未考虑完全的解法
 *
 * @param n
 */
void PrintToMaxOfNDigits_1(int n) {
int number = 1;
int i = 0;
// 找出最大值
while (i++ < n) {
number *= 10;
}
// 开始打印
for (int j = 0; j < number; j++) {
System.out.printf("%d、", j);
}
}

/**
 * 模拟整数加法的解法
 *
 * @param n
 */
void PrintToMaxOfNDigits(int n) {
if (n < 0) {
return;
}
char[] number = new char[n];
// 初始化
for (int i = 0; i < n; i++) {
number[i] = '0';
}
while (!Increment(number)) {
PrintNumber(number);
}
}

void PrintNumber(char[] number) {
boolean isBeginning0 = true;
for (int i = 0, len = number.length; i < len; ++i) {
if (isBeginning0 && number[i] != '0') {
isBeginning0 = false;
}
if (!isBeginning0) {
System.out.printf("%c", number[i]);
}
}
System.out.printf("\t");
}


boolean Increment(char[] nums) {
// 是否终止
boolean isOverflow = false;
// 进位
int nTakeOver = 0;
for (int len = nums.length, i = len - 1; i >= 0; i--) {
int nSum = nums[i] - '0' + nTakeOver;
if (i == len - 1) {
nSum++;
}
if (nSum >= 10) {
if (i == 0) {
isOverflow = true;
} else {
nSum -= 10;
nTakeOver = 1;
nums[i] = (char) ('0' + nSum);
}
} else {
nums[i] = (char) ('0' + nSum);
break;
}
}
return isOverflow;
}

public static void main(String[] args) {
new Main12().PrintToMaxOfNDigits(2);
}
}
