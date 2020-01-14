package offer.toutiao.four;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    private int[] dy = {-1, 1, 0, 0};
    private int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) {
        byte b1 = 1, b2 = 2, b3, b6;
        final byte b4 = 4, b5 = 6;
        b6 = b4 + b5;
        b3 = (byte) (b1 + b2);
        System.out.println(b3 + b6);

        String str = "5 5 3\n" +
                "hello help high\n" +
                "p a b h m\n" +
                "f h e c p\n" +
                "o i l l h\n" +
                "b g h o n\n" +
                "h x c m l\n";
        StringReader sr = new StringReader(str);
        Scanner sc = new Scanner(sr);

//        Scanner sc = new Scanner(System.in);

        int M = sc.nextInt();
        int N = sc.nextInt();
        int K = sc.nextInt();
        String[] words = new String[K];
        for (int i = 0; i < K; i++) {
            words[i] = sc.next();
        }
        char[][] dicts = new char[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                String c = sc.next();
                dicts[i][j] = c.charAt(0);
            }
        }
        Main test = new Main();
        List<String> lists = test.findWords(dicts, words);
        Collections.sort(lists);
        for (String string : lists) {
            System.out.println(string);
        }
    }

    private void find(char[][] board, boolean[][] visit, int y, int x, Trie node, List<String> results) {
        if (y < 0 || y >= board.length || x < 0 || x >= board[y].length) return;
        if (visit[y][x]) return;
        visit[y][x] = true;
        Trie next = node.nexts[board[y][x] - 'a'];
        if (next != null) {
            if (next.word != null) {
                results.add(next.word);
                next.word = null;
            }
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                find(board, visit, ny, nx, next, results);
            }
        }
        visit[y][x] = false;
    }

    public List<String> findWords(char[][] board, String[] words) {
        Trie root = new Trie();
        for (String word : words) {
            Trie node = root;
            char[] wa = word.toCharArray();
            for (char c : wa) node = node.append(c);
            node.word = word;
        }
        boolean[][] visit = new boolean[board.length][board[0].length];
        List<String> results = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                find(board, visit, i, j, root, results);
            }
        }
        return new ArrayList<>(results);
    }
}

class Trie {
    String word;
    Trie[] nexts = new Trie[26];

    Trie append(char ch) {
        if (nexts[ch - 'a'] == null) nexts[ch - 'a'] = new Trie();
        return nexts[ch - 'a'];
    }
}
