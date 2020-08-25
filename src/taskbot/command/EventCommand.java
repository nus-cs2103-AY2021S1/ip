package taskbot.command;

import taskbot.exceptions.TaskbotException;

import taskbot.task.TaskList;
import taskbot.ui.Ui;

public class EventCommand extends Command {
    private String task;

    public EventCommand(String task) {
        super(false);
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws TaskbotException {
        taskList.addEventTask(task);
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
