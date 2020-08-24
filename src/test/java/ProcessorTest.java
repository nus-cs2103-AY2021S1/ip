import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ProcessorTest {

    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void processorAddTodoTest() {
        Processor processor = new Processor();
        ArrayList<Task> arraylst = new ArrayList<>();
        DukeFile dukeFile = DukeFile.createDukeFile("test");
        try {
            processor.process("todo read book", arraylst, dukeFile);
            assertEquals("[T][✘] read book", arraylst.get(0).toString());
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void processorDoneTest() {
        Processor processor = new Processor();
        ArrayList<Task> arraylst = new ArrayList<>();
        DukeFile dukeFile = DukeFile.createDukeFile("test");
        try {
            processor.process("todo read book", arraylst, dukeFile);
            processor.process("done 1", arraylst, dukeFile);
            assertEquals("[T][✓] read book", arraylst.get(0).toString());
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void processorDeleteTest() {
        Processor processor = new Processor();
        ArrayList<Task> arraylst = new ArrayList<>();
        DukeFile dukeFile = DukeFile.createDukeFile("test");
        try {
            processor.process("todo read book", arraylst, dukeFile);
            processor.process("todo read 2nd book", arraylst, dukeFile);
            processor.process("delete 1", arraylst, dukeFile);
            assertEquals(1, arraylst.size());
            assertEquals("[T][✘] read 2nd book", arraylst.get(0).toString());
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }
}
