package offer.jianzhi.expend;

/**
 * 构建乘积数组
 *
 * @author jhZhang
 * @date 2018/8/28
 */
public class Main52 {
    public int[] multiply(int[] A) {
        if (A == null || A.length <= 0) {
            return null;
        }
        int len = A.length;

        int[] left = new int[len];
        left[0] = 1;
        for (int i = 1; i < A.length; i++) {
            left[i] = left[i - 1] * A[i - 1];
        }

        int[] right = new int[len];
        right[A.length - 1] = 1;
        for (int i = A.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * A[i + 1];
        }

        int[] B = new int[len];
        for (int i = 0; i < A.length; i++) {
            B[i] = left[i] * right[i];
        }
        return B;
    }
}
