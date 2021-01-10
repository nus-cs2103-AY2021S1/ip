package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents an exit command. An ExitCommand object represents a command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Creates a new ExitCommand.
     *
     * @param userString Tokenized array form of the input command string.
     */
    public ExitCommand(String[] userString) {
        super(userString);
    }

    /**
     * Represents an empty method as the program exits without any execution.
     *
     * @param tasks The task list to operate on.
     * @param ui The user-interaction object responsible for all system printing and user-interaction.
     * @param storage Represents the logic needed to write to an user-specified file.
     * @return String to be printed out. In this case, it will be null as the program will exit.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        //Empty as program will exit.
        return null;
    }

    /**
     * Determines if the command should result in the termination of the program.
     *
     * @return True If the program should exit.
     * In this case, it will return true as this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
