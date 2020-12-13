package duke.command;

import duke.exception.DukeException;
import duke.storage.StateManager;
import duke.task.Task;
import duke.task.TaskList;

public class ListCommand extends Command {

    /**
     * Formats all tasks in the TaskList into a message form, and prints the
     * list in the Ui.
     *
     * @param taskList The TaskList used by Duke.
     * @param stateManager  The Storage used by Duke.
     * @return CommandResult object for ui
     * @throws DukeException
     */
    @Override
    public CommandResult execute(TaskList taskList, StateManager stateManager) throws DukeException {
        assert taskList != null && stateManager != null;
        if (taskList.numberOfTasks() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Here are the tasks in your list:\n");

            for (int i = 0; i < taskList.numberOfTasks(); i++) {
                Task currentTask = taskList.getTask(i);
                stringBuilder.append((i + 1) + ". " + currentTask.toString() + "\n");
            }

            return new CommandResult(stringBuilder.toString());
        } else {
            return new CommandResult("There are no tasks yet!");
        }
    }
}
