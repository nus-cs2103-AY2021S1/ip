import duke.Todo;
import duke.command.ListCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void validTodo(){
        Todo dummyTodo = new Todo("get out of Hospital");
        assertEquals("[T]" + "[✘]" + " get out of Hospital", dummyTodo.toString());
    }

    @Test
    public void invalidTodo(){
        Todo dummyTodo = new Todo("this task is completed", true);
        assertEquals("[T]" + "[✓] this task is completed", dummyTodo.toString());
    }
}