package offer.jianzhi.expend;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class Main52Test {

    @Test
    public void multiply() {
        int[] A = new int[]{1, 2, 3, 4, 5};
        int[] A_Expect = new int[]{120, 60, 40, 30, 24};
        Main52 test = new Main52();
        int[] A_actural = test.multiply(A);
        Assert.assertEquals(Arrays.toString(A_Expect), Arrays.toString(A_actural));
    }
}