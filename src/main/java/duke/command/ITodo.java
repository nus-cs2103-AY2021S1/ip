package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class ITodo extends Instruction{

    public ITodo(TaskList taskList, String taskDescription) {
        super(taskList, taskDescription);
    }

    @Override
    public String execute() {
        Task newTask = new Todo(taskDescription);
        return taskList.addTask(newTask, true);
    }
}
