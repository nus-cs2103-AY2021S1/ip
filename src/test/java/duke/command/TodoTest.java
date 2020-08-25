package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeCommandException;
import duke.exception.DukeStorageException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    private String path = java.nio.file.Paths.get( "src", "test").toString();

    @Test
    public void test1(){
        TaskList taskList = new TaskList();
        Storage storage = new Storage(path);
        Ui ui = new Ui();

        Command command = new TodoCommand("todo");

        String message = null;
        try{
            command.execute(taskList, storage, ui);
        } catch (DukeStorageException | DukeCommandException e) {
            message = e.getMessage();
        }

        assertEquals("\u2639 OOPS!!! Wrong 'todo' command format!", message);
    }

    @Test
    public void test2() {
        TaskList taskList = new TaskList();
        Storage storage = new Storage(path);
        Ui ui = new Ui();

        Command command = new TodoCommand("todo homework");

        String message = null;
        try{
            command.execute(taskList, storage, ui);
        } catch (DukeStorageException | DukeCommandException e) {
            message = e.getMessage();
        }

        assertEquals(null, message);
    }
}