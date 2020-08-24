import duke.Duke;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void deadlineCreation_deadlineFulMessage_deadlineMatches() throws DukeException {
        File fw = new File("data/tasks.txt");
        fw.delete();
        Duke testDuke = new Duke("data/tasks.txt");
        String[] testInput = {"deadline finish level-7 /by 2020-08-24", "bye"};
        testDuke.testRun(testInput);

        Task taskCreated = testDuke.getTasks().getTask(0);
        assertEquals(new Deadline("finish level-7", LocalDate.parse("2020-08-24").toString()).getPlainText(),
                taskCreated.getPlainText());
    }
}