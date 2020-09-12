package duke.command;

import duke.Statistics;
import duke.Storage;
import duke.TaskList;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    private static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "bb cya again!";

    /**
     * Displays acknowledgement message that task has been exited.
     *
     * @param tasks Task list representing current tasks.
     * @param storage Storage Storage in charge of saving file to hard disk.
     * @return A string representing Duke's response after executing command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Statistics stats) {
        return MESSAGE_EXIT_ACKNOWLEDGEMENT;
    }

}
