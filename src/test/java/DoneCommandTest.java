import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DoneCommandTest {

    public class StorageStub extends Storage {
        public StorageStub(String path) {
            this.data = new File(path);
        }

        @Override
        public void generateTxt(TaskList list) throws DukeException {

        }
    }

    @Test
    public void execute_invalidInput_exceptionThrown() throws Exception{
        DoneCommand temp = new DoneCommand("10");
        TaskList list = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        try {
            temp.execute(list, ui, storage);
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, I can't seem to find the task...", e.getMessage());
        }
    }

    @Test
    public void execute_validInput_success() throws Exception {
        DoneCommand temp = new DoneCommand("1");
        TaskList list = new TaskList();
        Task test = new ToDo("Sample");
        list.add(test);
        Ui ui = new Ui();
        StorageStub storage = new StorageStub("");

        temp.execute(list, ui, storage);
        assertEquals(true, test.isDone());
    }

}
