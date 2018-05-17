package offer.jianzhi.chapter2;

/**
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * @author jhZhang
 * @date 2018/5/14
 */
public class Main3 {
public boolean Find(int target, int[][] array) {
    int rolCount = array.length;
    int colCount = array[0].length;
    int i, j;
    for (i = rolCount - 1, j = 0; i >= 0 && j < colCount; ) {
        if (array[i][j] == target) {
            return true;
        } else if (array[i][j] > target) {
            i--;
        } else if (array[i][j] < target) {
            j++;
        }
    }
    return false;
}
}
