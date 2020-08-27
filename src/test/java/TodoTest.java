//package duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test

    public void getTypeTest() {
        Todo todo = new Todo("test");
        assert(todo.getType() == "T");
    }

    public void getStatusTest() {
        Todo todo = new Todo("test");
        assert(todo.getStatus().substring(0, 2).equals("[T]"));
    }
}
