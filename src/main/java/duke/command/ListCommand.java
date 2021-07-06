package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
     *
     * @param tasks   The task list the command is executed with.
     * @param storage The storage the command is executed with.
     * @return A command response that represents the result of completing a command to list out all the tasks
     * in the specified task list.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Storage storage) {
        assert tasks != null && storage != null : "tasks and storage cannot be null.";
        return new CommandResponse(Ui.respondList(tasks));
    }
}
