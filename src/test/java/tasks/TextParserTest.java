package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class to teast TextParser and all public test
 */
public class TextParserTest {
    /**
     * Tests TextParser and ensures that it returns a error if a invalid command is given as input
     */
    @Test
    public void parseCommand_givenUnknownCommand_thenAssertThrowsException() {
        TextParser textParser = new TextParser();
        assertEquals(Command.ERROR, textParser.parseCommand("hello"));
    }

    /**
     * Tests that when a expected command is given, the correct enumeration is returned.
     */
    @Test
    public void parseCommand_givenSeperationToken_AssertTokenSeperatesString() {
        TextParser textParser = new TextParser();
        String test = "todo";
        Command expectedResult = Command.TODO;
        assertEquals(expectedResult, textParser.parseCommand(test));
    }
}
