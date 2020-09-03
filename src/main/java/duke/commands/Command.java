package duke.commands;

import duke.exception.InvalidFormatDateException;
import duke.exception.InvalidFormatDeadlineException;
import duke.exception.InvalidFormatEventException;
import duke.exception.UnknownCommandException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.textUI.Ui;

/**
 * Class that simulates the command of the user.
 */

public abstract class Command {
    private static final String KEYWORD_ERR = "Sorry something went wrong. Duke crashed X.X";
    protected String[] inputArr;
    /**
     * Creates a Command object.
     *
     * @param inputArr Array of length 2 that contains information of the user input
     *                 At index 0, contains the type of command
     *                 At index 1, contains the message of the command.
     */
    Command(String[] inputArr) {
        this.inputArr = inputArr;
    }
    
    /**
     * Prints the error message when duke crashes.
     */
    public static void printErr() {
        System.out.println(KEYWORD_ERR);
    }

    /**
     * Display out the number of task in the user's list.
     *
     * @param tasks the task object containing the list of task.
     */
    public String printNumTask(TaskList tasks) {
        return String.format("Now you have %d tasks in the list.\n", tasks.size());
    }

    /**
     * Mainly for polymorphism.
     *
     * @param tasks Object contains the task list.
     * @param ui Object that deals with interactions with the user.
     * @param storage Object that deals with loading tasks from the file and saving tasks in the file
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws UnknownCommandException,
            InvalidFormatDeadlineException, InvalidFormatEventException, InvalidFormatDateException;
}
