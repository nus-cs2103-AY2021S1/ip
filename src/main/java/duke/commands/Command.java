package duke.commands;

import duke.exception.InvalidFormatDateException;
import duke.exception.InvalidFormatDeadlineException;
import duke.exception.InvalidFormatEventException;
import duke.exception.UnknownCommandException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Class that simulates the command of the user.
 */

public class Command {
    
    private static final String KEYWORD_ERR = "Sorry something went wrong. duke.Duke crashed X.X";
    private boolean isExit = false;
    protected String[] inputArr;

    
    /**
     * Creates a Command object
     * 
     * @param inputArr Array of length 2 that contains information of the user input
     *                 At index 0, contains the type of command
     *                 At index 1, contains the message of the command.
     */
    Command(String[] inputArr) {
        this.inputArr = inputArr;
    }

    /**
     * Checks the exit status of the Duke program.
     * 
     * @return true if the exit status is true, false otherwise.
     */
    public boolean getExitStatus() {
        return isExit;
    }

    /**
     * Set the exit status of the Duke program depending on the input 'status'.
     * 
     * @param status boolean value to set the exit status.
     */
    public void setExitStatus(boolean status) {
        isExit = status;
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
    public void printNumTask(TaskList tasks) {
        System.out.print(String.format("Now you have %d tasks in the list.\n", tasks.size()));
    }

    /**
     * Mainly for polymorphism.
     * 
     * @param tasks Object contains the task list.
     * @param ui Object that deals with interactions with the user.
     * @param storage Object that deals with loading tasks from the file and saving tasks in the file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) 
            throws InvalidFormatDeadlineException, InvalidFormatEventException, InvalidFormatDateException, 
            UnknownCommandException { return; }
    
}
