// unable to import fail for fail()
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    
    @Test
    public void stringToIntConversion_includeInteger_success() throws DukeException {
        assertEquals(1, Parser.parseDelete("delete 2"));
        assertEquals(6, Parser.parseDelete("delete 7"));
    }
    
    @Test
    public void stringToIntConversion_excludeInteger_exceptionThrown() {
        try {
            assertEquals(1, Parser.parseDelete("delete"));
            // fail();
        } catch (DukeException e) {
            assertEquals("ERROR: Specify the task number which you want to delete.", e.getMessage());
        }
    }
    
    @Test
    public void stringToTodoConversion_includeDescription_success() throws DukeException {
        assertEquals("[T][✘] read book", Parser.parseTodo("todo read book").toString());
        assertEquals("[T][✘] borrow book", Parser.parseTodo("todo borrow book").toString());
    }

    @Test
    public void stringToTodoConversion_excludeDescription_exceptionThrown() {
        try {
            assertEquals(new Todo(""), Parser.parseTodo("todo"));
            // fail();
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }
}
