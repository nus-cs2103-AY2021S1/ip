package main.java.farrell.duke.command;

import main.java.farrell.duke.DukeException;
import main.java.farrell.duke.task.Task;
import main.java.farrell.duke.task.TaskList;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
        type = CommandType.DELETE;
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        if (taskIndex < 0 || taskIndex > taskList.getSize() - 1) {
            throw new DukeException("There is no task with index " + taskIndex);
        }
        Task deleteTask = taskList.getTask(taskIndex);
        taskList.deleteTask(taskIndex);
        return "I've removed this task:\n" + deleteTask.toString();
    }
}
