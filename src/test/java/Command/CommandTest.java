package Command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import DukeComponent.Ui;
import TaskList.Storage;
import TaskList.TaskList;
import Tasks.Todo;
import org.junit.jupiter.api.Test;

public class CommandTest {
    @Test
    void commandTest() {
        Storage s = new Storage();
        TaskList list = new TaskList(s);
        assertEquals(new ListCommand().act(list), Ui.list(list));
        assertEquals(new AddCommand(new Todo("test")).act(list),
                Ui.addTaskMessage(new Todo("test"), list.size()));
        assertEquals(new DeleteCommand(list.size() - 1).act(list),
                Ui.deleteMessage(new Todo("test"), list.size()));

    }
}
