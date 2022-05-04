import java.io.FileOutputStream;
import java.io.IOException;

public final class FileWriter {

    private FileWriter() {
    }

    public static void write(byte[] input, String filePath) throws IOException {
        if (input != null && filePath != null) {
            FileOutputStream stream = new FileOutputStream(filePath);
            stream.write(input);
            stream.close();
        }
    }

}
