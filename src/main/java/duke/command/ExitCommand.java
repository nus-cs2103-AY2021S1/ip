package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to exit the Chatbot.
 */
public class ExitCommand extends Command {

    /**
     * Creates a new exit command.
     */
    public ExitCommand() {
    }

    /**
     * Exits the Chatbot.
     * @param tasks The task list the command is executed with.
     * @param ui The ui the command is executed with.
     * @param storage The storage the command is executed with.
     * @throws DukeException If there was a problem with executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks.getTasks());
        ui.printExit();
    }

    /**
     * Indicates that this command is an exit command.
     * @return False.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
