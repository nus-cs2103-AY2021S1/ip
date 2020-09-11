import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.task.Task;

public class TaskTest {

    @Test
    public void containsKeywordTest1() {
        String keyword = "read";
        Task newTask = new Task("read book");
        assertTrue(newTask.containsKeyWord(keyword));
    }

    @Test
    public void containsKeywordTest2() {
        String keyword = "let";
        Task newTask = new Task("read book");
        assertFalse(newTask.containsKeyWord(keyword));
    }
}
