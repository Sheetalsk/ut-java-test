import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RandomStringFileGeneratorTest {

    private static final String FILE_PATH = "test.txt";

    @AfterEach
    private void removeTestFileAfterEachTest() {
        File file = new File(FILE_PATH);
        file.delete();
    }

    @Test
    public void shouldGenerateTextFile() throws IOException {
        String filePath = "test.txt";
        RandomStringFileGenerator.generateFileWithRandomStrings(3, FILE_PATH);
        Path path = Paths.get(filePath);
        Assertions.assertEquals(true, Files.exists(path));

    }

    @Test
    public void shouldGenerateUniqueStringsInFile() throws IOException {
        RandomStringFileGenerator.generateFileWithRandomStrings(3, FILE_PATH);

        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));

        String firstLine = br.readLine();
        String secondLine = br.readLine();
        String thirdLine = br.readLine();

        Assertions.assertNotEquals(firstLine, secondLine);
        Assertions.assertNotEquals(firstLine, thirdLine);
        Assertions.assertNotEquals(secondLine, thirdLine);
    }

    @Test
    public void shouldGenerateUniqueStringsInFile_IncreaseInsertedCharCountInEachLine() throws IOException {
        RandomStringFileGenerator.generateFileWithRandomStrings(3, FILE_PATH);

        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));

        String firstLine = br.readLine();
        String secondLine = br.readLine();
        String thirdLine = br.readLine();

        String firstChar = "a";
        String endChar = "z";

        Assertions.assertEquals(firstChar.repeat(1) + endChar.repeat(99), firstLine);
        Assertions.assertEquals(firstChar.repeat(2) + endChar.repeat(98), secondLine);
        Assertions.assertEquals(firstChar.repeat(3) + endChar.repeat(97), thirdLine);
    }

    @Test
    public void shouldGenerateUniqueStringsInFile_RepeatChars100TimesIn100Line() throws IOException {
        RandomStringFileGenerator.generateFileWithRandomStrings(100, FILE_PATH);

        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));

        String firstChar = "a";
        String lastLine = null;
        String nextLine;
        while ((nextLine = br.readLine()) != null) {
            lastLine = nextLine;
        }
        Assertions.assertEquals(firstChar.repeat(100), lastLine);
    }

    @Test
    public void shouldGenerateUniqueStringsInFile_FirstCharOf101LinesShouldChange() throws IOException {
        RandomStringFileGenerator.generateFileWithRandomStrings(101, FILE_PATH);

        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));

        String endChar = "a";

        String lastLine = null;
        String nextLine;
        while ((nextLine = br.readLine()) != null) {
            lastLine = nextLine;
        }

        Assertions.assertEquals("b".repeat(1) + endChar.repeat(99), lastLine);
    }


    @Test
    public void shouldGenerateXLinesOfStringsInFile() throws IOException {
        int expectedNumberOfLines = 319;

        RandomStringFileGenerator.generateFileWithRandomStrings(expectedNumberOfLines, FILE_PATH);

        int actualNumberOfLines = (int) Files.lines(Paths.get(FILE_PATH)).count();
        Assertions.assertEquals(expectedNumberOfLines, actualNumberOfLines);
    }

    @Test
    public void shouldGenerateStringsWithEachLineOfHundredCharactersInFile() throws IOException {
        int numberOfLines = 2;

        RandomStringFileGenerator.generateFileWithRandomStrings(numberOfLines, FILE_PATH);

        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String firstLine = br.readLine();
        String secondLine = br.readLine();

        Assertions.assertEquals(100, firstLine.length());
        Assertions.assertEquals( 100, secondLine.length());
    }

    @Test
    public void shouldNotGenerateFileWhenXNumberOfLinesIsZero() throws IOException {
        int numberOfLines = 0;

        RandomStringFileGenerator.generateFileWithRandomStrings(numberOfLines, FILE_PATH);

        Path path = Paths.get(FILE_PATH);

        Assertions.assertFalse(Files.exists(path));
    }

    @Test
    public void shouldThrowExceptionWithInvalidFilePath() {

        Assertions.assertThrows(FileNotFoundException.class, () -> RandomStringFileGenerator.generateFileWithRandomStrings(2, "/ABC/DEF/"));
    }

}

