package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.EmptyDescriptionException;
import duke.task.ToDo;

/**
 * represents a command to add a to do task
 */
public class ToDoCommand extends AddTaskCommand {

    /**
     * class constructor
     * @param fullCommand the full command given by the user
     */
    public ToDoCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * creates a new to do task which is added to the task list.
     * this change is reflected in the storage.
     * finally, the method returns a message indicating that the operation was successful
     * @param tasks the list of tasks
     * @param ui the user interface object responsible for system related commands
     * @param storage the storage system responsible for saving and loading data
     * @return message indicating the addition of the to do task was successful
     * @throws EmptyDescriptionException if the description for the to do task is empty
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EmptyDescriptionException {
        ToDo todo = new ToDo(fullCommand);
        tasks.add(todo);
        storage.save(tasks);
        return addedTaskMessage(todo, tasks);
    }
}
