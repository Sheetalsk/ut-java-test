import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public final class StringGenerator {

    private StringGenerator() {
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        FileWriter.write(StringGenerator.generateRandomStrings(n), "out.txt");
    }

    public static byte[] generateRandomStrings(int times) {
        byte[] c = new byte[times * 101];
        Arrays.fill(c, (byte) 'z');

        int index = 0;
        int newLineIndex = 100;
        int insertCharIndex = 0;
        byte insertChar = 'a';
        int insertCharCount = 0;
        while (index < c.length) {

            if (index == insertCharIndex) {
                c[index] = insertChar;
                insertCharIndex += 102;
                insertCharCount++;
            }

            if (insertCharCount == 100) {
                insertChar++;
                insertCharCount = 0;
                insertCharIndex = index + 2;
            }

            if (index == newLineIndex) {
                c[index] = '\n';
                newLineIndex += 101;
            }
            index++;
        }
        return c;
    }
}
