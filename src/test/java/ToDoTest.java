import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void markAsDone_success() {
        ToDo task = new ToDo("testing");
        assertEquals("\u2718", task.getStatusIcon());
        task = task.markAsDone();
        assertEquals("\u2713", task.getStatusIcon());
    }

    @Test
    public void toString_success() {
        ToDo task = new ToDo("study at home");
        assertEquals("[T][" + "\u2718" + "] " + "study at home", task.toString());

        ToDo doneTask = new ToDo("study at home", true);
        assertEquals("[T][" + "\u2713" + "] " + "study at home", doneTask.toString());
    }

    @Test
    public void toTxtFileformat_success() {
        ToDo task = new ToDo("study at home");
        assertEquals("T | 0 | study at home", task.toTxtFileFormat());

        ToDo doneTask = new ToDo("study at home", true);
        assertEquals("T | 1 | study at home", doneTask.toTxtFileFormat());
    }

}
