package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextParserTest {
    @Test
    public void parseCommand_givenUnknownCommand_thenAssertThrowsException() {
        TextParser textParser = new TextParser();
        assertEquals(Command.ERROR, textParser.parseCommand("hello"));
    }

    @Test
    public void parseCommand_givenSeperationToken_AssertTokenSeperatesString() {
        TextParser textParser = new TextParser();
        String test = "todo";
        Command expectedResult = Command.TODO;
        assertEquals(expectedResult, textParser.parseCommand(test));
    }


}
