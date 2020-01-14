package offer.jianzhi;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] popA = new int[4];
        for (int i = 0; i < 4; i++) {
            popA[i] = sc.nextInt();
        }
        int[] pushA = {1, 2, 3, 4};
        boolean result = IsPopOrder(pushA, popA);
        if (result) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    public static boolean IsPopOrder(int[] pushA, int[] popA) {
        Stack<Integer> seq = new Stack<Integer>();
        Queue<Integer> queue = new LinkedList<Integer>();
        if (pushA.length == 0 || popA.length == 0) {
            return false;
        }
        for (int i = 0; i < pushA.length; i++) {
            queue.add(pushA[i]);
        }
        for (int i = 0; i < popA.length; i++) {
            int ele = popA[i];

            if (!seq.isEmpty() && seq.peek() == ele) {  //栈顶元素等于出栈元素，出栈
                seq.pop();
            } else {               //栈顶元素不是出栈元素，在队列中查找元素，并将这个元素之前的元素进栈，如果没找到对应的元素，则为非法序列
                while (!queue.isEmpty() && queue.peek() != ele) {
                    seq.add(queue.poll());
                }
                if (queue.isEmpty()) {        //队列已经空了，还没找到对应的元素，非法序列
                    return false;
                }
                queue.poll();       //将ele元素出队列，因为要从栈中弹出，就不入栈了
            }
        }
        return true;
    }
}
