import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;


public class VernamaTest {

    private Vernama vernama;

    @Before
    public void setUp() {
        String file = "src/text.txt";
        vernama = new Vernama(file);
    }


    @Test
    public void shouldLoadDataFromFile(){
        //when
        String result = vernama.loadDataFromFile();

        //then
        Assert.assertEquals("kot Bengalski\n", result);
    }

    @Test
    public void shouldReturnEmptyResultWhenFileIsInvalid(){
        //given
        Vernama vernama = new Vernama("notExistingFile.txt");

        //when
        String result = vernama.loadDataFromFile();

        //then
        Assert.assertEquals("", result);

        List<Integer> jjj = Lists.newArrayList(1,2,3,4,5);

    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNPE(){
        //when
        vernama.changeToBinary(null);
    }

    @Test
    public void shouldTestChangeToBinary(){
        //given
        byte[] expected = { (byte) 75, (byte) 79, (byte) 84 };

        //when
        byte[] result = vernama.changeToBinary("KOT");

        //then
        Assert.assertEquals(expected.length, result.length);
        Assert.assertEquals(Arrays.toString(expected), Arrays.toString(result));
    }

    @Test
    public void shouldTestBinaryChangeToText(){
        //given
        String expected = "ko";
        //when
       String result = vernama.binaryToText(new int[] {0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1});

        //then
        Assert.assertEquals(expected,result);


    }

    @Test
    public void shouldTestChangeDecimalToBinary(){
        //given
        int[] expected = {0,1,0,1,0,1,0,0};
        //when
        int[] result = vernama.decimalToBinary(new byte[]{84});
        //then
        Assert.assertEquals(Arrays.toString(expected), Arrays.toString(result));

    }


    @Test
    public void shouldDecryptWhenPassedValueIsOK(){
        //given
        String expected = "ko";

        //when
        String result = vernama.decrypt(new int[] {0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1});

        //then
        Assert.assertEquals(expected, result);

    }

    @Test
    public void shouldDoDecodedValue(){
        //given
        int[] expected = {1, 1, 0 ,0};
        //when
       int[] result=  vernama.doDecoded(new int[]{1, 0, 0, 1}, new int[] {0, 1, 0, 1});
        //then
        Assert.assertEquals(Arrays.toString(expected), Arrays.toString(result));
    }

    @Test
    public void shouldDoEncodedValue() {
        //given
        int[] expected = {0, 1, 1, 1};
        //when
        int[] result = vernama.doEncode(new int[]{1, 0, 0, 0}, new int[]{1, 1, 1, 1});
        //then
        Assert.assertEquals(Arrays.toString(expected), Arrays.toString(result));
    }

    }
