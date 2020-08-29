package taskbot.command;

import taskbot.exceptions.TaskbotException;

import taskbot.task.TaskList;
import taskbot.ui.Ui;

/**
 * Encapsulates a command to complete a task.
 */
public class DoneCommand extends Command {
    // The index of the task to be completed
    private int taskIndex;

    /**
     * Creates a DoneCommand.
     *
     * @param taskIndex The index of the task to be completed.
     */
    public DoneCommand(int taskIndex) {
        super(false);
        this.taskIndex = taskIndex;
    }

    /**
     * @return An int representing the index in the list
     * where the task is at.
     */
    public int getTaskIndex() {
        return taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws TaskbotException {
        taskList.completeTask(taskIndex);
    }

    @Override
    public boolean equals(Object obj) {
        // Check if obj is compared with itself
        if (obj == this) {
            return true;
        }

        // Check if obj is an instance of this class
        if (!(obj instanceof DoneCommand)) {
            return false;
        }

        // Compare taskIndex and return accordingly
        return taskIndex == ((DoneCommand) obj).getTaskIndex();
    }
}
