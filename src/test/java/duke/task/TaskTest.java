package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void getStatusIconTest() {
        Task tDone = new Task("buy books", true);
        Task tNotDone = new Task("buy books", false);
        assertEquals("\u2713", tDone.getStatusIcon());
        assertEquals("\u2718", tNotDone.getStatusIcon());
    }

    @Test
    public void markAsDoneTest_Success() {
        Task t = new Task("buy books");
        assertEquals("[✘] buy books", t.toString());
        t.markAsDone();
        assertEquals("[✓] buy books", t.toString());
    }

}
