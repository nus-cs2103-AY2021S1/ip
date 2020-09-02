package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to list all the tasks.
 */
public class ListCommand extends Command {

    /**
     * Creates a new list command.
     */
    public ListCommand() {
    }

    /**
     * Lists all the tasks from the task list.
     * @param tasks is the task list that the command will execute with.
     * @param ui is the ui that the command will execute with.
     * @param storage is the storage that the command will execute with.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}