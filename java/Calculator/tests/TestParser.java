package tests;

import exceptions.ParserException;

import org.junit.Test;
import parsers.CommandType;
import parsers.Parser;

import static org.junit.Assert.*;


public class TestParser {
    @Test
    public void testParse() throws ParserException {
        Parser pars = new Parser();

        assertNull(pars.parse("").getType());
        assertNull(pars.parse("").getArgs());

        assertNull(pars.parse("#").getType());
        assertNull(pars.parse("#").getArgs());

        assertEquals(pars.parse("POP").getType(), CommandType.POP);
        assertTrue(pars.parse("POP").getArgs().isEmpty());

        assertEquals(pars.parse("PUSH 4").getType(), CommandType.PUSH);
        assertEquals(pars.parse("PUSH 4").getArgs().size(), 1);
        assertEquals(pars.parse("PUSH 4").getArgs().get(0), "4");
        assertEquals(pars.parse("PUSH a").getType(), CommandType.PUSH);
        assertEquals(pars.parse("PUSH a").getArgs().size(), 1);
        assertEquals(pars.parse("PUSH a").getArgs().get(0), "a");

        assertEquals(pars.parse("+").getType(), CommandType.PLUS);
        assertTrue(pars.parse("+").getArgs().isEmpty());

        assertEquals(pars.parse("-").getType(), CommandType.MINUS);
        assertTrue(pars.parse("-").getArgs().isEmpty());

        assertEquals(pars.parse("*").getType(), CommandType.MUL);
        assertTrue(pars.parse("*").getArgs().isEmpty());

        assertEquals(pars.parse("/").getType(), CommandType.DIV);
        assertTrue(pars.parse("/").getArgs().isEmpty());

        assertEquals(pars.parse("SQRT").getType(), CommandType.SQRT);
        assertTrue(pars.parse("SQRT").getArgs().isEmpty());

        assertEquals(pars.parse("PRINT").getType(), CommandType.PRINT);
        assertTrue(pars.parse("PRINT").getArgs().isEmpty());

        assertEquals(pars.parse("DEFINE a 5").getType(), CommandType.DEFINE);
        assertEquals(pars.parse("DEFINE a 5").getArgs().size(), 2);
        assertEquals(pars.parse("DEFINE a 5").getArgs().get(0), "a");
        assertEquals(pars.parse("DEFINE a 5").getArgs().get(1), "5");
    }
}
