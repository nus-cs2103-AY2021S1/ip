package tasks;

import exceptions.DukeDateTimeException;
import org.junit.jupiter.api.Test;
import tasks.Command;
import tasks.TextParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Class to teast TextParser and all public test
 */
public class TextParserTest {
    /**
     * Tests TextParser and ensures that it returns a error if a invalid command is given as input
     */
    @Test
    public void whenGivenUnknownCommand_thenAssertThrowsException() {
        TextParser textParser = new TextParser();
        assertEquals(Command.error,textParser.parseCommand("hello"));
    }

    /**
     * Tests that when a expected command is given, the correct enumeration is returned.
     */
    @Test
    public void whenGivenSeperationToken_AssertTokenSeperatesString() {
        TextParser textParser = new TextParser();
        String test = "todo";
        Command expectedResult = Command.todo;
        assertEquals(expectedResult, textParser.parseCommand(test));
    } 
    
    
}
