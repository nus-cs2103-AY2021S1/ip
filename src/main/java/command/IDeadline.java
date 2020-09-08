package command;

import duke.DukeException;
import task.Deadline;
import task.Task;
import task.TaskList;

public class IDeadline extends Instruction{

    public IDeadline(TaskList taskList, String taskDescription) {
        super(taskList, taskDescription);
    }

    @Override
    public String execute() throws DukeException {
        Task newTask = Deadline.create(taskDescription);
        return taskList.addTask(newTask, true);
    }
}
