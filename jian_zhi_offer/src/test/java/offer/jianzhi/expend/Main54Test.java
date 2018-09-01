package offer.jianzhi.expend;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class Main54Test {
    static Main54 test;

    @BeforeClass
    public static void setUp() {
        test = new Main54();
    }

    @Test
    public void isNumeric1() {
        Assert.assertTrue(test.isNumeric("100".toCharArray()));
    }

    @Test
    public void isNumeric2() {
        Assert.assertTrue(test.isNumeric("3.1416".toCharArray()));
    }

    @Test
    public void isNumeric3() {
        Assert.assertTrue(test.isNumeric("-.123".toCharArray()));
    }

    @Test
    public void isNumeric4() {
        Assert.assertTrue(test.isNumeric("123.45e+6".toCharArray()));
    }
}