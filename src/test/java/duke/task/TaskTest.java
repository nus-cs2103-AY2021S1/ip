package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void getStatusIconTest() {
        Task tDone = new Task("buy books", true);
        Task tNotDone = new Task("buy books", false);
        assertEquals("\u2713", tDone.getStatusIcon());
        assertEquals("\u2718", tNotDone.getStatusIcon());
    }

    @Test
    public void markAsDoneTest_success() {
        Task t = new Task("buy books");
        assertEquals("[✘] buy books", t.toString());
        t.markAsDone();
        assertEquals("[✓] buy books", t.toString());
    }

}
