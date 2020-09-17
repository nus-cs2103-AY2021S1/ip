import org.junit.jupiter.api.Test;

import rock.task.Todo;

public class TodoTest {
    @Test

    public void getDataFormatTest() {
        Todo todo = new Todo("test");
        assert(todo.getDataFormat().equals("T|test|false"));
    }
}
