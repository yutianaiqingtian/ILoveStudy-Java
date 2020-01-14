package offer.toutiao.four;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author jhZhang
 * @date 2018/9/20
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        System.out.println(simplifyPath(path));
    }

    static String simplifyPath(String path) {
        if (path == null || path.length() == 0)
            return path;
        Stack<String> stack = new Stack<String>();
        String[] list = path.split("/");
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(".") || list[i].length() == 0)
                continue;
            else if (!list[i].equals(".."))
                stack.push(list[i]);
            else {
                if (!stack.isEmpty())
                    stack.pop();
            }
        }
        StringBuilder res = new StringBuilder();
        Stack<String> temp = new Stack<String>();
        while (!stack.isEmpty())
            temp.push(stack.pop());
        while (!temp.isEmpty())
            res.append("/" + temp.pop());
        if (res.length() == 0) {
            res.append("/");
        }
        return res.toString();
    }
}
