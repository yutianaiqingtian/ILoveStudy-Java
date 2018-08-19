package offer.toutiao;

import java.io.StringReader;
import java.util.Scanner;

/**
 * @author jhZhang
 * @date 2018/8/13
 */
public class Solution2 {

    static int[] valueOf(String line) {
        if (line == null) {
            return null;
        }
        String[] items = line.split(",");
        if (items != null && items.length == 2) {
            int a;
            int b;
            try {
                a = Integer.valueOf(items[0]);
                b = Integer.valueOf(items[1]);
            } catch (NumberFormatException e) {
                return null;
            }
            if (a > b) {
                return null;
            }
            return new int[]{a, b};
        }
        return null;
    }

    static String getAllSentences(boolean[] bytes) {
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for (int i = 0; i < bytes.length; i++) {
            if (!flag && bytes[i]) {
                flag = true;
                sb.append(i + 1).append(",");
            } else if (flag && !bytes[i]) {
                flag = false;
                sb.append(i).append(";");
            }
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : null;
    }


    public static void main(String[] args) {
        String str = "3\n" +
                "1,10;32,45\n" +
                "78,94;5,16\n" +
                "80,100;200,220;16,32";
        StringReader sr = new StringReader(str);
        Scanner sc = new Scanner(sr);
        int M = sc.nextInt();
        boolean[] values = new boolean[Integer.MAX_VALUE >> 2];
        for (int i = 0; i < M; i++) {
            String line = sc.nextLine();
            String[] items = line.split(";");
            for (String item : items) {
                String[] tmp = item.split(",");
                if (tmp != null && tmp.length == 2) {
                    int a = Integer.valueOf(tmp[0]);
                    int b = Integer.valueOf(tmp[1]);
                    if (a <= b) {
                        for (int j = a; j < b; j++) {
                            values[j] = true;
                        }
                    }
                }
            }
        }
        System.out.println(getAllSentences(values));
    }
}
