package jeda00.adventura.core.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CommandParserTest {

    @Test
    public void itProperlyParsesArguments() {
        CommandParser it = new CommandParser("a b c");

        assertEquals("a", it.getCommand());
        assertEquals("b", it.getArguments().get(0));
        assertEquals("c", it.getArguments().get(1));
        assertEquals(2, it.getArguments().size());
    }

}
