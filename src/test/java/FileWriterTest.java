
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWriterTest {

    private static final String FILE_PATH = "out.txt";

    @Test
    public void shouldCreateAFile() throws IOException {

        byte[] input = new byte[]{'a', 'b', 'c'};
        FileWriter.write(input, FILE_PATH);

        Path path = Paths.get(FILE_PATH);

        Assertions.assertEquals(true, Files.exists(path));
    }

    @Test
    public void shouldSaveCorrectInputDataInFile() throws IOException {

        byte[] input = new byte[]{'a', 'b', '\n', 'c', 'd', '\n'};
        FileWriter.write(input, FILE_PATH);

        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));

        String firstLine = br.readLine();
        String secondLine = br.readLine();

        Assertions.assertEquals("ab", firstLine);
        Assertions.assertEquals("cd", secondLine);
    }

    @Test
    public void shouldNotCreateFileWithNullInput() throws IOException {

        FileWriter.write(null, FILE_PATH);

        Path path = Paths.get(FILE_PATH);
        Assertions.assertEquals(true, Files.exists(path));
    }

    @Test
    public void shouldNotCreateFileWithNullFilePath() throws IOException {

        byte[] input = new byte[]{'a', 'b', '\n', 'c', 'd', '\n'};

        FileWriter.write(input, null);

        Path path = Paths.get(FILE_PATH);
        Assertions.assertEquals(true, Files.exists(path));
    }

    @Test
    public void shouldThrowExceptionWithInvalidFilePath() throws IOException {

        byte[] input = new byte[]{'a', 'b', 'c'};

        Assertions.assertThrows(FileNotFoundException.class, () -> FileWriter.write(input, "/ABC/XYZ"));
    }
}
