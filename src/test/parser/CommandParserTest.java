package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import duke.exception.DukeIoException;
import duke.parser.FileParser;
import duke.task.Todo;

public class CommandParserTest {


    @Test
    @DisplayName("parseLine called on '[T],, 1,, todo'")
    public void parseLineFromFile_validTodo_todoReturned() {
        try {
            assertEquals("[T] [\u2713] todo", FileParser.parseLine("[T],, 1,, todo").toString());
        } catch (DukeIoException e) {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("convertTask called on a todo called todo")
    public void convertTaskForFile_validTodo_todoReturned() {
        Todo todo = new Todo("todo", "1");
        assertEquals("[T],, 1,, todo,, \n", FileParser.convertTaskToSaveFormat(todo));
    }


}
