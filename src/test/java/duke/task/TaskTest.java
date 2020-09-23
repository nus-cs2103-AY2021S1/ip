package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskTest {

    private Task task;

    @BeforeEach
    void init() {
        task = new Task("hello world", false);
    }
    @Test
    public void markAsDone_marksATask_success() {
        String taskCompletedMessage = task.markAsDone();
        String str = "Nice! I've marked this task as done:\n[\u2714] hello world";
        assertEquals(str, taskCompletedMessage);
    }

    @Test
    public void formatStyling_formatTheStorageString_success() {
        String str = ",hello world,0,0\n";
        assertEquals(str, task.formatStyling());
    }
}
