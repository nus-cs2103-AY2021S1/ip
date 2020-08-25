package king;
import org.junit.jupiter.api.Test;
import tasks.Event;
import tasks.TaskList;
import tasks.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    TaskList tasklist = new TaskList();
    Storage storage = new Storage("data/test/parserTest.txt");

    @Test
    public void parseTodoTest(){
        String testCommand = "todo read book";
        Parser parser = new Parser(storage,tasklist);
        try{
            String actual = parser.parse(testCommand);
            ToDo todo = new ToDo("read book");
            String expected = UI.addItemChatBox(todo.toString(),tasklist.size());
            assertEquals(actual, expected);
        } catch (Exception e){
            fail();
        }
    }
    @Test
    public void parseEventTest(){
        String testCommand = "event test /at 2pm";
        Parser parser = new Parser(storage,tasklist);
        try{
            String actual = parser.parse(testCommand);
            Event testEvent = new Event("test","2pm");
            String expected = UI.addItemChatBox(testEvent.toString(),tasklist.size());
            assertEquals(actual, expected);
        } catch (Exception e){
            fail();
        }
    }
}
