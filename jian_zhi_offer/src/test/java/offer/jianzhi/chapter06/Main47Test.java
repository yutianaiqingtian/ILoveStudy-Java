package offer.jianzhi.chapter06;

import org.junit.Assert;
import org.junit.Test;

public class Main47Test {

    @Test
    public void add() {
        Assert.assertEquals(Main47.Add(111, 899), 111 + 899);
    }

    @Test
    public void addByWhile() {
        Assert.assertEquals(Main47.AddByWhile(111, 899), 111 + 899);
    }
}