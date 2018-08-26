package offer.jianzhi.chapter06;

/**
 * @author jhZhang
 * @date 2018/8/26
 */
public class Main46 {
    public int Sum_Solution(int n) {
        int ans = n;
        boolean b = ans != 0 && (ans = n + Sum_Solution(n - 1)) != 0;
        return ans;
    }
}
