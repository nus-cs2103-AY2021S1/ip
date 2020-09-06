package duke.command;

import duke.DukeException;
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
     * @param tasks The task list the command is executed with.
     * @param storage The storage the command is executed with.
     * @return A command response that represents the result of completing a command to list out all the tasks
     * in the specified task list.
     * @throws DukeException If there was a problem with executing the command.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Storage storage) throws DukeException {
        assert !this.isExit() : "List command should not be an exit command.";
        return new CommandResponse(Ui.respondList(tasks), this.isExit());
    }
}
