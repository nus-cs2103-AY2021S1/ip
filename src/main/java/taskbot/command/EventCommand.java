package taskbot.command;

import taskbot.exceptions.TaskbotException;
import taskbot.task.TaskList;
import taskbot.ui.Ui;

/**
 * Encapsulates a command to add an event task.
 */
public class EventCommand extends Command {
    // The task description
    private String task;

    /**
     * Creates a EventCommand.
     *
     * @param task The task description.
     */
    public EventCommand(String task) {
        super(false);
        this.task = task;
    }

    /**
     * @return The task description.
     */
    public String getTask() {
        return task;
    }

    @Override
    public String execute(TaskList taskList) throws TaskbotException {
        return taskList.addEventTask(task);
    }

    @Override
    public boolean equals(Object obj) {
        // Check if obj is compared with itself
        if (obj == this) {
            return true;
        }

        // Check if obj is an instance of this class
        if (!(obj instanceof EventCommand)) {
            return false;
        }

        // Compare tasks and return accordingly
        return task.equals(((EventCommand) obj).getTask());
    }
}
