package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.EmptyDateException;
import duke.exception.EmptyDescriptionException;
import duke.task.Deadline;

/**
 * represents a command to add a deadline task
 */
public class DeadlineCommand extends AddTaskCommand {

    /**
     * class constructor
     * @param fullCommand the full command given by the user
     */
    public DeadlineCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * creates a new deadline task which is added to the task list.
     * this change is reflected in the storage.
     * finally, the method returns a message indicating that the operation was successful
     * @param tasks the list of tasks
     * @param ui the user interface object responsible for system related commands
     * @param storage the storage system responsible for saving and loading data
     * @return message indicating the addition of the deadline task was successful
     * @throws EmptyDescriptionException if the description for the deadline task is empty
     * @throws EmptyDateException if the date for the deadline task is empty
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EmptyDescriptionException, EmptyDateException {
        Deadline deadline = new Deadline(fullCommand);
        tasks.add(deadline);
        storage.save(tasks);
        return addedTaskMessage(deadline, tasks);
    }
}
