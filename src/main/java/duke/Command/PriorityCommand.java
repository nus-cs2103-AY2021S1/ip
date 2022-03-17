package duke.command;

import duke.Storage;

import duke.exception.DukeException;

import duke.task.Task;
import duke.task.TaskList;

import duke.ui.Ui;

import static duke.ui.Message.concatLines;
import static duke.ui.Message.MESSAGE_PRIORITY;
import static duke.ui.Ui.LINE_SEPARATOR;

/**
 * Prioritises a task to the specified priority value.
 */
public class PriorityCommand extends Command {

    private final int taskIndex;
    private final int priorityValue;

    /**
     * Constructs a <code>PriorityCommand</code> Object given index of the task to be prioritised.
     *
     * @param taskIndex The 1-based index of the task to be prioritised.
     * @param priorityValue The priority value to be assigned to the task.
     */
    public PriorityCommand(int taskIndex, int priorityValue) {
        this.taskIndex = taskIndex;
        this.priorityValue = priorityValue;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.get(this.taskIndex);
        task.setPriority(this.priorityValue);
        storage.saveTasks(taskList);
        return concatLines(MESSAGE_PRIORITY, task.toString(), LINE_SEPARATOR);
    }
}
