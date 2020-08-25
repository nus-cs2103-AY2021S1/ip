package util;

import duke.DukeException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    Ui ui = new Ui();
    TaskList lst = new TaskList();
    Parser parser = new Parser(lst);
    
    @Test
    public void testParseForInvalidCommand() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse("awdawdwaed"));
    }

    @Test
    public void testParseForBlankCommand() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse(""));
    }

    @Test
    public void testParseForEmptyCommandDesc() {
        Exception e = assertThrows(DukeException.class, () -> parser.parse("TODO"));
        
        String expectedMessage = "Command description cannot be empty";
        String actualMessage = e.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testParseForInvalidDeadlineCommand() {
        Exception e = assertThrows(DukeException.class, () -> parser.parse("deadline buy bread by let's say March 3"));

        String expectedMessage = "Be sure to include a task description and date in the correct format.";
        String actualMessage = e.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testParseForInvalidEventCommand() {
        Exception e = assertThrows(DukeException.class, () -> parser.parse("event buy bread by let's say March 3"));

        String expectedMessage = "Be sure to include a task description and date in the correct format.";
        String actualMessage = e.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testParseForInvalidDoneCommand() {
        Exception e = assertThrows(DukeException.class, () -> parser.parse("done 0"));

        String expectedMessage = "You have no such task. Please check your task number.";
        String actualMessage = e.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testParseForInvalidDeleteCommand() {
        Exception e = assertThrows(DukeException.class, () -> parser.parse("done -1"));

        String expectedMessage = "You have no such task. Please check your task number.";
        String actualMessage = e.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
