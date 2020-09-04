package duke.command;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class CommandTests {
    protected final Storage storage = new Storage();
    protected final TaskList taskList = new TaskList(new ArrayList<>());
    protected final Ui ui = new Ui();
}
