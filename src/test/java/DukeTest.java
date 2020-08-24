import duke.Duke;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void deadlineAddTest() {
        File fw = new File("data/tasks.txt");
        fw.delete();
        Duke testDuke = new Duke("data/tasks.txt");
        String[] testInput = {"deadline finish level-7 /by 2020-08-24", "bye"};
        testDuke.testRun(testInput);

        assertEquals(1, testDuke.getTasks().getSize());

    }
}