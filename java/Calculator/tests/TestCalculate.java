package tests;

import calculator.Main;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class TestCalculate {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;

    @Test
    public void testCalculate() throws IOException {
        String filePrefix = "C:\\Users\\User\\IdeaProjects\\Calculator\\src\\tests\\";
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        String[] test1 = {filePrefix + "a.txt"};
        Main.main(test1);
        assertEquals("", errContent.toString());
        assertEquals("9.0\r\n", outContent.toString());
        errContent.reset();
        outContent.reset();

        String[] test2 = {filePrefix + "b.txt"};
        Main.main(test2);
        assertEquals("", errContent.toString());
        assertEquals("3.0\r\n", outContent.toString());
        errContent.reset();
        outContent.reset();
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}
