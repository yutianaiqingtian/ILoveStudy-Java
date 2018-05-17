package offer.jianzhi.chapter2;

/**
 * @author jhZhang
 * @date 2018/3/29
 */
public class Programe {
    public static void main(String[] args) {
        String str1 = "hello world";
        String str2 = "hello world";
        System.out.println(str1==str2);
        System.out.println(str1.getClass());
        String[] str3 = {"h","k"};
        String[] str4 = {"h","k"};
        System.out.println(str3==str4);
    }
}
