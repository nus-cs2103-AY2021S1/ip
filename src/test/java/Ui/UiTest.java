package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import storage.Storage;
import tasks.TaskList;

public class UiTest {
    private Ui ui = new Ui();
    private Storage store = new Storage("mug-test.txt");
    private TaskList taskList = new TaskList(store);

    @Test
    public void readCommandTest() {
        String actResult = this.ui.readCommand("bye", taskList);
        String expResult = "** Bye. Hope to see you soon!! **";

        assertEquals(expResult, actResult);
    }
}
