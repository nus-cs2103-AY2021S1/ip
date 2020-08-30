package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import duke.component.CliUi;
import duke.component.Storage;
import duke.component.StorageStub;
import duke.component.TaskList;
import duke.task.Task;
import duke.task.ToDo;

public class FindCommandTest {
    @Test
    public void isExit_alwaysFalse() {
        assertFalse(new FindCommand("find this").isExit());
        assertFalse(new FindCommand("find ").isExit());
    }

    @Test
    public void execute_emptyString_findsAll() {
        CliUi ui = new CliUi();
        Storage storage = new StorageStub();
        TaskList list = storage.getList();
        Task task = new ToDo("hello");
        Task task2 = new ToDo("world");
        list.add(task);
        list.add(task2);
        assertEquals("containing '' 2", new FindCommand("find ").execute(ui, list, storage));
    }

    @Test
    public void execute_anyString_findsMatches() {
        CliUi ui = new CliUi();
        Storage storage = new StorageStub();
        TaskList list = storage.getList();
        Task task = new ToDo("hello");
        Task task2 = new ToDo("world");
        list.add(task);
        list.add(task2);
        assertEquals("containing 'o' 2", new FindCommand("find o").execute(ui, list, storage));
        assertEquals("containing 'll' 1", new FindCommand("find ll").execute(ui, list, storage));
        assertEquals("containing 's' 0", new FindCommand("find s").execute(ui, list, storage));
    }
}
