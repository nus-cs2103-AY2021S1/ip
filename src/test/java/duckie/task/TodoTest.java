package duckie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void todo_inputCommand_todoString() {
        Task task = new Todo("borrow books");
        assertEquals("[T][✘] borrow books", task.toString());
    }

    @Test
    public void todoMarkDone_inputCommand_todoString() {
        Task task = new Todo("borrow books");
        task.markDone();
        assertEquals("[T][✔] borrow books", task.toString());
    }
}
