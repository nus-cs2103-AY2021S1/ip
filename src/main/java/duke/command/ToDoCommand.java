package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.ToDo;

public class ToDoCommand extends Command {
    private String taskName;

    public ToDoCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        verifyInput();
        Task task = new ToDo(taskName);
        tasks.addTask(task);
        ui.displayAddTask(task, tasks.numTasks());
    }

    @Override
    public String executeWithOutput(TaskList tasks, Ui ui) throws DukeException {
        verifyInput();
        Task task = new ToDo(taskName);
        tasks.addTask(task);
        return ui.getAddTaskResponseAsString(task, tasks.numTasks());
    }
    private void verifyInput() throws DukeException {
        if (taskName.isBlank()) {
            throw DukeException.badToDo();
        }
    }
}
