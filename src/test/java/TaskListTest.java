import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void taskList_emptyDescription() throws DukeException {
        try {

            TaskList.taskStorage = new ArrayList<>();
            TaskList.write("borrow book", "todo", new DateAndTime());
            ArrayList<Task> exp = new ArrayList<>(1);
            exp.add(new ToDo("borrow book"));
            assertEquals(TaskList.taskStorage.toString(), exp.toString());

        } catch (DukeException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

}
