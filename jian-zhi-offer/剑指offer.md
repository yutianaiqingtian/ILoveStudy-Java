[TOC]

---

### Main5：从尾到头打印链表

---

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
public class offer.alibaba.Main2019_01 {
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

### 面试题41：和为s的两个数字VS和为s的连续正数序列

[牛客网连接](https://www.nowcoder.com/practice/390da4f7a00f44bea7c2f3d19491311b?tpId=13&tqId=11195&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

> 题目一：输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，输出任意一对即可。

例如输入数组{1、2、4、7、11、15}和数字15。由于4＋11＝15，因此输出4和11。

解题思路：

1. 首先定义两个指针，第一个指针指向数组的第一个（也是最小的）数字1，第二个指针指向数组的最后一个（也是最大的）数字15。
2. 这两个数字的和16大于15，因此我们把第二个指针向前移动一个数字，让它指向11。
3. 这个时候两个数字1与11的和是12，小于15。我们把第一个指针向后移动一个数字指向2。
4. 此时两个数字2与11的和13，还是小于15。我们再一次向后移动第一个指针，让它指向数字4。数字4、11的和是15，正是我们期待的结果

![Main41_1 查找两个数字和为15的过程](剑指offer.image/Main41_1 查找两个数字和为15的过程.png)

基于上面思路编写的Java代码

```java
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList list = new ArrayList();
        if (array == null || array.length <= 2) {
            return list;
        }
        int start = 0;
        int end = array.length - 1;
        while (start < end) {
            int tmp = array[start] + array[end];
            if (tmp == sum) {
                list.add(array[start]);
                list.add(array[end]);
                break;
            } else if (tmp < sum) {
                start++;
            } else {
                end--;
            }
        }
        return list;
    }
```


> 题目二： 和为s的连续正数序列

[牛客网链接](https://www.nowcoder.com/practice/c451a3fd84b64cb19485dad758a55ebe?tpId=13&tqId=11194&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

> 题目二：输入一个正数s，打印出所有和为s的连续正数序列（至少含有两个数）。例如输入15，由于1＋2＋3＋4＋5＝4＋5＋6＝7＋8＝15，所以结果打印出3个连续序列1～5、4～6和7～8。


解题思路：参考前面的方法。

1. 我们考虑用两个数 small 和 big
分别表示序列的最小值和最大值。首先把 small 初始化为1, big 初始化为2；
2. 当我们发现 small ~ big 之和小于总数 sum 的时候，我们将 end 向后进行累加操作，并向后移动；
3. 当我们发现 small ~ big 之和大于 sum 总数的时候，我们将 small 剔除出连续子序列中，并向后移动。

我们以 sum = 9 为例说明。整个过程如下图。

![Main41_2 求取和为9的连续序列的过程](剑指offer.image/Main41_2 求取和为9的连续序列的过程.png)

参考上面思路写下的Java代码：（可优化）

```java
    static ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList();
        int small = 1;
        int big = 2;
        int tmp = small + big;
        while (big < sum) {
            if (tmp == sum) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = small; i <= big; i++) {
                    list.add(i);
                }
                result.add(list);
                ++big;
                tmp += big;
            } else if (tmp > sum) {
                tmp -= small;
                ++small;
            } else {
                ++big;
                tmp += big;
            }
        }
        return result;
    }
```

程序耗时

> 运行时间：14ms </br> 占用内存：9552k


### 面试题42：翻转单词顺序 VS 左旋转字符串


[牛客网链接](https://www.nowcoder.com/practice/3194a4f4cf814f63919d0790578d51f3?tpId=13&tqId=11197&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

> 题目一：输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。

解题思路：

1. 先将整个句子都反转;
2. 将每个单词反转。

下面是将输入字符串反转的。

解题思路：

先定义个函数，用来实现字符串的反转。

```java
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

```

下面的方法通过判断为**空格**或者**最后一个元素**来反转每个单词

```java
    public String ReverseSentence(String str) {
        if (str == null || str.length() <= 0) {
            return "";
        }
        char[] chars = str.toCharArray();
        int len = chars.length;
        swapArray(chars, 0, len - 1);

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
```

运行效率：

> 运行时间：19ms </br> 占用内存：9572k

#### 左旋转字符串

> 题目二：字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如输入字符串"abcdefg"和数字2，该函数将返回左旋转2位得到的结果"cdefgab"。

[牛客网链接](https://www.nowcoder.com/practice/12d959b108cb42b1ab72cef4d36af5ec?tpId=13&tqId=11196&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

同样的基于上面的思路，采用旋转来考虑。

```java
    /**
     * 左旋转字符串
     *
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
```

思考：当然其实前面两个字符移动到字符串的末尾，通过其它的方式也可以做，但是通过旋转，就不需要额外的空间开销

运行效率:

> 运行时间：21ms </br> 占用内存：9680k

### 面试题43：n个骰子的点数

> 题目：把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。

玩过麻将的人都知道，骰子一共6个面，每个面上都有一个点数，对应的是1～6之间的一个数字。所以n个骰子的点数和的最小值为n，最大值为6n。另外根据排列组合的知识，我们还知道n个骰子的所有点数的排列数为6n。要解决这个问题，我们需要先统计出每一个点数出现的次数，然后把每一个点数出现的次数除以6n，就能求出每个点数出现的概率。


#### 基于递归的解法

解题思路： 

1. 先把n个骰子分为两堆：第一堆只有一个，另一个有n－1个。
2. 单独的那一个有可能出现从1到6的点数。我们需要计算从1到6的每一种点数和剩下的n－1个骰子来计算点数和。
3. 接下来把剩下的n－1个骰子还是分成两堆，第一堆只有一个，第二堆有n－2个。
4. 我们把上一轮那个单独骰子的点数和这一轮单独骰子的点数相加，再和剩下的n－2个骰子来计算点数和。

基于上面思路的代码：

```java
    /**
     * 最大的点数
     */
    static final int gMaxValue = 6;

    /**
     * @param n         循环的次数
     * @param preSumsum 前一个值
     * @param s         预期值
     * @param times     传递次数
     */
    static void sumCountNumber(int n, int preSumsum, int s, int[] times) {
        if (n == 0 && preSumsum == s) {
            times[0] += 1;
            return;
        } else if (n <= 0) {
            return;
        }
        for (int i = 1; i <= gMaxValue; i++) {
            sumCountNumber(n - 1, preSumsum + i, s, times);
        }
    }

    static String properties(int n, int s) {
        double prop = 0.0;
        int min = n * 1;
        int max = n * gMaxValue;
        if (n <= 0 || s < min || s > max) {
            return "0/0";
        }
        int j = n;
        int maxTimes = gMaxValue;
        while (j-- > 1) {
            maxTimes *= gMaxValue;
        }
        int[] times = new int[]{0};
        sumCountNumber(n, 0, s, times);
        return String.format("%s/%s", times[0], maxTimes);
    }
```

#### 解法二：基于循环求骰子点数，时间性能好

可以换一种思路来解决这个问题。我们可以考虑用两个数组来**存储骰子点数**的**每一个总数出现的次数**。

解题思路：

1. 在一次循环中，第一个数组中的第n个数字表示骰子和为n出现的次数。
2. 在下一循环中，我们加上一个新的骰子，此时和为n的骰子出现的次数应该等于上一次循环中骰子点数和为n－1、n－2、n－3、n－4、n－5与n－6的次数的总和，所以我们把另一个数组的第n个数字设为前一个数组对应的第n－1、n－2、n－3、n－4、n－5与n－6之和。

参考代码：

```java
    static void PrintProbability(int number) {
        if (number < 1) {
            return;
        }
        int[][] pProbabilities = new int[2][];
        pProbabilities[0] = new int[g_maxValue * number + 1];
        pProbabilities[1] = new int[g_maxValue * number + 1];
        for (int i = 0; i < g_maxValue * number + 1; ++i) {
            pProbabilities[0][i] = 0;
            pProbabilities[1][i] = 0;
        }

        int flag = 0;
        // 初始化为1的情况
        for (int i = 1; i <= g_maxValue; ++i) {
            pProbabilities[flag][i] = 1;
        }

        for (int k = 2; k <= number; ++k) {
            for (int i = 0; i < k; ++i) {
                pProbabilities[1 - flag][i] = 0;
            }
            for (int i = k; i <= g_maxValue * k; ++i) {
                pProbabilities[1 - flag][i] = 0;
                // f(n) = f(n-1) + f(n-2) + f(n-3) + f(n-4) ...
                for (int j = 1; j <= i && j <= g_maxValue; ++j) {
                    pProbabilities[1 - flag][i] += pProbabilities[flag][i - j];
                }
            }
            flag = 1 - flag;
        }

        int total = (int) Math.pow(g_maxValue, number);
        for (int i = number; i <= g_maxValue * number; ++i) {
            System.out.printf("%s/%s\n", pProbabilities[flag][i], total);
        }
    }
```

当number=4 的时候，与进行调试信息。

```
Arrays.toString(pProbabilities[flag]) = "[0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]"
Arrays.toString(pProbabilities[1 - flag]) = "[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]"

Arrays.toString(pProbabilities[flag]) = "[0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]"
Arrays.toString(pProbabilities[1 - flag]) = "[0, 0, 1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]"

Arrays.toString(pProbabilities[flag]) = "[0, 0, 1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]"
Arrays.toString(pProbabilities[1 - flag]) = "[0, 0, 0, 1, 3, 6, 10, 15, 21, 25, 27, 27, 25, 21, 15, 10, 6, 3, 1, 0, 0, 0, 0, 0, 0]"

Arrays.toString(pProbabilities[flag]) = "[0, 0, 0, 1, 3, 6, 10, 15, 21, 25, 27, 27, 25, 21, 15, 10, 6, 3, 1, 0, 0, 0, 0, 0, 0]"
Arrays.toString(pProbabilities[1 - flag]) = "[0, 0, 0, 0, 1, 4, 10, 20, 35, 56, 80, 104, 125, 140, 146, 140, 125, 104, 80, 56, 35, 20, 10, 4, 1]"

```

### 面试题44：扑克牌的顺子

[牛客网链接](https://www.nowcoder.com/practice/762836f4d43d43ca9deb273b3de8e1f4?tpId=13&tqId=11198&tPage=3&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

> 题目：从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王可以看成任意数字。

解题思路：我们将一副牌进行抽象，将大小王用0表示，可以描述任意字符。

1. 将5个元素的数字进行排序;
2. 统计其中不连续的个数；
3. 统计其中0的个数，如果 0 的个数小于前面的不连续个数则不连续，否则连续。
4. 最后，我们还需要**注意**一点：如果数组中的非0数字重复出现，则该数组不是连续的。

基于上面的思路写下如下代码：

```java
    static boolean isContinuous(int[] numbers) {
        if (numbers == null || numbers.length <= 0) {
            return false;
        }
        Arrays.sort(numbers);
        int gap = 0;
        int zeroTimes = 0;
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] == 0) {
                zeroTimes++;
                continue;
            }
            // 如果为顺子，直接返回false
            if (numbers[i + 1] == numbers[i]) {
                return false;
            } else if (numbers[i + 1] - numbers[i] != 1) {
                gap += numbers[i + 1] - numbers[i] - 1;
            }
        }
        return zeroTimes >= gap ? true : false;
    }
```

运行效率：

> 运行时间：15ms </br> 占用内存：9296k


### 面试题45：圆圈中最后剩下的数字

[牛客网链接](https://www.nowcoder.com/practice/f78a359491e64a50bce2d89cff857eb6?tpId=13&tqId=11199&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

> 题目：0,1,…,n－1这n个数字排成一个圆圈，从数字0开始每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。

例如，0、1、2、3、4这5个数字组成一个圆圈（如图6.2所示），从数字0开始每次删除第3个数字，则删除的前四个数字依次是2、0、4、1，因此最后剩下的数字是3。

![由0－4这5个数字组成的圆圈](剑指offer.image/由0－4这5个数字组成的圆圈.png)

本题就是有名的**约瑟夫（Josephuse）环**问题。我们介绍两种方法：一种方法是用**环形链表**模拟圆圈的经典解法，第二种方法是分析每次被删除的**数字的规律**并直接计算出圆圈中最后剩下的数字。

#### 经典的解法，用环形链表模拟圆圈

我们直接使用Java中的 ListedList 来模拟链表。

```java
    static int removeList(LinkedList lists, int start, int m) {
        int index = start + m;
        while (index > lists.size()) {
            index = index - lists.size();
        }
        index = index - 1;
        lists.remove(index);
        return index < lists.size() ? index : 0;
    }

    static int LastRemaining_Solution(int n, int m) {
        if (n <= 0 || m <= 0) {
            return -1;
        }
        LinkedList<Integer> lists = new LinkedList();
        for (int i = 0; i < n; i++) {
            lists.add(i);
        }
        int start = 0;
        while (lists.size() > 1) {
            start = removeList(lists, start, m);
        }
        return lists.get(0);
    }
```

#### 基于循环的解法

解题思路：约瑟夫环，圆圈长度为 n 的解可以看成长度为 n-1 的解再加上报数的长度 m。因为是圆圈，所以最后需要对 n 取余。

![约瑟夫环推导公式](剑指offer.image/约瑟夫环推导公司.png)


```java
    public int LastRemaining_Solution(int n, int m) {
        if (n == 0)     /* 特殊输入的处理 */
            return -1;
        if (n == 1)     /* 递归返回条件 */
            return 0;
        return (LastRemaining_Solution(n - 1, m) + m) % n;
    }
```

或者通过循环来进行

```java
    static int LastRemaining_Solution(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }
        int last = 0;
        for (int i = 2; i <= n; i++) {
            last = (last + m) % i;
        }
        return last;
    }
```

运行效率为

```
运行时间：16ms
占用内存：9328k
```

### 求1+2+3+...+n

[牛客网链接](https://www.nowcoder.com/practice/7a0da8fc483247ff8800059e12d7caf1?tpId=13&tqId=11200&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：

> 题目：求1＋2＋…＋n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。


解题思路：

1. 不允许使用循环可以使用递归来代替
2. 不允许使用判断可以使用 && 的短路特性来代替

参考代码：

```java
    public int Sum_Solution(int n) {
        int ans = n;
        boolean b = ans != 0 && (ans = n + Sum_Solution(n - 1)) != 0;
        return ans;
    }
```
发现Java中不能自动的转换为Boolean型，所以增加了一些冗余代码。

运行效率：

```
运行时间：19ms
占用内存：9428k
```

### 面试题47：不用加减乘除做加法

[牛客网链接](https://www.nowcoder.com/practice/59ac416b4b944300b617d4f7f111b215?tpId=13&tqId=11201&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：

> 题目：写一个函数，求两个整数之和，要求在函数体内不得使用＋、－、×、÷四则运算符号。

解题思路：

如果学过数电的话，就会有加法器的概念。那么通过位运算来模拟加法就不会显得很困难。模拟a、b两个数的加法计算如下。

1. 将 a 与 b 进行异或运算，这就表示不做进位运算时， a 与 b 的和;
2. 将 a 与 b 进行与运行，并且进行左移一位，就表示 a 与 b 的进位情况;
3. 将 1、 2 两步的和进行 “相加”（重复的计算1、2两步）， 直到 a 和 b不产生进位。

参考代码：

```java
    static int Add(int num1, int num2) {
        int xor = num1 ^ num2;
        int and = (num1 & num2) << 1;
        return and != 0 ? Add(xor, and) : xor;
    }
```

或者基于循环

```java
    static int AddByWhile(int a, int b) {
        int xor, and;
        do {
            xor = a ^ b;
            and = (a & b) << 1;

            a = xor;
            b = and;
        } while (and != 0);
        return xor;
    }
```
运行效率：

```
运行时间：15ms
占用内存：9272k
```

#### 相关问题

不使用新的变量，交换两个变量的值。比如有两个变量 a 和 b，我们希望交换它们的值。有两种不同(加减法或者异或的方法)的办法。

![不使用新的变量交换两个变量的值](剑指offer.image/不使用新的变量交换两个变量的值.png)


### 把字符串转换成整数

[牛客网链接](https://www.nowcoder.com/practice/1277c681251b4372bdef344468e4f26e?tpId=13&tqId=11202&rp=3&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

题目描述：

> 将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0。

解题思路：

1. 将字符串转换为char数组
2. 从数组的最后一位依次的累加
3. 判断首位元素是否为“+”或者“-”元素

参考代码：

```java
    static int StrToInt(String str) {
        if (str == null || str.length() <= 0) {
            return 0;
        }
        int sum = 0;
        char[] chars = str.trim().toCharArray();
        int n = chars.length - 1;
        int times = 1;
        while (n >= 0 && chars[n] >= '0' && chars[n] <= '9') {
            sum += times * (chars[n] - '0');
            times *= 10;
            n--;
        }
        if (n == -1) {
            return sum;
        } else if (n == 0 && chars[0] == '+') {
            return sum;
        } else if (n == 0 && chars[0] == '-') {
            return -sum;
        }
        return 0;
    }
```

程序耗时：

```
运行时间：9ms
占用内存：9300k
```

### 案例一：（面试题49）把字符串转换成整数

[牛客网链接](https://www.nowcoder.com/practice/1277c681251b4372bdef344468e4f26e?tpId=13&tqId=11202&tPage=3&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

参考代码：

```java
    static int StrToInt(String str) {
        if (str == null || str.length() <= 0) {
            return 0;
        }
        int sum = 0;
        char[] chars = str.trim().toCharArray();
        int n = chars.length - 1;
        int times = 1;
        while (n >= 0 && chars[n] >= '0' && chars[n] <= '9') {
            sum += times * (chars[n] - '0');
            times *= 10;
            n--;
        }
        if (n == -1) {
            return sum;
        } else if (n == 0 && chars[0] == '+') {
            return sum;
        } else if (n == 0 && chars[0] == '-') {
            return -sum;
        }
        return 0;
    }
```

### 案例二：（面试题50）树中两个结点的最低公共祖先

里面很多问题都描述的很模糊，就如这里。

如果这个树是一个二叉查找树，我们可以知道，该二叉查找树是排序后的。同一个结点的左子树小于右子树。所以，从根结点出发，依次遍历，找出第一个结点，能够让输入的两个结点的值介于两者之间，那么这个结点就是两个结点的最低公共祖先。

![二叉查找树](https://github.com/CyC2018/CS-Notes/blob/master/pics/293d2af9-de1d-403e-bed0-85d029383528.png)

PS：为啥是最低，因为如果两个结点在同一个树里面，只要不是根结点，那么根结点都将会是着两个结点的最低祖先。

```java
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null){
            return root;
        }
        // 如果两个结点值都小于根结点，则往左子树查找
        if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left, p, q);
        }
        if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }
```

如果这个树就是一个普通的多叉树，但是每个结点保留了指向父结点的指针pParent。那么这道题就转换成了求两个链表的第一个公共子结点。

![树中的结点有指向父结点的指针，用虚线箭头表示](剑指offer.image/树中的结点有指向父结点的指针，用虚线箭头表示.png)

```java
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null){
            return root;
        }
        TreeNode pHead = p;
        TreeNode qHead = q;
    
        // 获得p链表的长度
        int pLength = 0;
        while(p!=root){
            p = p.parent;
            pLength++;
        }
        p = pHead;

        // 获得q链表的长度
        int qLength = 0;
        while(q!=root){
            q = q.parent;
            qLength++;
        }
        q = qHead;

        // 长的链表先走差值步数
        if(qLength>=pLength){
            int diff = qLength - pLength;
            while(diff-- > 0){
                q = q.parent;
            }
        }else{
            int diff = pLength - qLength;
            while(diff-- > 0){
                p = p.parent;
            }
        }

        while(p!=null && q!=null && p!=q){
            p = p.parent;
            q = q.parent;
        }
        return p == q ? p : null;
    }
```

如果就是普通的树呢？没有任何的其它条件

![一棵普通的树，树中的结点没有指向父结点的指针](剑指offer.image/一棵普通的树，树中的结点没有指向父结点的指针.png)

解题思路： 新建两个链表分别保存从根结点到输入的两个结点的路径，然后把问题转换成两个链表的最后公共结点。

我们首先得到一条从根结点到树中某一结点的路径，这就要求在遍历的时候，有一个辅助内存来保存路径。（这里我们假设查找F和H两个结点的最低公共祖先）比如我们用前序遍历的方法来得到从根结点到H的路径的过程是这样的：

1. 遍历到A，把A存放到路径中去，路径中只有一个结点A；
2. 遍历到B，把B存到路径中去，此时路径为A->B；
3. 遍历到D，把D存放到路径中去，此时路径为A->B->D；
4. 遍历到F，把F存放到路径中去，此时路径为A->B->D->F；
5. F已经没有子结点了，因此这条路径不可能到达结点H。把F从路径中删除，变成A->B->D；
6. 遍历G。和结点F一样，这条路径也不能到达H。遍历完G之后，路径仍然是A->B->D；
7. 由于D的所有子结点都遍历过了，不可能到达结点H，因此D不在从A到H的路径中，把D从路径中删除，变成A->B；
8. 遍历E，把E加入到路径中，此时路径变成A->B->E，
9. 遍历H，已经到达目标结点，**A->B->E就是从根结点开始到达H必须经过的路径**。

找出上面的两条路径之后，我们求出两个路径最后的公共子结点。

参考代码：略

上面算法的运行效率： 为了得到从根结点开始到输入的两个结点的两条路径，需要遍历两次树，每遍历一次的时间复杂度是O（n）。得到的两条路径的长度在最差情况时是O（n），通常情况下两条路径的长度是O（logn）。

同样上面的思路，不使用辅助内存，则可以考虑如下。

在左右子树中查找是否存在 p 或者 q，如果 p 和 q 分别在两个子树中，那么就说明根节点就是最低公共祖先。

参考代码：

```java
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }
```


### 数组中重复的数字

[牛客网链接](https://www.nowcoder.com/practice/623a5ac0ea5b4e5f95552655361ae0a8?tpId=13&tqId=11203&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：

> 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。

参考代码：

```java
     /**
     * @param numbers     an array of integers
     * @param length      the length of array numbers
     * @param duplication (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
     * @return true if the input is valid, and there are some duplications in the array number otherwise false
     */
    public boolean duplicate(int numbers[],int length,int [] duplication) {
    StringBuffer sb = new StringBuffer(); 
        for(int i = 0; i < length; i++){
                sb.append(numbers[i] + "");
            }
        for(int j = 0; j < length; j++){
            if(sb.indexOf(numbers[j]+"") != sb.lastIndexOf(numbers[j]+"")){
                duplication[0] = numbers[j];
                return true;
            }
        }
        return false;
    }
```

运行效率

```
运行时间：15ms
占用内存：9680k
```

### 构建乘积数组

[牛客网链接](https://www.nowcoder.com/practice/94a4d381a68b47b7a8bed86f2975db46?tpId=13&tqId=11204&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：

> 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素 B[i]=A[0]\*A[1]\*...\*A[i-1]\*A[i+1]\*...\*A[n-1] 。不能使用除法。 

解题思路：我们可以把 B[i] 看成是左右两部分的乘积。

![B[i]的值可以看作矩阵中每行的乘积](剑指offer.image/B[i]的值可以看作矩阵中每行的乘积.png)

参考代码：

```java
    public int[] multiply(int[] A) {
        if (A == null || A.length <= 0) {
            return null;
        }
        int len = A.length;

        int[] left = new int[len];
        left[0] = 1;
        for (int i = 1; i < A.length; i++) {
            left[i] = left[i - 1] * A[i-1];
        }

        int[] right = new int[len];
        right[A.length - 1] = 1;
        for (int i = A.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * A[i + 1];
        }

        int[] B = new int[len];
        for (int i = 0; i < A.length; i++) {
            B[i] = left[i] * right[i];
        }
        return B;
    }
```

运行效率

```
运行时间：18ms
占用内存：9348k
```

### 正则表达式匹配

[牛客网链接](https://www.nowcoder.com/practice/45327ae22b7b413ea21df13ee7d6429c?tpId=13&tqId=11205&tPage=3&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：

> 请实现一个函数用来匹配包括'.'和'\*'的正则表达式。模式中的字符'.'表示任意一个字符，而'\*'表示它前面的字符可以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab\*ac\*a"匹配，但是与"aa.a"和"ab\*a"均不匹配

解题思路：(非确定性有限状态机)

当模式中的第二个字符不是“\*”时：

1. 如果字符串第一个字符和模式中的第一个字符相匹配，那么字符串和模式都后移一个字符，然后匹配剩余的。
2. 如果 字符串第一个字符和模式中的第一个字符相不匹配，直接返回false。

而当模式中的第二个字符是“\*”时：

如果字符串第一个字符跟模式第一个字符不匹配，则模式后移2个字符，继续匹配。如果字符串第一个字符跟模式第一个字符匹配，可以有3种匹配方式：

1. 模式后移2字符，相当于x\*被忽略；
2. 字符串后移1字符，模式后移2字符；
3. 字符串后移1字符，模式不变，即继续匹配字符下一位，因为\*可以匹配多位；

参考代码：

```java
    public boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        int strIndex = 0;
        int patternIndex = 0;
        return matchCore(str, strIndex, pattern, patternIndex);
    }

    public boolean matchCore(char[] str, int strIndex, char[] pattern, int patternIndex) {
        //有效性检验：str到尾，pattern到尾，匹配成功
        if (strIndex == str.length && patternIndex == pattern.length) {
            return true;
        }
        //pattern先到尾，匹配失败
        if (strIndex != str.length && patternIndex == pattern.length) {
            return false;
        }
        //模式第2个是*，且字符串第1个跟模式第1个匹配,分3种匹配模式；如不匹配，模式后移2位
        if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
            if ((strIndex != str.length && pattern[patternIndex] == str[strIndex]) || (pattern[patternIndex] == '.' && strIndex != str.length)) {
                //模式后移2，视为x*匹配0个字符
                return matchCore(str, strIndex, pattern, patternIndex + 2)
                        //视为模式匹配1个字符
                        || matchCore(str, strIndex + 1, pattern, patternIndex + 2)
                        //*匹配1个，再匹配str中的下一个
                        || matchCore(str, strIndex + 1, pattern, patternIndex);
            } else {
                return matchCore(str, strIndex, pattern, patternIndex + 2);
            }
        }
        //模式第2个不是*，且字符串第1个跟模式第1个匹配，则都后移1位，否则直接返回false
        if ((strIndex != str.length && pattern[patternIndex] == str[strIndex]) || (pattern[patternIndex] == '.' && strIndex != str.length)) {
            return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
        }
        return false;
    }
```

运行效率

```
运行时间：13ms
占用内存：9420k
```

### 表示数值的字符串

[牛客网链接](https://www.nowcoder.com/practice/6f8c901d091949a5837e24bb82a731f2?tpId=13&tqId=11206&tPage=3&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：

> 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。

参考代码：

```java
    /**
     * 符合模式串为 [符号]整数数字[小数点及小数点数字部分][e|E [符号]指数数字部分]
     * <p>
     * [sign]integral-digits[.[fractional-digits][e|E[sign]exponential-digits] 其中[]表示可选部分
     * </p>
     *
     * @param str 输入的字符串
     */
    public boolean isNumeric(char[] str) {
        if (str == null || str.length == 0) {
            return false;
        }
        int[] index = new int[1];

        // ① 第一个符位
        if (str[0] == '+' || str[0] == '-') {
            index[0] += 1;
            if (str.length == 1) {
                return false;
            }
        }

        scanDigits(str, index);
        boolean numeric = true;

        if (index[0] != str.length) {
            if (str[index[0]] == '.') {
                index[0] += 1;
                scanDigits(str, index);
                if (index[0] < str.length && (str[index[0]] == 'e' || str[index[0]] == 'E')) {
                    numeric = isExponential(str, index);
                }
            } else if (index[0] < str.length && (str[index[0]] == 'e' || str[index[0]] == 'E')) {
                numeric = isExponential(str, index);
            } else {
                numeric = false;
            }
        }

        return numeric && index[0] == str.length;
    }

    /**
     * @return 扫描数字字母
     */
    void scanDigits(char[] str, int[] i) {
        if (i[0] > str.length - 1) {
            return;
        }
        while (i[0] <= str.length - 1 && str[i[0]] >= '0' && str[i[0]] <= '9') {
            i[0] += 1;
        }
    }

    /**
     * @param str 输入的字符串
     * @param i   表示起始扫描的位置
     * @return 匹配科学计数法的结尾部分
     */
    boolean isExponential(char[] str, int[] i) {
        if (i[0] > str.length - 1 || i[0] < 0) {
            return false;
        }
        if (str[i[0]] != 'E' && str[i[0]] != 'e') {
            return false;
        }
        i[0] += 1;
        if (i[0] == str.length) {
            return false;
        }
        if (str[i[0]] == '+' || str[i[0]] == '-') {
            i[0] += 1;
        }
        if (i[0] > str.length - 1) {
            return false;
        }
        scanDigits(str, i);
        return i[0] == str.length ? true : false;
    }
```

运行效率

```
运行时间：20ms
占用内存：8984k
```

### 字符流中第一个不重复的字符

[牛客网链接](https://www.nowcoder.com/practice/00de97733b8e4f97a3fb5c680ee10720?tpId=13&tqId=11207&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：

> 请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。如果当前字符流没有存在出现一次的字符，返回#字符。

解题思路：

1. 使用一个数组来存储数组出现的次数
2. firstChar 来表明第一个访问的数组

参考代码：

```java
    int[] cnts = new int[256];
    char firtChar = '#';
    //Insert one char from stringstream
    public void Insert(char ch) {
        if (firtChar == ch) {
            firtChar = '#';
        }
        cnts[ch] += 1;
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        if (firtChar == '#') {
            for (int i = 0; i < cnts.length; i++) {
                if (cnts[i] == 1) {
                    firtChar = (char) i;
                    break;
                }
            }
        }
        return firtChar;
    }
```

运行效率

```
运行时间：18ms
占用内存：9488k
```

### 链表中环的入口结点

[牛客网链接](https://www.nowcoder.com/practice/253d2c59ec3e4bc68da16833f79a38e4?tpId=13&tqId=11208&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：

> 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。

解题思路：

将所有的结点放到set集合中，第一次出现重复的结点就是我们链表的入口结点。

参考代码：

```java
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        Set<ListNode> sets = new HashSet<>();
        while (pHead != null) {
            if (sets.contains(pHead)) {
                return pHead;
            } else {
                sets.add(pHead);
            }
            pHead = pHead.next;
        }
        return null;
    }
```

还有的思路就是不使用额外的存储空间。

1. 先求出环形链表中的环形长度。可参考前面的面试题 “如何判断一个链表有环”，求出环的大小为 k。
2. 同样的定义两个指针 p1 和 p2 两个指针，先让 p1 指针移动 k 步，然后同时的移动 p1 和 p2 指针
3. 当 p1 和 p2 两个指针相遇的时候，这个相遇的结点就是环的入口结点。

参考代码:

```java
    public int getAnnularLegth(ListNode pHead) {
        // 表示不存在环
        int result = -1;
        if (pHead == null) {
            return result;
        }
        // 寻找第一个相遇的结点
        ListNode p1 = pHead, p2 = pHead;
        do {
            if (p1.next == null || p2.next == null) {
                return result;
            }
            p1 = p1.next;
            p2 = p2.next.next;
        } while (p1 != null && p2 != null && p1 != p2);

        // 第一个结点
        ListNode sameNode = p1;
        result = 0;
        do {
            p1 = p1.next;
            result++;
        } while (p1 != sameNode);
        return result;
    }

    public ListNode EntryNodeOfLoop2(ListNode pHead) {
        int K = getAnnularLegth(pHead);
        if (K == -1) {
            return null;
        }

        ListNode p1 = pHead;
        while (K > 0) {
            p1 = p1.next;
            K--;
        }

        ListNode p2 = pHead;
        while (p1 != null && p2 != null && p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
```

运行效率

```
运行时间：21ms
占用内存：9660k

运行时间：13ms
占用内存：9672k
```

### 删除链表中重复的结点

[牛客网链接](https://www.nowcoder.com/practice/fc533c45b73a41b0b44ccba763f866ef?tpId=13&tqId=11209&tPage=3&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：

> 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5

解题思路：

1. 每次从一个结点出发，找到该结点下的下一个不同结点。
2. 从头到尾扫描整个链表

参考代码：

```java
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        pHead = getNextDiffNode(pHead);
        ListNode preNode = pHead;
        while (preNode != null) {
            ListNode diffNode = getNextDiffNode(preNode.next);
            preNode.next = diffNode;
            preNode = diffNode;
        }

        return pHead;
    }

    public ListNode getNextDiffNode(ListNode node) {
        if (node == null) {
            return null;
        }
        ListNode nextNode = node.next;
        if (nextNode == null) {
            return node;
        } else {
            if (node.val != nextNode.val) {
                return node;
            } else {
                while (nextNode != null && nextNode.val == node.val) {
                    nextNode = nextNode.next;
                }
                return getNextDiffNode(nextNode);
            }
        }
    }
```

运行效率

```
运行时间：18ms
占用内存：48k
```



### 二叉树的下一个结点

[牛客网链接](https://www.nowcoder.com/practice/9023a0c988684a53960365b889ceaf5e?tpId=13&tqId=11210&tPage=3&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：

> 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。

解题思路：

1. 如果一个结点有右子结点，那么下一个就是该右子结点的最左子结点；
2. 如果这个结点没有右子结点，且该结点是它父结点的左子结点，那么下一结点就是它自己的父结点；
3. 如果这个结点没有右子结点，且该结点是它父结点的右子结点，那么它的下一个结点就是父结点，持续向上遍历，直到找到一个是它父结点的左子结点的结点。（说的有点绕，大概就是下图所示。）

![Main58第三种情况图示](剑指offer.image/Main58第三种情况图示.png)

参考代码：

```java
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) {
            return null;
        }
        // ① 第一种情况
        if (pNode.right != null) {
            pNode = pNode.right;
            while ((pNode != null && pNode.left != null)) {
                pNode = pNode.left;
            }
            return pNode;
        }


        // ② 第二种情况 和 第三种情况
        TreeLinkNode pParent = pNode.next;
        while (pParent != null && pParent.right == pNode) {
            pNode = pNode.next;
            pParent = pNode.next;
        }

        return pParent != null ? pParent : null;
    }
```

运行效率

```
运行时间：39ms
占用内存：9760k
```

### 对称的二叉树

[牛客网链接](https://www.nowcoder.com/practice/ff05d44dfdb04e1d83bdbdab320efbcb?tpId=13&tqId=11211&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：

> 请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。

解题思路：

分析图示可以看到就是对称的两个结点位置都是，左边的结点的左结点 = 右边结点的右结点， 左边结点的右结点 = 右边结点的左结点。

参考代码：

```java
    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        return isSymmetrical(pRoot.left, pRoot.right);
    }

    boolean isSymmetrical(TreeNode p1, TreeNode p2) {
        if (p1 == null && p2 == null) {
            return true;
        }
        if (p1 != null && p2 != null) {
            if (p1.val == p2.val) {
                return isSymmetrical(p1.left, p2.right) && isSymmetrical(p1.right, p2.left);
            }
        }
        return false;
    }
```

运行效率

```
运行时间：14ms
占用内存：9388k
```

### 把二叉树打印成多行

[牛客网链接](https://www.nowcoder.com/practice/445c44d982d04483b04a54f298796288?tpId=13&tqId=11213&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：

> 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。

解题思路：

1. 按行打印二叉树，思想肯定还是利用队列来保持输出顺序
2. 重点就在于采用 nextLevel 和 toBePrinted 两个元素来记录是否该换行啦
3. nextLevel 表示下一行需要打印的元素个数，toBePrinted 表示当前行还有多少个元素需要打印。

参考代码：

```java
     ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList();
        if (pRoot == null) {
            return lists;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();

        ArrayList<Integer> sublists = new ArrayList();
        queue.add(pRoot);
        // 下一层需要打印的结点数
        int nextLevel = 0;
        // 当前层未打印完的结点数
        int toBePrinted = 1;

        while (!queue.isEmpty()) {
            TreeNode node = queue.pop();
            sublists.add(node.val);
            --toBePrinted;

            if (node.left != null) {
                queue.add(node.left);
                nextLevel++;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextLevel++;
            }

            if (toBePrinted == 0) {
                lists.add(sublists);
                sublists = new ArrayList();
                toBePrinted = nextLevel;
                nextLevel = 0;
            }
        }
        return lists;
    }
```

### 按之字形顺序打印二叉树

[牛客网链接](https://www.nowcoder.com/practice/91b69814117f4e8097390d107d2efbe0?tpId=13&tqId=11212&tPage=3&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：

> 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。

解题思路：

1. 按之字型顺序打印二叉树需要两个栈；
2. 在打印某一行的结点时，把下一层的结点保存到响应的栈中。
3. 如果打印的层数是奇数层，则先保存左结点，再保存右结点，否则的话先右结点，再左结点。

参考代码：

```java
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        if (pRoot == null) {
            return results;
        }

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.add(pRoot);

        // 转换标记
        boolean flag = true;

        ArrayList<Integer> subList = new ArrayList<>();

        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            subList.add(node.val);

            if (flag) {
                if (node.left != null) {
                    stack2.add(node.left);
                }
                if (node.right != null) {
                    stack2.add(node.right);
                }
            } else {
                if (node.right != null) {
                    stack2.add(node.right);
                }
                if (node.left != null) {
                    stack2.add(node.left);
                }
            }

            if (stack1.isEmpty()) {
                Stack stack = stack1;
                stack1 = stack2;
                stack2 = stack;
                results.add(subList);
                subList = new ArrayList<>();
                flag = !flag;
            }
        }
        return results;
    }

```

### 序列化二叉树

[牛客网链接](https://www.nowcoder.com/practice/cf7e25aa97c04cc1a68c8f040e71fb84?tpId=13&tqId=11214&tPage=4&rp=4&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：

> 请实现两个函数，分别用来序列化和反序列化二叉树

序列化解题思路：

1. 利用先序遍历进行序列化
2. 每次遇到结点空格使用 `$` 符号来替代

参考代码

```java
    /*
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }
    */
    /**
     * 序列化
     *
     * @param root
     * @return
     */
    String Serialize(TreeNode root) {
        if (root == null) {
            return "$";
        }
        String result = String.valueOf(root.val);
        result += "," + Serialize(root.left);
        result += "," + Serialize(root.right);
        return result;
    }
```

反序列化解题思路：

1. 将其装换为数组
2. 利用 index 数组来进行指针操作
3. 进行数据的模拟

参考代码：

```java
    /**
     * 反序列化
     *
     * @param str
     * @return
     */
    TreeNode Deserialize(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        String[] items = str.split(",");
        int[] index = {0};
        return Deserialize(items, index);
    }

    TreeNode Deserialize(String[] chars, int[] index) {
        if (index[0] >= chars.length || chars[index[0]].equals("$")) {
            index[0] += 1;
            return null;
        }

        int val = Integer.valueOf(chars[index[0]]);
        TreeNode node = new TreeNode(val);
        index[0] += 1;
        node.left = Deserialize(chars, index);
        node.right = Deserialize(chars, index);
        return node;
    }
```

### 二叉搜索树的第K个结点

[牛客网链接](https://www.nowcoder.com/practice/ef068f602dde4d28aab2b210e859150a?tpId=13&tqId=11215&rp=4&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：

> 给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。

解题思路：

如果按照中序遍历的顺序遍历一颗二叉搜索树，遍历序列的数值是递增排序的

参考代码：

```java
    int index = 0; //计数器
    TreeNode KthNode(TreeNode root, int k) {
        //中序遍历寻找第k个
        if (root != null) {
            TreeNode node = KthNode(root.left, k);
            if (node != null) {
                return node;
            }
            index++;
            if (index == k) {
                return root;
            }
            node = KthNode(root.right, k);
            if (node != null) {
                return node;
            }
        }
        return null;
    }
```

### 数据流中的中位数

[牛客网链接](https://www.nowcoder.com/practice/9be0172896bd43948f8a32fb954e1be1?tpId=13&tqId=11216&rp=4&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：

> 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。

解题思路：

需要完成两个操作，一个是往数据结构中插入数据流中的数据，一个是查找该数据结构中的中位数

|数据结构|插入的时间效率|得到中位数的时间效率|
|---|:---:|:---:|
|没有排序的数组|O(1)|O(n)|
|排序的数组|O(n)|O(1)|
|排序的链表|O(n)|O(1)|
|二叉搜索树|平均O(log(n)，最差O(n))|平均O(logn)，最差O(n)|
|AVL数|O(log(n))|O(1)|
|最大堆和最小堆|O(log(n))|O(1)|

**二叉搜索树**

二叉搜索树可以把插入新数据的平均复杂度降低到$O(logn)$。 但是当二叉搜索数极度的不平衡的从而看起来像一个排序的链表时，插入新数据的复杂度仍然为$O(n)$。

**平衡的二叉搜索树**

也即是 AVL 数。把平衡因子改成左右子树的高度差。

**最大堆和最小堆**

整个数据可以看成被中间的一个或者两个数据分成左右两小段，位于左边容器的数据比位于右边容器的数据小。将左边用最大堆来实现，右边用最小堆来实现。

实现的一些细节：

1. 保证数据的平均分配到两个堆中，可以在偶数时，把数据插入到最小堆中，奇数时，插入到最大堆中。
2. 保证最大堆（左边）中的元素都比最小堆（右边）中的元素小。新元素如果插入到最大堆中，则可以将最大堆中的元素取出，然后插入到最小堆中。插入完之后，再从最小堆中取出最小的元素，插回到最大堆元素之中。新元素插入最小堆中，此过程类似。


参考代码：

```java
    int count;
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    //PriorityQueue默认是小顶堆，实现大顶堆，需要反转默认排序器
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(11, Comparator.reverseOrder());

    public void Insert(Integer num) {
        count++;
        // 判断偶数的高效写法
        if ((count & 1) == 0) {
            if (!maxHeap.isEmpty() && num < maxHeap.peek()) {
                maxHeap.offer(num);
                num = maxHeap.poll();
            }
            minHeap.offer(num);
        } else {
            if (!minHeap.isEmpty() && num > minHeap.peek()) {
                minHeap.offer(num);
                num = minHeap.poll();
            }
            maxHeap.offer(num);
        }
    }

    public Double GetMedian() {
        if (count == 0) {
            throw new RuntimeException("no available number!");
        }
        double result;
        //总数为奇数时，大顶堆堆顶就是中位数
        if ((count & 1) == 1) {
            result = maxHeap.peek();
        } else {
            result = (minHeap.peek() + maxHeap.peek()) / 2.0;
        }
        return result;
    }
```
PS:Java的PriorityQueue 是从JDK1.5开始提供的新的数据结构接口，默认内部是自然排序，结果为小顶堆，也可以自定义排序器，比如下面反转比较，完成大顶堆。

### 滑动窗口的最大值

[牛客网链接](https://www.nowcoder.com/practice/1624bc35a45c42c0bc17d17fa0cba788?tpId=13&tqId=11217&rp=4&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：

> 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。

解题思路：

滑动窗口应当是队列，但为了得到滑动窗口的最大值，队列序可以从两端删除元素，因此使用双端队列。

原则：
1. 对新来的元素k，将其与双端队列中的元素相比较
2. 前面比k小的，直接移出队列（因为不再可能成为后面滑动窗口的最大值了!）,
3. 前面比k大的X，比较两者下标，判断X是否已不在窗口之内，不在了，直接移出队列
4. 队列的第一个元素是滑动窗口中的最大值

参考代码：

```java
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (num == null) {
            return ret;
        }
        if (num.length < size || size < 1) {
            return ret;
        }

        LinkedList<Integer> indexDeque = new LinkedList<>();
        for (int i = 0; i < size - 1; i++) {
            while (!indexDeque.isEmpty() && num[i] > num[indexDeque.getLast()]) {
                indexDeque.removeLast();
            }
            indexDeque.addLast(i);
        }

        for (int i = size - 1; i < num.length; i++) {
            while (!indexDeque.isEmpty() && num[i] > num[indexDeque.getLast()]) {
                indexDeque.removeLast();
            }
            indexDeque.addLast(i);
            if (i - indexDeque.getFirst() + 1 > size) {
                indexDeque.removeFirst();
            }
            ret.add(num[indexDeque.getFirst()]);
        }
        return ret;
    }
```

### 矩阵中的路径

[牛客网链接](https://www.nowcoder.com/practice/c61c6999eecb4b8f88a98f66b273a3cc?tpId=13&tqId=11218&rp=4&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：

> 链接：https://www.nowcoder.com/questionTerminal/c61c6999eecb4b8f88a98f66b273a3cc
来源：牛客网

请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这个格子。 例如 a b c e s f c s a d e e 这样的3 X 4 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。

[解题思路](https://www.nowcoder.com/questionTerminal/c61c6999eecb4b8f88a98f66b273a3cc)：

这是一个可以用回朔法解决的典型题。

1. 首先，在矩阵中任选一个格子作为路径的起点。如果路径上的第i个字符不是ch，那么这个格子不可能处在路径上的
第i个位置。如果路径上的第i个字符正好是ch，那么往相邻的格子寻找路径上的第i+1个字符。除在矩阵边界上的格子之外，其他格子都有4个相邻的格子。
重复这个过程直到路径上的所有字符都在矩阵中找到相应的位置。
2. 由于回朔法的递归特性，路径可以被开成一个栈。当在矩阵中定位了路径中前n个字符的位置之后，在与第n个字符对应的格子的周围都没有找到第n+1个
字符，这个时候只要在路径上回到第n-1个字符，重新定位第n个字符。
3. 由于路径不能重复进入矩阵的格子，还需要定义和字符矩阵大小一样的布尔值矩阵，用来标识路径是否已经进入每个格子。 当矩阵中坐标为（row,col）的
格子和路径字符串中相应的字符一样时，从4个相邻的格子(row,col-1),(row-1,col),(row,col+1)以及(row+1,col)中去定位路径字符串中下一个字符
如果4个相邻的格子都没有匹配字符串中下一个的字符，表明当前路径字符串中字符在矩阵中的定位不正确，我们需要回到前一个，然后重新定位。
4. 一直重复这个过程，直到路径字符串上所有字符都在矩阵中找到合适的位置

参考代码：

```java
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        boolean[] visited = new boolean[matrix.length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (searchFromHere(matrix, rows, cols, i, j, 0, str, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean searchFromHere(char[] matrix, int rows, int cols, int r, int c, int index, char[] str, boolean[] visited) {
        if (r < 0 || r >= rows || c < 0 || c >= cols || matrix[r * cols + c] != str[index] || visited[r * cols + c]) {
            return false;
        }
        if (index == str.length - 1) {
            return true;
        }
        visited[r * cols + c] = true;
        if (searchFromHere(matrix, rows, cols, r - 1, c, index + 1, str, visited) ||
                searchFromHere(matrix, rows, cols, r, c - 1, index + 1, str, visited) ||
                searchFromHere(matrix, rows, cols, r + 1, c, index + 1, str, visited) ||
                searchFromHere(matrix, rows, cols, r, c + 1, index + 1, str, visited)) {
            return true;
        }
        visited[r * cols + c] = false;
        return false;
    }
```

### 机器人的运动范围

[牛客网链接](https://www.nowcoder.com/practice/6e5207314b5241fb83f2329e89fdecc8?tpId=13&tqId=11219&rp=4&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

题目描述：

> 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？

解题思路：

与上面的题目类似，这个方格可以看成是一个$m\timesn$的矩阵。机器人从坐标 (0,0) 开始移动。当它准备进入坐标为 (i,j) 的格子的时，通过检查坐标的**数位和**来判断机器人是否能够进入。如果机器人能够进入，我们接着判断它能够进入四个相邻的格子 (i,j-1)、(i-1,j)、(i,j+1)、(i+1,j)。

利用回溯法的参考代码：

```java
    public int movingCount(int threshold, int rows, int cols) {
        boolean[][] visited = new boolean[rows][cols];
        return countingSteps(threshold, rows, cols, 0, 0, visited);
    }

    public int countingSteps(int limit, int rows, int cols, int r, int c, boolean[][] visited) {
        if (r < 0 || r >= rows || c < 0 || c >= cols
                || visited[r][c] || getDigitSum(r) + getDigitSum(c) > limit) {
            return 0;
        }
        visited[r][c] = true;
        return countingSteps(limit, rows, cols, r - 1, c, visited)
                + countingSteps(limit, rows, cols, r, c - 1, visited)
                + countingSteps(limit, rows, cols, r + 1, c, visited)
                + countingSteps(limit, rows, cols, r, c + 1, visited)
                + 1;
    }

    public int getDigitSum(int t) {
        int count = 0;
        while (t != 0) {
            count += t % 10;
            t /= 10;
        }
        return count;
    }
```


### 送快递

题目描述：

> 如图，某物流派送员p，需要给a、b、c、d 4个快递点派送包裹，请问派送员需要选择什么的路线，才能完成最短路程的派送。假设如图派送员的起点坐标(0,0)，派送路线只能沿着图中的方格边行驶，每个小格都是正方形，且边长为1，如p到d的距离就是4。随机输入n个派送点坐标，求输出最短派送路线值（从起点开始完成n个点派送并回到起始点的距离）。

![30分钟面试题](剑指offer.image/TB1cUaAdMmTBuNjy1XbXXaMrVXa-1242-844.png)

解题思路：

利用全排列的思想，将所有的点依次的读入，记录每个节点的状态

参考代码：

```java
public class offer.alibaba.Main2019_01 {

    static Point zero = new Point(0, 0);
    int min;
    // 所有的点
    Point[] points;
    // 记录总共的次数
    int count = 0;

    public offer.alibaba.Main2019_01(Point[] points) {
        this.points = new Point[points.length + 1];
        System.arraycopy(points, 0, this.points, 1, points.length);
        this.points[0] = zero;
        min = Integer.MAX_VALUE;
    }

    public int getMinLenth() {
        boolean[] statue = new boolean[points.length];
        Arrays.fill(statue, true);
        statue[0] = false;
        getMinLenth(statue, zero, 0);
        return min;
    }

    private void getMinLenth(boolean[] statue, Point pre, int sum) {
        if (isEnd(statue)) {
            sum += lenth(pre, zero);
            if (sum < this.min) {
                this.min = sum;
            }
            System.out.println(this.count++ + "\t" + sum);
        }
        for (int i = 1; i < statue.length; i++) {
            if (statue[i]) {
                sum += lenth(pre, points[i]);
                boolean[] newStatus = new boolean[statue.length];
                System.arraycopy(statue, 0, newStatus, 0, statue.length);
                newStatus[i] = false;
                getMinLenth(newStatus, points[i], sum);
            }
        }
    }

    // 递推结束条件
    boolean isEnd(boolean[] state) {
        for (boolean b : state) {
            if (b) {
                return false;
            }
        }
        return true;
    }

    // 计算两个点之间的距离
    int lenth(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}
```

模拟的点类，将各个放快递的模拟成点。

```java
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
```

运行的主类，模拟数据的输入

```java
//        String str = "4\n" +
//                "2,2\n" +
//                "2,8\n" +
//                "4,4\n" +
//                "7,2";

        String str = "3\n" +
                "2,2\n" +
                "2,8\n" +
                "6,6";
        StringReader sr = new StringReader(str);
        Scanner sc = new Scanner(sr);

        int M = sc.nextInt();
        sc.useDelimiter(",|\\n");
        Point[] points = new Point[M];
        for (int i = 0; i < M; i++) {
            points[i] = new Point(sc.nextInt(), sc.nextInt());
        }

        offer.alibaba.Main2019_01 main2019shixi = new offer.alibaba.Main2019_01(points);
        System.out.println(main2019shixi.getMinLenth());
```


时间限制: 3S (C/C++以外的语言为: 5 S)   内存限制: 128M (C/C++以外的语言为: 640 M)
输入:
```
4
2,2
2,8
4,4
7,2
```
输出:
```
30
```
输入范例:
```
3
2,2
2,8
6,6
```
输出范例:
```
28
```
