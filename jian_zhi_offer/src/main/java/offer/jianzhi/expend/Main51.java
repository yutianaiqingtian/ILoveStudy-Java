package offer.jianzhi.expend;

/**
 * 数组中重复的数字
 *
 * @author jhZhang
 * @date 2018/8/28
 */
public class Main51 {
    /**
     * @param numbers     an array of integers
     * @param length      the length of array numbers
     * @param duplication (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
     * @return true if the input is valid, and there are some duplications in the array number otherwise false
     */
    public static boolean duplicate(int numbers[], int length, int[] duplication) {
        boolean validate = false;
        if (numbers == null || length <= 0) {
            return validate;
        }
        int[] cnts = new int[length];
        for (int number : numbers) {
            if (cnts[number] != 0) {
                duplication[0] = number;
                validate = true;
                break;
            }
            cnts[number] += 1;
        }
        return validate;
    }

    public static void main(String[] args) {
//        int[] numbers = new int[]{2, 3, 1, 0, 2, 5, 3};
        int[] numbers = new int[]{2, 1, 3, 1, 4};
        int[] duplication = new int[1];

        System.out.println(duplicate(numbers, numbers.length, duplication) + "\t" + duplication[0]);
    }
}
