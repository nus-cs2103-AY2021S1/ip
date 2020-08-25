package duke.command;

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

    public AddToDoCommand(String taskDetails) {
        super(taskDetails);
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskException {
        if (getTaskDetails().isEmpty()) {
            throw new EmptyTaskException(TaskType.TODO);
        } else {
            addTask(new ToDo(getTaskDetails()), tasks, ui, storage);
        }
    }
}
