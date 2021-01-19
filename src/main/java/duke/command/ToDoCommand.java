package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
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
        assert !taskName.isBlank() : "The task name should not be blank";

        Task task = new ToDo(taskName);
        tasks.addTask(task);
        ui.displayAddTask(task, tasks.numTasks());
    }

    @Override
    public String executeWithOutput(TaskList tasks, Ui ui) throws DukeException {
        verifyInput();
        assert !taskName.isBlank() : "The task name should not be blank";

        Task task = new ToDo(taskName);
        tasks.addTask(task);
        return ui.getAddTaskResponseAsString(task, tasks.numTasks());
    }

    @Override
    public void undo(TaskList tasks) {
        tasks.removeLastTask();
    }
    private void verifyInput() throws DukeException {
        if (taskName.isBlank()) {
            throw DukeException.badToDo();
        }
    }
}
