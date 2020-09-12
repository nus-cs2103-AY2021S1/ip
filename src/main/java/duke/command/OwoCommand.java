package duke.command;

import duke.Statistics;
import duke.Storage;
import duke.TaskList;

/**
 * Represents an owo command.
 */
public class OwoCommand extends Command {
    public static final String COMMAND_WORD = "owo";
    private static final String MESSAGE_OWO_ACKNOWLEDGEMENT = "uwu";

    /**
     * Displays acknowledgement message to owo command.
     *
     * @param tasks Task list representing current tasks.
     * @param storage Storage Storage in charge of saving file to hard disk.
     * @return A string representing Duke's response after executing command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Statistics stats) {
        return MESSAGE_OWO_ACKNOWLEDGEMENT;
    }

}
