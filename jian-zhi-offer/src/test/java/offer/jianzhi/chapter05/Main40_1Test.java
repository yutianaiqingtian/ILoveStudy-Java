package offer.jianzhi.chapter05;

import org.junit.Assert;
import org.junit.Test;

public class Main40_1Test {

    @Test
    public void findNumsAppearOnce() {
        int[] num1 = {0};
        int[] num2 = {0};
        int[] arrays = {2, 4, 3, 6, 3, 2, 5, 5};
        Main40_1.FindNumsAppearOnce(arrays, num1, num2);
        Assert.assertEquals(4, num1[0]);
        Assert.assertEquals(6, num2[0]);

    }

    @Test
    public void findFirstBitIs1() {

        Assert.assertEquals(0, Main40_1.findFirstBitIs1(5));
        Assert.assertEquals(1, Main40_1.findFirstBitIs1(2));
        Assert.assertEquals(2, Main40_1.findFirstBitIs1(4));
    }
}