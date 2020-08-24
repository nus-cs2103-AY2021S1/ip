package duke.command;

import duke.*;

/**
 * Represents a command to see all the tasks.
 */
public class ListCommand extends Command {

    /**
     * Creates a new list command.
     */
    public ListCommand() {
    }

    /**
     * Lists out all the tasks in the specified task list.
     * @param tasks The task list the command is executed with.
     * @param ui The ui the command is executed with.
     * @param storage The storage the command is executed with.
     * @throws DukeException If there was a problem with executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printList(tasks);
    }
}
