package parser;

import DukeException.DukeException;
import org.junit.jupiter.api.Test;
import storage.Storage;
import tasks.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void handleTodoExceptionTest() {
        boolean exceptionThrown = false;
        try {
            Storage storage = new Storage("storage_test.txt");
            TaskList tasks = new TaskList(storage);
            Parser parser = new Parser(tasks);
            parser.handleTodo("todo");
        } catch (DukeException e) {
            exceptionThrown = true;
        }
        assertEquals(true, exceptionThrown);
    }

}
