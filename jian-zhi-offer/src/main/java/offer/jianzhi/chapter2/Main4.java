package offer.jianzhi.chapter2;

/**
 * @author jhZhang
 * @date 2018/4/3
 */
public class Main4 {
    /**
     * 替换空格为 %20
     *
     * @param chars
     */
    public static String replaceString(char[] chars) {
        if (chars.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != 32) {
                sb.append(chars[i]);
            } else {
                sb.append("%20");
            }
        }
        return sb.toString();

    }

    public static void main(String[] args) {
        String str = "We are happy";
        System.out.println(replaceString(str.toCharArray()));
    }
}
