package offer.jianzhi.chapter2;

/**
 * @author jhZhang
 * @date 2018/4/12
 */
public class BitCounter {

    public static void main(String[] args) {
        int n = 9;
        System.out.println(numberOf1(n));
    }

    /**
     * 输入一个整数，输出该数二进制表示中的１的个数结论：
     * 把一个整数减去１再和原整数与，就会把整数最右边一个１变成０
     *
     * @param n
     * @return
     */
    public static int numberOf1(int n) {
        int count = 0;
        int flag = 1;
        while (flag != 0) {
            if ((flag & n) != 0) count++;
            flag = flag << 1;
        }
        return count;
    }

    /*
    一个二维数组，每一行从左到右递增，每一列从上到下递增．
    输入一个二维数组和一个整数，判断数组中是否含有整数
     */
    public class Find {
        public boolean find(int[][] array, int number) {
            if (array == null)
                return false;
            int column = array[0].length - 1, row = 0;
            while (row < array.length && column >= 0) {
                if (array[row][column] == number)
                    return true;
                if (array[row][column] > number)
                    column--;
                else
                    row++;

            }
            return false;
        }
    }
}
