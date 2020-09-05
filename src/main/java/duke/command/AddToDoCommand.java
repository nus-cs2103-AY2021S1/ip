package duke.command;

import duke.exception.DukeException;
import duke.exception.EmptyTaskException;
import duke.storage.Storage;
import duke.task.TaskType;
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
     * @param tasks Task List object.
     * @param ui User Interface object.
     * @param storage Storage object.
     * @throws EmptyTaskException If the task detail is empty.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskDetails.isEmpty()) {
            throw new EmptyTaskException(TaskType.TODO);
        } else {
            return addTask(new ToDo(taskDetails), tasks, ui, storage);
        }
    }
}
