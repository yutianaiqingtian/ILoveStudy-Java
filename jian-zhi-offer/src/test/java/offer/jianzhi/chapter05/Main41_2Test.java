package offer.jianzhi.chapter05;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class Main41_2Test {

    @Test
    public void findContinuousSequence() {
        ArrayList<ArrayList<Integer>> result = Main41_2.FindContinuousSequence(3);
        Assert.assertEquals("[[1, 2]]", Arrays.toString(result.toArray()));

        result = Main41_2.FindContinuousSequence(9);
        Assert.assertEquals("[[2, 3, 4], [4, 5]]", Arrays.toString(result.toArray()));

        result = Main41_2.FindContinuousSequence(15);
        Assert.assertEquals("[[1, 2, 3, 4, 5], [4, 5, 6], [7, 8]]", Arrays.toString(result.toArray()));
    }
}