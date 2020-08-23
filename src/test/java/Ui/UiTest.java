package Ui;

import Storage.Storage;
import Tasks.TaskList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    Ui ui = new Ui();
    Storage store = new Storage("test.txt");
    TaskList taskList = new TaskList(store);

    @Test
    public void readCommandTest() {
        String actResult = this.ui.readCommand("bye",  taskList);
        String expResult = "** Bye. Hope to see you soon!! **";

        assertEquals(expResult, actResult);
    }
}
