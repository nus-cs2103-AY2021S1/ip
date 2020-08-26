import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void markTaskDone_InvalidIndex_SuccessMessage(){
        ArrayList<Task> testTasks = new ArrayList<>();
        testTasks.add(new Todo("return book"));
        TaskList tasktester = new TaskList(testTasks);
        String actual = "";
        try {
            tasktester.markTaskDone("done 0");
        } catch (DukeException e) {
            actual = e.getMessage();
        }
        String expected = "Sorry, I don't think that's a valid index...";
        assertEquals(expected, actual);
    }
}
