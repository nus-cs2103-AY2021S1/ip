package duke;


import duke.component.DukeException;
import duke.task.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest_newTask_stringOutput() {
        try {
            Deadline deadlineTask = new Deadline("get 6 abs", "2040/05/21 13:43");
            assertEquals("[D][✘] get 6 abs (by: May 21 2040 13:43:00)", deadlineTask.toString());
        } catch (DukeException e) {
            System.out.println(" ");
        }
    }
    @Test
    public void extractStringToSave_newTask_taskInProperFormat() {
        try {
            Deadline deadlineTask = new Deadline("test", "2040/05/21 13:43");
            assertEquals("D | 0 | test | May 21 2040 13:43:00", deadlineTask.stringToSave());
        } catch (DukeException e) {
            System.out.println(" ");
        }
    }

}