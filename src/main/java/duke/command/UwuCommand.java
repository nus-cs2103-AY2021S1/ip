package duke.command;

import duke.Statistics;
import duke.Storage;
import duke.TaskList;

/**
 * Represents an uwu command.
 */
public class UwuCommand extends Command {
    public static final String COMMAND_WORD = "uwu";
    private static final String MESSAGE_UWU_ACKNOWLEDGEMENT = "owo";

    /**
     * Displays acknowledgement message to uwu command.
     *
     * @param tasks Task list representing current tasks.
     * @param storage Storage Storage in charge of saving file to hard disk.
     * @return A string representing Duke's response after executing command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Statistics stats) {
        return MESSAGE_UWU_ACKNOWLEDGEMENT;
    }

}
