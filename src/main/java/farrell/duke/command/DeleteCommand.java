package main.java.farrell.duke.command;

import main.java.farrell.duke.DukeException;
import main.java.farrell.duke.task.Task;
import main.java.farrell.duke.task.TaskList;

public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Creates a DeleteComman with a specified task index.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
        type = CommandType.DELETE;
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        if (taskIndex <= 0 || taskIndex > taskList.getSize()) {
            System.out.println(taskIndex);
            throw new DukeException("There is no task with index " + taskIndex);
        }
        Task deleteTask = taskList.getTask(taskIndex);
        taskList.deleteTask(taskIndex);
        return "I've removed this task:\n" + deleteTask.toString();
    }
}
