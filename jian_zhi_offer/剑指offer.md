[TOC]



---

# 剑指offer

## 笔试题10：二进制中1的个数 

> **题目描述：**输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。 

## 笔试题11：数值的整数次方

>  题目描述：实现函数 double Power(double base,int exponent),求base的exponent次方。不得使用库函数，同时不需要考虑大树问题。

分析：自以为简单的题目，直接累成exponent次就可以啦。代码如下：

```java
double Power(double base,int exponent){
    double result = 1.0;
    for(int i = 0, i <= exponent; i++){
        result *= base;
	}
}
```

很简答，但是没充分的考虑到一些边界条件。如果输入的指数(exponent)为0或者为负数怎么办。考虑情况。

解析：底数是double类型，指数是int类型。分为多种情况。

- 底数为0，则直接返回0，且指数为负数，没有意义。
- 底数为1，则直接返回1。
- 指数为正：常规的解法$a^b=a*a*a...$
- 指数为0：返回为1
- 指数为负数：可以认为是，现将$a^{-b}=(\frac 1a)^b = \frac 1{a^b}$等形式。

上面是考虑的所有特殊情况。代码实现如下：

``` java
    private Double PowerWithUnsignedExponent(Double base, int exponent) {
        if (base == 1) {
            return base;
        }
        if (exponent == 0) {
            return 1.0;
        }
        // 用右移运算符代替除以2
        Double result = PowerWithUnsignedExponent(base, exponent >> 1);
        result *= result;
        // 用位&运算符来判断一个数是否为奇数还是偶数
        if ((exponent & 0x01) != 0) {
            result *= base;
        }
        return result;
    }

    private double Power(Double base, int exponent) throws Exception {
        if (equal(base, 0.0) && exponent < 0) {
            throw new Exception("０的负数次幂无意义");
        }
        if (equal(exponent, 0)) {
            return 1.0;
        }
        double result = 0.0;
        if (exponent < 0) {
            result = PowerWithUnsignedExponent(1.0 / base, -exponent);
        } else {
            result = PowerWithUnsignedExponent(base, exponent);
        }
        return result;
    }
    /* 判断两个double型数据，计算机有误差 */
    private boolean equal(double num1, double num2) {
        return ((num1 - num2 > -0.0000001) && num1 - num2 < 0.0000001);
    }
```

扩展：但是如果，指数也可以为double类型，该如何计算？



## 面试题12：打印1到最大的n位数

> 题目描述：输入数字n，按顺序打印出从1到最大的n位数十进制数。比如输入3，则打印出1、2、3一直到最大的3位数，即999。

粗看起来感觉很简单，感觉没有啥坑。可能会傻傻的写下如下代码：

``` java
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
```

但是，当我们输入的n很大的时候，这样计算肯定会出现溢出，这时就需要去考虑**大树**问题。在字符串上模拟数字加法的解法，绕过陷阱才能拿到offer。

解析：经过前面的分析很容易的想到使用**String**或者**数组**来表示大树。用字符串来表示数字的时候，输入n位数字，我们需要定义n+1长度的字符串（最后一个字符串的结束符号'\0'）。这里我们使用数组char[]来实现。首先，我们把字符串的每一个数字都初始化为'0'，然后每一次为字符串表示的数字+1，再打印出来。我们只需要做两件事：一是**在字符串表达的数字上模拟加法**，二是把字符串表达的数字打印出来。

```java
    void PrintToMaxOfNDigits(int n) {
        if (n < 0) {
            return;
        }
        char[] number = new char[n];
        // 初始化
        for (int i = 0; i < n; i++) {
            number[i] = '0';
        }
        // 模拟累加操作
        while (!Increment(number)) {
            // 打印字符
            PrintNumber(number);
        }
    }
```

对于模拟加法，我们需要知道什么时候开始停止在number上+1。最简单的方法是在每次递增之后，都调用字符串对比函数比较是不是相等。但是显然这种方法是时间复杂度$\Omicron(n)$。下面的方法使用$\Omicron(1)$的效率来判断是否到达循环终止条件。

``` java
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
```

**代码分析**：（有点没有理解过来）

出于人们的阅读习惯，不打印前面为0的字符，所以自定义字符打印函数如下。

``` java
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
```

**把问题转化为数字排列的解法，递归让代码变得更加简介**

这个问题就是有n位，每位从0到9进行**全排列**。全排列用递归算法很容易的表示出来。递归的结束条件就是我们已经设置数字的最后一位。

