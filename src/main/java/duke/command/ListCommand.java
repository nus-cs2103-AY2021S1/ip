package duke.command;

import duke.storage.Storage;
import duke.util.TaskList;
import duke.Ui;

/**
 * Command that outputs the current state of the task list as an update.
 */
public class ListCommand extends Command {

    /** Default message to be sent when a Task is added */
    protected static final String LIST_HEADER = "Here are the tasks in your list: ";

    /**
     * Static method that creates a generic list command
     * @return list command
     */
    public static ListCommand create() {
        return new ListCommand();
    }

    /**
     * Bot outputs the elements in the task list provided to the given ui.
     * The bot does not perform any manipulation.
     * @param taskList List of Tasks to work with
     * @param ui UI element to be used
     * @param storage Storage element to be used
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.outputMessage(LIST_HEADER + '\n' + taskList.toString());
    }


}
