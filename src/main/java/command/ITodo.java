package command;

import duke.DukeException;
import task.Task;
import task.TaskList;
import task.Todo;

public class ITodo extends Instruction{

    public ITodo(TaskList taskList, String taskDescription) {
        super(taskList, taskDescription);
    }

    @Override
    public String execute() throws DukeException {
        Task newTask = new Todo(taskDescription);
        return taskList.addTask(newTask, true);
    }
}
