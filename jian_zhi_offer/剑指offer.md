[TOC]



---

# 剑指offer

### Main5：从尾到头打印链表

---

2018/5/16 星期三

> **题目描述**：输入个链表的头结点，从尾到头反过来打印出每个结点的值。 

设计一个链表结点

```java
    class ListNode<T> {
        T val;
        ListNode next = null;

        ListNode(T val) {
            this.val = val;
        }
    }
```

利用堆栈可以很方便的实现这个目的

```java
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        ListNode current = listNode;
        while (current != null) {
            stack.push(current.val);
            current = current.next;
        }
        ArrayList<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }
```



### Main6 题目：重建二叉树

> 题目描述：输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如：前序遍历序列｛ 1, 2, 4, 7, 3, 5, 6, 8｝和中序遍历序列｛4, 7, 2, 1, 5, 3,  8，6}，重建出下图所示的二叉树并输出它的头结点。 

构建二叉树的结点类

```java
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
```

构建二叉树

```java
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    private TreeNode reConstructBinaryTree(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        for (int i = inStart; i <= inEnd; i++) {
            if (root.val == in[i]) {
                root.left = reConstructBinaryTree(pre, preStart + 1, preStart + i - inStart, in, inStart, i - 1);
                root.right = reConstructBinaryTree(pre, preStart + 1 + i - inStart, preEnd, in, i + 1, inEnd);
                break;
            }
        }
        return root;
    }
```







### 笔试题10：二进制中1的个数 

> **题目描述：**输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。 

### 笔试题11：数值的整数次方

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

```java
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



### 面试题12：打印1到最大的n位数

> 题目描述：输入数字n，按顺序打印出从1到最大的n位数十进制数。比如输入3，则打印出1、2、3一直到最大的3位数，即999。

粗看起来感觉很简单，感觉没有啥坑。可能会傻傻的写下如下代码：

```java
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

```java
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

```java
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


### 面试题38：数字在排序数组中出现的次数

