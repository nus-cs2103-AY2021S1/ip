package duke.command;

import duke.exception.DuplicateTaskException;
import duke.exception.EmptyToDoException;
import duke.exception.FileUpdateFailException;
import duke.storage.Storage;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Adds a todo task into the task list.
 */
public class AddToDoCommand extends AddCommand {

    private final String taskDetails;

    /**
     * Initializes the AddToDoCommand with the taskDetails;
     *
     * @param taskDetails Task details.
     */
    public AddToDoCommand(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    /**
     * Adds a todo task into the TaskList object.
     *
     * @param taskList TaskList object.
     * @param ui User Interface object.
     * @param storage Storage object.
     * @throws EmptyToDoException If the task detail is empty.
     * @throws FileUpdateFailException If storage file fails to get updated.
     * @throws DuplicateTaskException If the new task to be added already exists in the current {@code TaskList}.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws EmptyToDoException,
        FileUpdateFailException, DuplicateTaskException {
        if (taskDetails.isEmpty()) {
            throw new EmptyToDoException();
        }
        return addTask(new ToDo(taskDetails), taskList, ui, storage);
    }
}
