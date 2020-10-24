package duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void addTodo() throws IOException, DukeException {
        Duke duke = new Duke("data/duke.txt");
        assertEquals(0, duke.tasks.taskList.size());
        duke.user_input_handler("todo homework", false);
        assertEquals( 1, duke.tasks.taskList.size());
    }

    @Test
    public void addAndDeleteTodo() throws IOException, DukeException {
        Duke duke = new Duke("data/duke.txt");
        duke.user_input_handler("todo homework", false);
        assertEquals( 1, duke.tasks.taskList.size());
        duke.user_input_handler("delete 1", false);
        assertEquals( 0, duke.tasks.taskList.size());
    }
}
