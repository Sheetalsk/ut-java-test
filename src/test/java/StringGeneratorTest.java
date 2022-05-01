import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringGeneratorTest {

    @Test
    public void shouldGenerateXLinesOfStrings() {
        int expectedNumberOfLines = 319;

        byte[] randomStrings = StringGenerator.generateRandomStrings(expectedNumberOfLines);

        String outputString = new String(randomStrings);

        int actualNumberOfLines = outputString.split("\n").length;
        Assertions.assertEquals(expectedNumberOfLines, actualNumberOfLines);
    }

    @Test
    public void shouldGenerateUniqueStrings() {
        int expectedNumberOfLines = 3;
        byte[] randomStrings = StringGenerator.generateRandomStrings(expectedNumberOfLines);

        String outputString = new String(randomStrings);
        String[] generatedStrings = outputString.split("\n");

        String firstLine = generatedStrings[0];
        String secondLine = generatedStrings[1];
        String thirdLine = generatedStrings[2];

        Assertions.assertNotEquals(firstLine, secondLine);
        Assertions.assertNotEquals(firstLine, thirdLine);
        Assertions.assertNotEquals(secondLine, thirdLine);
    }

    @Test
    public void shouldGenerateStringsWithEachLineOfHundredCharacters() {
        int expectedNumberOfLines = 2;

        byte[] randomStrings = StringGenerator.generateRandomStrings(expectedNumberOfLines);

        String outputString = new String(randomStrings);
        String[] generatedStrings = outputString.split("\n");

        String firstLine = generatedStrings[0];
        String secondLine = generatedStrings[1];

        Assertions.assertEquals(firstLine.length(), 100);
        Assertions.assertEquals(secondLine.length(), 100);
    }

    @Test
    public void shouldNotGenerateStringWhenXNumberOfLinesIsZero() {
        int expectedNumberOfLines = 0;

        byte[] randomStrings = StringGenerator.generateRandomStrings(expectedNumberOfLines);

        String outputString = new String(randomStrings);

        Assertions.assertEquals(outputString, "");
        Assertions.assertEquals(0, randomStrings.length);
    }


}
