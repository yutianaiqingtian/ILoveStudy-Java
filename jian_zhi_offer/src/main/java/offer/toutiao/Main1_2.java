package offer.toutiao;

import java.io.StringReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 2019年-今日头条研发第二次笔试-帮助编辑合并病句
 *
 * @author jhZhang
 * @date 2018/8/13
 */
public class Main1_2 {

    /**
     * 思路一： 获得所有句子
     *
     * @param bytes
     * @return
     */
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
        List<Sentence> lists = new LinkedList<>();
        int M = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < M; i++) {
            String line = sc.nextLine();
            String[] items = line.split(";");
            for (String item : items) {
                String[] tmp = item.split(",");
                if (tmp != null && tmp.length == 2) {
                    int a = Integer.valueOf(tmp[0]);
                    int b = Integer.valueOf(tmp[1]);
                    lists.add(new Sentence(a, b));
                }
            }
        }
        Collections.sort(lists);

        combine(lists, 0);

        System.out.println(lists);

    }

    static void combine(List<Sentence> lists, int i) {
        if (i < 0 || i >= lists.size()) {
            return;
        }
        Sentence cur = lists.get(i);
        if (i <= lists.size() - 2) {
            Sentence next = lists.get(i + 1);
            if (cur.end >= next.start) {
                next.start = cur.start;
                lists.remove(i);
            } else {
                i++;
            }
            combine(lists, i);
        }
    }

    static class Sentence implements Comparable {
        int start;
        int end;

        public Sentence(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Sentence) {
                Sentence sen = (Sentence) obj;
                return sen.start == this.start && sen.end == this.end;
            }
            return false;
        }

        @Override
        public int compareTo(Object o) {
            Sentence sen = (Sentence) o;
            if (sen.start < this.start) {
                return 1;
            } else if (sen.start == this.start) {
                return 0;
            } else {
                return -1;
            }
        }

        @Override
        public String toString() {
            return start + "," + end;
        }
    }
}
