package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to exit the chat bot.
 */
public class ExitCommand extends Command {

    /**
     * Creates a new exit command.
     */
    public ExitCommand() {
    }

    /**
     * Exits the chat bot.
     *
     * @param tasks The task list the command is executed with.
     * @param storage The storage the command is executed with.
     * @return A command response that represents the result of completing an exit command.
     * @throws DukeException If there was a problem with executing the command.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null && storage != null : "tasks and storage cannot be null.";
        storage.save(tasks.getTasks());
        return new CommandResponse(Ui.respondExit(), true);
    }

}
