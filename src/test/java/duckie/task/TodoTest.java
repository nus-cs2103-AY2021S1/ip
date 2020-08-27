package duckie.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todo_inputCommand_todoString() {
        Task task = new Todo("borrow books");
        assertEquals("[T][✘] borrow books", task.toString());
    }

    @Test
    public void todoMarkDone_inputCommand_todoString() {
        Task task = new Todo("borrow books");
        task.checked();
        assertEquals("[T][✔] borrow books", task.toString());
    }
}
