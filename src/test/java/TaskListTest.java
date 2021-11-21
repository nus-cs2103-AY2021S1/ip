import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class TaskListTest {

    @Test
    void add() throws BlankTaskException {
        TaskList taskList = new TaskList(new ArrayList<>());
        try {
            taskList.add(TaskType.E, "lorem ipsum",
                    LocalDate.of(2020, 12, 12), LocalTime.of(12, 12));
        } catch (BlankTaskException e) {
            fail();
            e.printStackTrace();
        }
        assertArrayEquals(new Task[] { new Event("lorem ipsum",
                        LocalDate.of(2020, 12, 12), LocalTime.of(12, 12)) },
                taskList.getList().toArray());
    }
}