package offer.toutiao;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class Main3912Test {

    @Test
    public void valueOf1() {
        String line = "1,10";
        int[] actural = Solution2.valueOf(line);
        int[] expect = new int[]{1, 10};
        Assert.assertEquals(Arrays.toString(expect), Arrays.toString(actural));
    }

    @Test
    public void valueOf2(){
        String line = "1 10";
        int[] actural = Solution2.valueOf(line);
        Assert.assertNull(actural);
    }
    @Test
    public void valueOf3(){
        String line = "10,1";
        int[] actural = Solution2.valueOf(line);
        Assert.assertNull(actural);
    }

    @Test
    public void getAllSentences(){
        boolean[] bytes = new boolean[]{false,true,true,true,false};
        String expect = "2,4";
        Assert.assertEquals(expect,Solution2.getAllSentences(bytes));
    }

}