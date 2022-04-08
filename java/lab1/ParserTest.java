package nsu.fit.oop.java;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    String filePrefix = "C:\\Users\\User\\IdeaProjects\\laba1\\src\\nsu\\fit\\oop\\java\\";

    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setErr(new PrintStream(errContent));
    }
    @Test
    void testParse() throws IOException {
        Main.main(new String[]{filePrefix + "a.txt", filePrefix + "q.csv"});
        Scanner scan = new Scanner(filePrefix + "q.csv");
        Scanner veriscan = new Scanner(filePrefix + "b.csv");
        scan.nextLine();
        veriscan.nextLine();
        while (scan.hasNext())
            assertEquals(scan.nextLine(), veriscan.nextLine());
    }

    @Test
    void nullFileTest() throws IOException {
        Parser pars = new Parser(null);
        pars.parse();
        assertEquals("Input file is NULL\r\n", errContent.toString());
        errContent.reset();

        Parser pars1 = new Parser(filePrefix + "a.txt", null);
        pars1.parse();
        pars1.printing();
        assertEquals("Output file is NULL\r\n", errContent.toString());
        errContent.reset();
    }

    @AfterEach
    public void restoreStreams() {
        System.setErr(originalErr);
    }
}
