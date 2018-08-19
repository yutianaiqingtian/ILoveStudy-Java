package offer.jianzhi.chapter05;

/**
 * @author jhZhang
 * @date 2018/8/13
 */
public class Main38 {
    public static void main(String[] args) {
//        int[] array = new int[]{1, 2, 3, 3, 3, 3, 4, 5};
        int[] array = new int[]{1, 2, 3, 3, 3, 3, 4, 5};
        // 期望为
        Main38 test = new Main38();
        System.out.println(test.GetNumberOfK(array, 0));
    }

    /**
     * @return 获得数组中的第一个 k 元素
     */
    int getFirstNumberOfK(int[] array, int left, int right, int k) {
        if (left > right) {
            return -1;
        }
        int midIndex = (left + right) >> 1;
        int midValue = array[midIndex];
        if (midValue == k) {
            if (midIndex > 0 && array[midIndex - 1] != k || midIndex == 0) {
                return midIndex;
            } else {
                right = midIndex - 1;
            }
        } else if (midValue < k) {
            left = midIndex + 1;
        } else {
            right = midIndex - 1;
        }
        return getFirstNumberOfK(array, left, right, k);
    }

    /**
     * @return 获得数组中的最后一个 k 元素
     */
    int getLastNumberOfK(int[] array, int left, int right, int k) {
        if (left > right) {
            return -1;
        }
        int midIndex = (left + right) >> 1;
        int midValue = array[midIndex];
        if (midValue == k) {
            if (midIndex < array.length - 1 && array[midIndex + 1] != k || midIndex == array.length - 1) {
                return midIndex;
            } else {
                left = midIndex + 1;
            }
        } else if (midValue < k) {
            left = midIndex + 1;
        } else {
            right = midIndex - 1;
        }
        return getLastNumberOfK(array, left, right, k);
    }

    public int GetNumberOfK(int[] array, int k) {
        int startIndex = getFirstNumberOfK(array, 0, array.length-1, k);
        if (startIndex == -1) {
            return 0;
        }
        int endIndex = getLastNumberOfK(array, 0, array.length-1, k);
        if (endIndex == -1) {
            return 0;
        }
        return endIndex >= startIndex ? endIndex - startIndex + 1 : 0;
    }
}
