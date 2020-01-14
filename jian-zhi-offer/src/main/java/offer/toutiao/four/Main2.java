package offer.toutiao.four;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author jhZhang
 * @date 2018/9/20
 */
public class Main2 {
    /**
     * 根结点
     */
    Trie root;

    public static void main(String[] args) {
        String str = "5\n" +
                "bytedance\n" +
                "toutiaohao\n" +
                "toutiaoapp\n" +
                "iesaweme\n" +
                "iestiktok";
        StringReader sr = new StringReader(str);
        Scanner sc = new Scanner(sr);

//        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = sc.next();
        }

        Main2 test = new Main2();
        System.out.println(test.findWords(words));
    }


    public List<String> findWords(String[] words) {
        this.root = new Trie();
        for (String word : words) {
            Trie node = root;
            char[] wa = word.toCharArray();
            for (char c : wa) {
                node = node.append(c);
            }
        }

        List<String> preStrign = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            preStrign.add(preSuffix(words[i]));
        }
        return preStrign;
    }

    private String preSuffix(String word) {
        StringBuilder sb = new StringBuilder();
        char[] chars = word.toCharArray();
        Trie node = root;
        for (int i = 0; i < chars.length; i++) {
            // 该字符是它的下一个元素
            char ch = chars[i];
            if (root.nexts[ch - 'a'] != null && root.nexts[ch - 'a'].statues == 0) {
                sb.append(ch);
                break;
            }
            sb.append(chars[i]);
            // 向后移动一个结点
            node = node.nexts[ch - 'a'];
        }
        return sb.toString();
    }

    class Trie {
        int statues = -1;
        Trie[] nexts = new Trie[26];

        Trie append(char ch) {
            if (nexts[ch - 'a'] == null) {
                nexts[ch - 'a'] = new Trie();
            }
            nexts[ch - 'a'].statues += 1;
            return nexts[ch - 'a'];
        }
    }

}
