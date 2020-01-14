package offer.jianzhi.expend;

import org.junit.Assert;
import org.junit.Test;

public class Main53Test {

    Main53 test = new Main53();

    @Test
    public void match1() {
        Assert.assertTrue(test.match("aaa".toCharArray(), "a.a".toCharArray()));
    }

    @Test
    public void match2() {
        Assert.assertFalse(test.match("aa.a".toCharArray(), "ab*a".toCharArray()));
    }

    @Test
    public void match3() {
        Assert.assertTrue(test.match("".toCharArray(), "".toCharArray()));
    }

    @Test
    public void match4() {
        Assert.assertTrue(test.match("".toCharArray(), ".*".toCharArray()));
    }
}