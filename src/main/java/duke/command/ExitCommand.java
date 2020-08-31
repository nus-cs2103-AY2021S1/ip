package duke.command;

import duke.*;

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
     * @param tasks is the task list that the command will execute with.
     * @param ui is the ui that the command will execute with.
     * @param storage is the storage that the command will execute with.
     * @throws DukeException when there is a problem executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printExit();
        storage.save(tasks.getTasks());
    }

    /**
     * Indicates whether the command is an exit command.
     * @return is true only if it is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
