package king;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import parser.Parser;
import storage.Storage;
import tasks.Event;
import tasks.TaskList;
import tasks.ToDo;
import ui.UI;

public class ParserTest {

    private TaskList tasklist = new TaskList();

    @Test
    public void parseToDoTest() {
        try {
            Storage storage = new Storage("data/test/parser/parserTest.txt");
            String testCommand = "todo read book";
            Parser parser = new Parser(storage, tasklist);
            String actual = parser.parse(testCommand);
            ToDo todo = new ToDo("read book");
            String expected = UI.addItemChatBox(todo.toString(), tasklist.size());
            assertEquals(actual, expected);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseEventTest() {
        try {
            Storage storage = new Storage("data/test/parser/parserTest.txt");
            String testCommand = "event test /at 2pm";
            Parser parser = new Parser(storage, tasklist);
            String actual = parser.parse(testCommand);
            Event testEvent = new Event("test", "2pm");
            String expected = UI.addItemChatBox(testEvent.toString(), tasklist.size());
            assertEquals(actual, expected);
        } catch (Exception e) {
            fail();
        }
    }
}
