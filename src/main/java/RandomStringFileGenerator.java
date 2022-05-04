import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class RandomStringFileGenerator {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();

        generateFileWithRandomStrings(x, "out.txt");
    }

    public static void generateFileWithRandomStrings(int x, String filePath) throws IOException {

        if (x != 0 && filePath != null) {

            byte[] input = new byte[100];
            Arrays.fill(input, (byte) 'z');
            FileOutputStream stream = new FileOutputStream(filePath);
            byte insertChar = (byte) 'a';

            int insertCharIndex = 0;
            for (int i = 0; i < x; i++) {
                if (insertCharIndex == 100) {
                    insertCharIndex = 0;
                    insertChar++;
                }
                input[insertCharIndex] = insertChar;
                stream.write(input);

                if (i != x - 1) {
                    stream.write((byte) '\n');
                }
                insertCharIndex++;
            }
            stream.close();
        }
    }
}
