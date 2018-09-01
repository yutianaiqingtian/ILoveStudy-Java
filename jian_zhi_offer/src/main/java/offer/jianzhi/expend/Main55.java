package offer.jianzhi.expend;

/**
 * 字符流中第一个不重复的字符
 *
 * @author jhZhang
 * @date 2018/9/1
 */
public class Main55 {
    int[] cnts = new int[256];
    char firtChar = '#';

    public static void main(String[] args) {
        Main55 test = new Main55();
        String str = "go";
        for (char c : str.toCharArray()) {
            test.Insert(c);
            System.out.println(test.FirstAppearingOnce());
        }

    }


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
}
