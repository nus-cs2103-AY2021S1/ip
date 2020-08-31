package duke.command;

import duke.*;

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
     * @throws DukeException when there is a problem executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printList(tasks);
    }
}