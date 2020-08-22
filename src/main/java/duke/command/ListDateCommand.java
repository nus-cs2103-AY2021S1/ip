package main.java.duke.command;

import java.io.IOException;
import java.time.LocalDate;
import main.java.duke.core.Ui;
import main.java.duke.core.TaskList;
import main.java.duke.core.Storage;
import main.java.duke.handle.TaskNotFoundException;

/**
 * The ListDateCommand class represents a command that lists a task on a specific date in the task list.
 */
public class ListDateCommand extends Command{
    private LocalDate localDate;

    /**
     * Takes in the date for the searching of the task and returns a list date command.
     *
     * @param localDate The specific date.
     */
    public ListDateCommand(LocalDate localDate) {
        this.localDate = localDate;
    }

    /**
     * Takes in the task list, the interface, and the storage components, and list
     * the tasks on a specific date in the task list.
     *
     * @param taskList The task list component.
     * @param ui The user interface component.
     * @param storage The storage component.
     * @throws TaskNotFoundException If there is no task corresponding to the count of the task.
     * @throws IOException If the storage process needs to be handled
     */
    @Override
    public void excecute(TaskList taskList, Ui ui, Storage storage) throws TaskNotFoundException, IOException {
        ui.showList(taskList.findTaskAt(localDate));
    }
}
