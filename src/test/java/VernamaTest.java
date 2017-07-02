import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;


public class VernamaTest {

    private Vernama vernama;

    @Before
    public void setUp() {
        String file = "src/text.txt";
        vernama = new Vernama(file);
    }


    @Test
    public void shouldLoadDataFromFile() {
        //when
        String result = vernama.loadDataFromFile();

        //then
        Assert.assertEquals("kot Bengalski\n", result);
    }

    @Test
    public void shouldReturnEmptyResultWhenFileIsInvalid() {
        //given
        Vernama vernama = new Vernama("notExistingFile.txt");

        //when
        String result = vernama.loadDataFromFile();

        //then
        Assert.assertEquals("", result);

    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNPE() {
        //when
        vernama.changeToBinary(null);
    }

    @Test
    public void shouldTestChangeToBinary() {
        //given
        byte[] expected = {(byte) 75, (byte) 79, (byte) 84};

        //when
        byte[] result = vernama.changeToBinary("KOT");

        //then
        Assert.assertEquals(expected.length, result.length);
        Assert.assertEquals(Arrays.toString(expected), Arrays.toString(result));
    }

    @Test
    public void shouldTestBinaryChangeToText() {
        //given
        String expected = "ko";

        //when
        String result = vernama.binaryToText(new int[]{0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1});

        //then
        Assert.assertEquals(expected, result);


    }

    @Test
    public void shouldTestChangeDecimalToBinary() {
        //given
        int[] expected = {0, 1, 0, 1, 0, 1, 0, 0};

        //when
        int[] result = vernama.decimalToBinary(new byte[]{84});

        //then
        Assert.assertEquals(Arrays.toString(expected), Arrays.toString(result));

    }


    @Test
    public void shouldDecryptWhenPassedValueIsOK() {
        //given
        String expected = "ko";

        //when
        String result = vernama.decrypt(new int[]{0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1});

        //then
        Assert.assertEquals(expected, result);

    }

    @Test
    public void shouldDoDecodedValue() {
        //given
        int[] expected = {1, 1, 0, 0};

        //when
        int[] result = vernama.doDecoded(new int[]{1, 0, 0, 1}, new int[]{0, 1, 0, 1});

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

    @Test
    public void duplicateList(){
        List lista = new LinkedList();
        lista.add(1);
        lista.add(1);
        lista.add(2);
        lista.add(1);
        lista.add(2);
        lista.add(1);
        lista.add(1);
        lista.add(3);
        Set sjadj = new HashSet<>();
        sjadj.addAll(lista);

        sjadj.forEach(m -> System.out.print(m));


        for(int i=0; i<lista.size(); i++){
            for(int j=i+1; j<lista.size(); j++){
                if (lista.get(i)== lista.get(j)){
                    lista.remove(j);
                    j--;
                }

            }
            System.out.println(lista.get(i));
        }

    }

}
