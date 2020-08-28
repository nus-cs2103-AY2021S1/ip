package parser;

import org.junit.jupiter.api.Test;
import dukeexception.DukeException;
import storage.Storage;
import tasklist.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void handleTodoExceptionTest() {
        boolean exceptionThrown = false;
        try {
            Storage storage = new Storage("storage_test.txt");
            TaskList tasks = new TaskList(storage);
            Parser parser = new Parser(tasks);
            String testing = parser.handleTodo("todo");
        } catch (DukeException e) {
            exceptionThrown = true;
        }
        assertEquals(true, exceptionThrown);
    }

}
