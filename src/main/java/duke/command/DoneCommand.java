package duke.command;

import duke.Storage;
import duke.exception.InvalidTaskException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.DukeMessages;

/**
 * Represents a Command given by the user to mark a Task as done.
 */
public class DoneCommand extends Command {
    private int taskIndex;

    /**
     * Creates a DoneCommand.
     * @param taskIndex An integer value representing the index of the Task to be marked completed.
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the DoneCommand.
     * @param list A TaskList containing the user's Tasks.
     * @param storage A Storage object that handles the storage of tasks in local storage, allowing them to persist.
     * @return A String that contains a user message indicating that the specified task has been marked completed.
     * @throws InvalidTaskException if details provided of Task to be marked done are invalid.
     */
    @Override
    public String execute(TaskList list, Storage storage) throws InvalidTaskException {
        Task doneTask = list.markTaskAsDone(this.taskIndex);
        storage.writeToTaskStorage(list.getSaveString());
        return DukeMessages.printDoneTaskMessage(doneTask);
    }
}
