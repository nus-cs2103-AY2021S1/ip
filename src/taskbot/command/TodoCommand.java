package taskbot.command;

import taskbot.task.TaskList;
import taskbot.ui.Ui;

public class TodoCommand extends Command {
    private String task;

    public TodoCommand(String task) {
        super(false);
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTodoTask(task);
    }

    @Override
    public boolean equals(Object obj) {
        // Check if obj is compared with itself
        if (obj == this) {
            return true;
        }

        // Check if obj is an instance of this class
        if (!(obj instanceof TodoCommand)) {
            return false;
        }

        // Compare tasks and return accordingly
        return task.equals(((TodoCommand) obj).getTask());
    }
}
