package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/** Represents the command that is generated when an erroneous user input is given. */
public class ExceptionCommand extends Command {

    /** The exception that is resulted from the erroneous user input.*/
    private Exception exception;

    /** Constructs a new ExceptionCommand object with the specified exception.
     *
     * @param exception The exception that is resulted from the erroneous user input.
     */
    public ExceptionCommand(Exception exception) {
        this.exception = exception;
    }

    /** Prints out the ExceptionCommand message in Duke format.
     *
     * @param taskList The list of tasks.
     * @param ui The UI that prints out messages in Duke format.
     * @param storage The storage system that saves the taskList.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        setDialog(ui.showError(exception));
    }
}
