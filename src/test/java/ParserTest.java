import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void processorAddTodoTest() {
        Parser parser = new Parser();
        ArrayList<Task> arraylst = new ArrayList<>();
        Storage storage = Storage.createDukeFile("test");
        try {
            parser.process("todo read book", arraylst, storage);
            assertEquals("[T][✘] read book", arraylst.get(0).toString());
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void processorDoneTest() {
        Parser parser = new Parser();
        ArrayList<Task> arraylst = new ArrayList<>();
        Storage storage = Storage.createDukeFile("test");
        try {
            parser.process("todo read book", arraylst, storage);
            parser.process("done 1", arraylst, storage);
            assertEquals("[T][✓] read book", arraylst.get(0).toString());
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void processorDeleteTest() {
        Parser parser = new Parser();
        ArrayList<Task> arraylst = new ArrayList<>();
        Storage storage = Storage.createDukeFile("test");
        try {
            parser.process("todo read book", arraylst, storage);
            parser.process("todo read 2nd book", arraylst, storage);
            parser.process("delete 1", arraylst, storage);
            assertEquals(1, arraylst.size());
            assertEquals("[T][✘] read 2nd book", arraylst.get(0).toString());
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }
}
