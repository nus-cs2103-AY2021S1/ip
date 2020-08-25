package taskbot.command;

import taskbot.exceptions.TaskbotException;

import taskbot.task.TaskList;
import taskbot.ui.Ui;

public class DeadlineCommand extends Command {
    private String task;

    public DeadlineCommand(String task) {
        super(false);
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws TaskbotException {
        taskList.addDeadlineTask(task);
    }

    @Override
    public boolean equals(Object obj) {
        // Check if obj is compared with itself
        if (obj == this) {
            return true;
        }

        // Check if obj is an instance of this class
        if (!(obj instanceof DeadlineCommand)) {
            return false;
        }

        // Compare tasks and return accordingly
        return task.equals(((DeadlineCommand) obj).getTask());
    }
}
