package ui;

import storage.Storage;
import tasks.TaskList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private Ui ui = new Ui();
    private Storage store = new Storage("test.txt");
    private TaskList taskList = new TaskList(store);

    @Test
    public void readCommandTest() {
        String actResult = this.ui.readCommand("bye",  taskList);
        String expResult = "** Bye. Hope to see you soon!! **";

        assertEquals(expResult, actResult);
    }
}
