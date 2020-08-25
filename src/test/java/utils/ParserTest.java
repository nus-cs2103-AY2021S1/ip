package utils;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseMainCommand_validCommand_firstWord() {
        assertEquals(Parser.parseMainCommand("done 3"), "done");
    }

    @Test
    public void parseMainCommand_invalidCommand_empty() {
        assertEquals(Parser.parseMainCommand(""), "");
    }

    @Test
    public void parseParameters_oneParameters_twoMappings() {
        HashMap<String, String> expected = new HashMap<>();
        expected.put("argument", "abc");
        expected.put("by", "def");
        assertEquals(Parser.parseParameters("deadline abc /by def"), expected);
    }

    @Test
    public void parseParameters_twoParameters_threeMappings() {
        HashMap<String, String> expected = new HashMap<>();
        expected.put("argument", "abc");
        expected.put("by", "def");
        expected.put("at", "ghi");
        assertEquals(Parser.parseParameters("deadline abc /by def /at ghi"), expected);
    }
}
