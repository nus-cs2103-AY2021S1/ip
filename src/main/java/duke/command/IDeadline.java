package duke.command;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

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
