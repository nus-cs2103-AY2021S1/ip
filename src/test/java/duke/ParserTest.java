package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void stringSplit_getWords_success() {
        assertEquals("Hello", Parser.stringSplit("Hello world", " ")[0]);
        assertEquals("world", Parser.stringSplit("Hello world", " ")[1]);
    }

    @Test
    public void stringSplitLimit_splitTwoString_success() {
        assertEquals("world how are you",
                Parser.stringSplitLimit("Hello world how are you", " ",2)[1]);
    }

    @Test
    public void getIndex_parseStringToInt_success() {
        assertEquals(0, Parser.getIndex("done 1"));
    }
}
