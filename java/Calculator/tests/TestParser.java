package tests;

import exceptions.ParserException;

import org.junit.Test;
import parsers.Parser;

import static org.junit.Assert.*;


public class TestParser {
    @Test
    public void testParse() throws ParserException {
        Parser pars = new Parser();

        assertEquals(pars.parse("").getType(), "COMMENT");
        assertNull(pars.parse("").getArgs());

        assertEquals(pars.parse("#").getType(), "COMMENT");
        assertNull(pars.parse("#").getArgs());

        assertEquals(pars.parse("POP").getType(), "POP");
        assertTrue(pars.parse("POP").getArgs().isEmpty());

        assertEquals(pars.parse("PUSH 4").getType(), "PUSH");
        assertEquals(pars.parse("PUSH 4").getArgs().size(), 1);
        assertEquals(pars.parse("PUSH 4").getArgs().get(0), "4");
        assertEquals(pars.parse("PUSH a").getType(), "PUSH");
        assertEquals(pars.parse("PUSH a").getArgs().size(), 1);
        assertEquals(pars.parse("PUSH a").getArgs().get(0), "a");

        assertEquals(pars.parse("+").getType(), "+");
        assertTrue(pars.parse("+").getArgs().isEmpty());

        assertEquals(pars.parse("-").getType(), "-");
        assertTrue(pars.parse("-").getArgs().isEmpty());

        assertEquals(pars.parse("*").getType(), "*");
        assertTrue(pars.parse("*").getArgs().isEmpty());

        assertEquals(pars.parse("/").getType(), "/");
        assertTrue(pars.parse("/").getArgs().isEmpty());

        assertEquals(pars.parse("SQRT").getType(), "SQRT");
        assertTrue(pars.parse("SQRT").getArgs().isEmpty());

        assertEquals(pars.parse("PRINT").getType(), "PRINT");
        assertTrue(pars.parse("PRINT").getArgs().isEmpty());

        assertEquals(pars.parse("DEFINE a 5").getType(), "DEFINE");
        assertEquals(pars.parse("DEFINE a 5").getArgs().size(), 2);
        assertEquals(pars.parse("DEFINE a 5").getArgs().get(0), "a");
        assertEquals(pars.parse("DEFINE a 5").getArgs().get(1), "5");
    }
}
