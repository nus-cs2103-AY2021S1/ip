package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.java.seedu.duke.Parser;
import main.java.seedu.duke.todo.Task;



public class ParserTest {
    @Test
    public void parseTest() {
        String input = "todo borrow book";
        Task newTask = Parser.parseTask(input);
        String description = newTask.getDescription();
        String expected = "borrow book";
        assertEquals(expected, description);
    }

    @Test void standardizeTimeStringTest() {
        String input = "2000/ 12/ 20";
        String output = Parser.standardizeTimeString(input);
        String expected = "2000-12-20";
        assertEquals(expected, output);
    }
}
