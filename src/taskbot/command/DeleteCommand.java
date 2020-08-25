package taskbot.command;

import taskbot.exceptions.TaskbotException;

import taskbot.task.TaskList;
import taskbot.ui.Ui;

public class DeleteCommand extends Command {
    private int taskIndex;
    public DeleteCommand(int taskIndex) {
        super(false);
        this.taskIndex = taskIndex;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws TaskbotException {
        taskList.deleteTask(taskIndex);
    }

    @Override
    public boolean equals(Object obj) {
        //Check if obj is compared with itself
        if (obj == this) {
            return true;
        }

        //Check if obj is an instance of this class
        if (!(obj instanceof DeleteCommand)) {
            return false;
        }

        //Compare taskIndex and return accordingly
        return taskIndex == ((DeleteCommand) obj).getTaskIndex();
    }
}
