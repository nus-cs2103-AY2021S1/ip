package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toString_createNewTodoTask_todoTaskInfoReturned() {
        assertEquals("[T][✘] borrow books",
                new Todo("borrow books", false).toString());
    }
}
