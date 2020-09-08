package main.java.com.jacob.duke.command;
import java.util.List;

import main.java.com.jacob.duke.DukeException;
import main.java.com.jacob.duke.DukeList;
import main.java.com.jacob.duke.Ui;
import main.java.com.jacob.duke.io.Storage;
import main.java.com.jacob.duke.task.Task;

public class PrintListCommand implements Command {
    /**
     * Executes command for pre-determined PrintList Command.
     *
     * @param ui UI object to deal with program output.
     * @param dukeList Task List Representation.
     * @param storage Storage object to deal with interfacing with file system.
     * @throws DukeException In case there are internal errors.
     */
    @Override
    public String execute(Ui ui, DukeList dukeList, Storage storage) {
        List<Task> taskList = dukeList.getTaskList();
        return ui.showFullList(taskList);
    }

    /**
     * Check if it is the bye Command.
     *
     * @return false since it is not
     */
    @Override
    public boolean isBye() {
        return false;
    }
}
