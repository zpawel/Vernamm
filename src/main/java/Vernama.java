import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Pawel
 */
public class Vernama {

    private static final String EMPTY_SPACE = "";
    private int[] keyBinary;
    private int[] valuesToEncode;
    private int[] coded;
    private String fileName;

    public Vernama(String fileNameEncoded) {
        this.fileName = fileNameEncoded;
    }

    public void startEncoding(){
        String loadedData = loadDataFromFile();

        System.out.println("this text will be coded: " + loadedData);

        byte[] newBinary = changeToBinary(loadedData);

        valuesToEncode = decimalToBinary(newBinary);

        keyBinary = getRandom(newBinary.length * 8);

        int[] result = doEncode(valuesToEncode, keyBinary);

        String encoded = binaryToText(result);

        byte[] encodedToBinary = encoded.getBytes(StandardCharsets.UTF_8 );
        coded = decimalToBinary(encodedToBinary);

        int[] decodedResult = doDecoded(result, keyBinary);

        decrypt(decodedResult);
    }

    /**
     * @param decodedResult
     * @return String only for test purposes
     */
    protected String decrypt(int[] decodedResult) {
        String decoded = EMPTY_SPACE;
        char nextCharr;
        String decodedValues = Arrays.toString(decodedResult).replaceAll("\\[|\\]|,|\\s", EMPTY_SPACE);

        for (int i = 0; i <= decodedValues.length() - 8; i += 8) {
            nextCharr = (char) Integer.parseInt(decodedValues.substring(i, i + 8), 2);
            decoded += nextCharr;
        }
        System.out.println("decoded text: " + decoded);
        return decoded;
    }

    protected String binaryToText(int[] result) {
        String codedValues = Arrays.toString(result).replaceAll("\\[|\\]|,|\\s", EMPTY_SPACE);
        String encoded = EMPTY_SPACE;
        char nextChar;

        for (int i = 0; i <= codedValues.length() - 8; i += 8) {
            nextChar = (char) Integer.parseInt(codedValues.substring(i, i + 8), 2);
            encoded += nextChar;
        }
        System.out.println("encoded text: " + encoded);
        return encoded;

    }

    protected byte[] changeToBinary(String loadedData) {
        byte[] toBinaryString = loadedData.getBytes(StandardCharsets.UTF_8);

        byte[] newBinary = new byte[toBinaryString.length];
        System.arraycopy( toBinaryString, 0, newBinary, 0, toBinaryString.length );
        return newBinary;
    }

    protected int[] doDecoded(int[] valuesDecode, int[] keys) {
        int[] resultFinish = new int[valuesDecode.length];
        for (int i = 0; i < valuesDecode.length; i++) {
            if (valuesDecode[i] != keys[i]) {
                resultFinish[i] = 1;
            } else {
                resultFinish[i] = 0;
            }
        }
        return resultFinish;
    }

    protected int[] doEncode(int[] valuesEncode, int[] keys) {

        int[] result = new int[valuesEncode.length];
        for (int i = 0; i < valuesEncode.length; i++) {
            if (valuesEncode[i] != keys[i]) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }
        }
        return result;
    }

    protected int[] decimalToBinary(byte[] toBinaryString) {
        int j = 0;
        StringBuilder builder = new StringBuilder();
        int[] test = new int[toBinaryString.length * 8];
        for (byte b : toBinaryString) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                int binaryValue = (val & 128) == 0 ? 0 : 1;
                val <<= 1;
                builder.append(binaryValue);
                test[j] = binaryValue;
                j++;
            }
        }
        return test;

    }

    protected String loadDataFromFile() {
        File file = new File(fileName);
        String read = EMPTY_SPACE;
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                read = read + scanner.nextLine() + "\n";
            }

        } catch (FileNotFoundException e) {
            System.out.println("Missing file to load");
        }
        return read;
    }

    protected int[] getRandom(int length) {

        double p = 15485857;
        double q = 15485857;
        double n = p * q;
        double seed = 10;
        double[] x = new double[length];
        int[] z = new int[length];
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                x[i] = (seed * seed) % n;
            } else {
                x[i] = (x[i - 1] * x[i - 1]) % n;
                z[i] = (int) x[i] % 2;
            }
        }
        return z;
    }

    public int[] getKeyBinary() {
        return keyBinary;
    }

    public void setKeyBinary(int[] keyBinary) {
        this.keyBinary = keyBinary;
    }

    public int[] getValuesToEncode() {
        return valuesToEncode;
    }

    public void setValuesToEncode(int[] valuesToEncode) {
        this.valuesToEncode = valuesToEncode;
    }

    public int[] getCoded() {
        return coded;
    }

    public void setCoded(int[] coded) {
        this.coded = coded;
    }



}
