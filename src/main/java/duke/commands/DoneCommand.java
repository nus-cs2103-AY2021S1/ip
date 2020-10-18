package duke.commands;

import duke.exceptions.CommandException;
import duke.exceptions.DukeException;
import duke.support.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Marks a certain task as done status.
 */
public class DoneCommand extends Command {
    private int taskIndex;

    /**
     * Creates a {@code DoneCommand}
     * @param taskIndex The index of the task marked.
     */
    public DoneCommand(String taskIndex) {
        this.taskIndex = Integer.parseInt(taskIndex);
    }

    @Override
    public String run(TaskList taskList, Storage storage) throws DukeException {
        try {
            Task currentTask = taskList.get(taskIndex - 1);
            currentTask.markAsDone();
            return Ui.doneTask(currentTask);
        } catch (Exception e) {
            throw new CommandException("Sorry! Failed to mark task as done. Please check the index.");
        }
    }
}