[牛客网链接](https://www.nowcoder.com/practice/70610bf967994b22bb1c26f9ae901fa2?tpId=13&tqId=11190&tPage=2&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

> 统计一个数字在排序数组中出现的次数。


一开始没有看到**有序的数组中**找出某个数字的统计，写出来如下利用hashMap的解法。

```java
        HashMap<Integer, Integer> maps = new HashMap<>();
        for (int i : array) {
            if (maps.get(i) == null) {
                maps.put(i, 1);
            } else {
                maps.put(i, maps.get(i) + 1);
            }
        }
        return maps.get(k) == null ? 0 : maps.get(k);
```

追求更快的算法，则应该是使用二分法是比较快速的方法。整体步骤如下

1. 二分查找算法总是先拿数组中间的数字和k作比较。
2. 如果中间的数字比k大，那么k只有可能出现在数组的前半段，下一轮我们只在数组的前半段查找就可以了。
3. 如果中间的数字比k小，那么k只有可能出现在数组的后半段，下一轮我们只在数组的后半段查找就可以了。
4. 如果中间的数字和k相等呢？我们先判断这个数字是不是第一个k。如果位于中间数字的前面一个数字不是k，此时中间的数字刚好就是第一个k。如果中间数字的前面一个数字也是k，也就是说第一个k肯定在数组的前半段，下一轮我们仍然需要在数组的前半段查找。

```java

    /**
     * @return 获得数组中的第一个 k 元素
     */
    int getFirstNumberOfK(int[] array, int left, int right, int k) {
        if (left > right) {
            return -1;
        }
        int midIndex = (left + right) >> 1;
        int midValue = array[midIndex];
        if (midValue == k) {
            if (midIndex > 0 && array[midIndex - 1] != k || midIndex == 0) {
                return midIndex;
            } else {
                right = midIndex - 1;
            }
        } else if (midValue < k) {
            left = midValue - 1;
        } else {
            right = midValue + 1;
        }
        return getFirstNumberOfK(array, left, right, k);
    }

    /**
     * @return 获得数组中的最后一个 k 元素
     */
    int getLastNumberOfK(int[] array, int left, int right, int k) {
        if (left > right) {
            return -1;
        }
        int midIndex = (left + right) >> 1;
        int midValue = array[midIndex];
        if (midValue == k) {
            if (midIndex < array.length - 1 && array[midIndex + 1] != k || midIndex == array.length - 1) {
                return midIndex;
            } else {
                left = midIndex + 1;
            }
        } else if (midValue < k) {
            left = midValue - 1;
        } else {
            right = midValue + 1;
        }
        return getLastNumberOfK(array, left, right, k);
    }

    public int GetNumberOfK(int[] array, int k) {
        int startIndex = getFirstNumberOfK(array, 0, array.length, k);
        int endIndex = getLastNumberOfK(array, 0, array.length, k);
        return endIndex >= startIndex ? endIndex - startIndex + 1 : 0;
    }
```	

该算法所需要的空间效率为：

> 运行时间：11ms  占用内存：9308k

### 面试题39-1：二叉树的深度

[牛客网链接](https://www.nowcoder.com/practice/435fb86331474282a3499955f0a41e8b?tpId=13&tqId=11191&tPage=2&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

#### 题目描述
>输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。

解题思路如下：

1. 树的深度为左右子树中最大值 +1;

```java
    public int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int nLeft = TreeDepth(root.left);
        int nRight = TreeDepth(root.right);
        return nLeft > nRight ? nLeft + 1 : nRight + 1;
    }
```

运行效率：

> 运行时间：12ms </br>
占用内存：9408k

### 面试题39-2：平衡二叉树

[牛客网链接](https://www.nowcoder.com/practice/8b3b95850edb4115918ecebdf1b4d222?tpId=13&tqId=11192&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

> 题目二：输入一棵二叉树的根结点，判断该树是不是平衡二叉树。如果某二叉树中任意结点的**左右子树的深度相差不超过1**，那么它就是一棵平衡二叉树。

思路一： 基于上面的数的深度，可以考虑出如下的代码。

```java
    public int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int nLeft = TreeDepth(root.left);
        int nRight = TreeDepth(root.right);
        return nLeft > nRight ? nLeft + 1 : nRight + 1;
    }

    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        int nLeft = TreeDepth(root.left);
        int nRight = TreeDepth(root.right);
        if (Math.abs(nLeft - nRight) > 1) {
            return false;
        }
        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
    }
```

上面代码由于应用了迭代，每次计算某个结点的深度，都遍历了该结点下的所有子结点，所以存在遍历结点冗余的情况。**简单大不足以打动关注**。

代码运行效率如下：

> 运行时间：19ms </br>
占用内存：9300k

思路二： 每个结点只遍历一次的解法

如果我们用**后序遍历**的方式遍历二叉树的每一个结点，在遍历到一个结点之前我们就已经遍历了它的左右子树。只要在遍历每个结点的时候记录它的深度（某一结点的深度等于它到叶节点的路径的长度），我们就可以一边遍历一边判断每个结点是不是平衡的。

```java
public class Solution {
    public boolean IsBalanced_Solution(TreeNode root) {
        return getDepth(root) != -1;
    }

    private int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getDepth(root.left);
        if (left == -1) {
            return -1;
        }
        int right = getDepth(root.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left, right);
    }
}
```

代码的运行效率：

> 运行时间：18ms </br>
占用内存：9412k


### 面试题40：数组中只出现一次的数字

[牛客网链接](https://www.nowcoder.com/practice/e02fdb54d7524710a7d664d082bb7811?tpId=13&tqId=11193&tPage=2&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

> 题目：一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O（n），空间复杂度是O（1）。

例如输入数组{2,4,3,6,3,2,5,5}，因为只有4、6这两个数字只出现一次，其他数字都出现了两次，所以输出4和6。

Tips: ：你可以先考虑这个数组中只有一个数字只出现一次，其他的都出现了两次，怎么找出这个数字？

这两个题目都在强调一个（或两个）数字只出现一次，其他的出现两次。这有什么意义呢？我们想到**异或运算**的一个性质：**任何一个数字异或它自己都等于0**。也就是说，如果我们从头到尾依次异或数组中的每一个数字，那么最终的结果刚好是那个只出现一次的数字，因为那些成对出现两次的数字全部在异或中抵消了。

**只有一个数字只出现一次的解法**

```java
    /**
     * 找出数组中只出现一次的数字,num1,num2分别为长度为1的数组。传出参数
     * @param 
     * @param num1
     */
    static void FindNumsAppearOnce(int[] array, int[] num1, int[] num2) {
        for (int i = 0; i < array.length; i++) {
            num1[0] ^= array[i];
        }
        // 根据 num1[0] 来将两个数组分为两个不同的小数组
        int diffIndex = findFirstBitIs1(num1[0]);
        int diffValue = 1 << diffIndex;
        num1[0] = 0;
        for (int i = 0; i < array.length; i++) {
            if ((array[i] & diffValue) != diffValue) {
                num1[0] ^= array[i];
            } else {
                num2[0] ^= array[i];
            }
        }
    }
    /**
     * @param num 输入的数字
     * @return 返回数字中二进制表示的第一个1的位置
     */
    static int findFirstBitIs1(int num) {
        int indexBit = 0;
        while ((num & 1) != 1) {
            num = num >> 1;
            indexBit++;
        }
        return indexBit;
    }
```

程序耗时

> 运行时间：13ms </br> 占用内存：9660k