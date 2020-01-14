package offer.toutiao.one;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class Main1_2Test {
    static int[] valueOf(String line) {
        if (line == null) {
            return null;
        }
        String[] items = line.split(",");
        if (items != null && items.length == 2) {
            int a;
            int b;
            try {
                a = Integer.valueOf(items[0]);
                b = Integer.valueOf(items[1]);
            } catch (NumberFormatException e) {
                return null;
            }
            if (a > b) {
                return null;
            }
            return new int[]{a, b};
        }
        return null;
    }


    @Test
    public void valueOf1() {
        String line = "1,10";
        int[] actural = valueOf(line);
        int[] expect = new int[]{1, 10};
        Assert.assertEquals(Arrays.toString(expect), Arrays.toString(actural));
    }

    @Test
    public void valueOf2() {
        String line = "1 10";
        int[] actural = valueOf(line);
        Assert.assertNull(actural);
    }

    @Test
    public void valueOf3() {
        String line = "10,1";
        int[] actural = valueOf(line);
        Assert.assertNull(actural);
    }

    @Test
    public void getAllSentences() {
        boolean[] bytes = new boolean[]{false, true, true, true, false};
        String expect = "2,4";
        Assert.assertEquals(expect, Main1_2.getAllSentences(bytes));
    }

}