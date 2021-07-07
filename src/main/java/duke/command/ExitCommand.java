package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to exit from the program.
 */
public class ExitCommand extends Command {
    /**
     * Creates a new exit command.
     */
    public ExitCommand() {
    }

    /**
     * Exits from the program.
     *
     * @param tasks is the task list that the command will execute with.
     * @param storage is the storage that the command will execute with.
     * @return a command response after executing the exit command.
     * @throws DukeException when there is a problem executing the command.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Storage storage) throws DukeException {
        storage.save(tasks.getTasks());
        return new CommandResponse(Ui.printExit(), this.isExit());
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return is true only if it is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
