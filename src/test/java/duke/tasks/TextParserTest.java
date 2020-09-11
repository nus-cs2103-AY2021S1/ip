package duke.tasks;

import duke.command.CommandHelp;
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
        assertEquals(CommandHelp.ERROR, textParser.parseHelpCommand("hello"));
    }

    /**
     * Tests that when a expected command is given, the correct enumeration is returned.
     */
    @Test
    public void parseCommand_givenSeperationToken_AssertTokenSeperatesString() {
        TextParser textParser = new TextParser();
        String test = "todo";
        CommandHelp expectedResult = CommandHelp.TODO;
        assertEquals(expectedResult, textParser.parseHelpCommand(test));
    }
}
