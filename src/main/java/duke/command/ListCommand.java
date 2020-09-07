package duke.command;

import duke.Ui;
import duke.storage.Storage;
import duke.util.TaskList;

/**
 * Command that outputs the current state of the task list as an update.
 */
public class ListCommand extends Command {

    /** Default message to be sent when a Task is added */
    protected static final String LIST_HEADER = "Here are the tasks in your list: ";
    /** Default message to be sent when no tasks are found in the list */
    protected static final String EMPTY_LIST_MESSAGE = "You have no tasks on your list!";

    /**
     * Returns a standard List command.
     *
     * @return list command.
     */
    public static ListCommand create() {
        return new ListCommand();
    }

    /**
     * Executes the list command which outputs the elements in the task list to the given ui.
     * The command does not perform any manipulation.
     *
     * @param taskList List of Tasks to work with.
     * @param ui UI element to be used.
     * @param storage Storage element to be used.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String message;
        if (taskList.size() == 0) {
            message = EMPTY_LIST_MESSAGE;
        } else {
            message = LIST_HEADER + '\n' + taskList.toString();
        }
        ui.outputMessage(message);
    }


}
