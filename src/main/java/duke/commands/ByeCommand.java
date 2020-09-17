package duke.commands;

import static duke.util.FormatChecker.checkByeFormat;

import duke.exception.InvalidFormatByeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.textui.Ui;

/**
 * Class that simulates the bye command of the user.
 */
public class ByeCommand extends Command {

    /**
     * Initialize an ByeCommand object.
     *
     * @param inputArr Array of length 2 that contains information of the user input
     * Index 0 contains the type of command while Index 1 contains the message of the command.
     */
    public ByeCommand(String[] inputArr) {
        super(inputArr);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidFormatByeException {
        checkByeFormat(inputArr);
        return endProgram(tasks, ui, storage);
    }

    /**
     * Save and store the relevant information into local storage.
     *
     * @param tasks Object contains the task list.
     * @param ui Object that deals with interactions with the user.
     * @param storage Object that contains the saved list of tasks.
     * @return A String message that informs the user that the program will be ending.
     */
    private String endProgram(TaskList tasks, Ui ui, Storage storage) {
        String bye = ui.goodBye();
        storage.record(tasks);
        return bye;
    }
}
