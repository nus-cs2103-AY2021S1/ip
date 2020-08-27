package tasks;

import exceptions.DukeDateTimeException;
import org.junit.jupiter.api.Test;
import tasks.Command;
import tasks.TextParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TextParserTest {
    @Test
    public void whenGivenUnknownCommand_thenAssertThrowsException(){
        TextParser textParser = new TextParser();
        assertEquals(Command.error,textParser.parseCommand("hello"));
    }
    @Test
    public void whenGivenSeperationToken_AssertTokenSeperatesString(){
        TextParser textParser = new TextParser();
        String test = "todo";
        Command expectedResult = Command.todo;
        assertEquals(expectedResult, textParser.parseCommand(test));
    } 
    
    
}
