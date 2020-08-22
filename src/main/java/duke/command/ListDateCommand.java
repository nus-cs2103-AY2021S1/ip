package main.java.duke.command;

import java.io.IOException;
import java.time.LocalDate;
import main.java.duke.core.*;
import main.java.duke.handle.*;

/**
 * The ListDateCommand class represents a commmand that lists a task on a specific date in the taks list.
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
     * Takes in the task list, the interface, and the storage compoenents, and list
     * the tasks on a specific date in the task list.
     *
     * @param taskList The task list component.
     * @param ui The user interface component.
     * @param storage The storage component.
     * @throws TaskNotFoundException If there is no task corresponding to the count of the task.
     * @throws IOException If the stroage process needs to be handled
     */
    @Override
    public void excecute(TaskList taskList, Ui ui, Storage storage) throws TaskNotFoundException, IOException {
        ui.showList(taskList.findTaskAt(localDate));
    }
}
