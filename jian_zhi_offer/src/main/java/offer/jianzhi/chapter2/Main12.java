package offer.jianzhi.chapter2;

/**
 * @author jhZhang
 * @date 2018/5/18
 */
public class Main12 {

//从n的2进制形式的最右边开始判断是不是1
/*
 * 该解法如果输入时负数会陷入死循环，
 * 因为负数右移时，在最高位补得是1
 * 二本题最终目的是求1的个数，那么会有无数个
 * 1了。
 */
//-------------可能陷入死循环的解法---------------------
public int NumberOf1_CanNotUse(int n) {
int count = 0;
while (n != 0) {
/*
 * 用1和n进行位与运算，
 * 结果要是为1则n的2进制形式
 * 最右边那位肯定是1，否则为0
 */
if ((n & 1) == 1) {
count++;
}
//把n的2进制形式往右推一位
n = n >> 1;
}
return count;
}

//---------------正解--------------------------------
//思想：用1（1自身左移运算，其实后来就不是1了）和n的每位进行位与，来判断1的个数
private int NumberOf1_low(int n) {
int count = 0;
int flag = 1;
while (flag != 0) {
if ((n & flag) != 0) {
count++;
}
flag = flag << 1;
}
return count;
}

//--------------------最优解----------------------------
public int NumberOf1(int n) {
int count = 0;
while (n != 0) {
++count;
n = (n - 1) & n;
}
return count;
}
}
