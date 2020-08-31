package command;

import java.util.Objects;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import task.Task;
import task.TodoTask;

/**
 * Class to initiate the Todo Command
 */
public class TodoCommand extends Command {
    private String todo;

    /**
     * Command for creating Todos
     *
     * @param todo Name of todo.
     */
    public TodoCommand(String todo) {
        super(CommandType.Todo);
        this.todo = todo;
    }

    /**
     * Runs the command to add a new Todo Task into the TaskList
     *
     * @param taskList ArrayList of Tasks Objects.
     * @param ui       Object of the Ui class.
     * @param storage  Object of the Storage class.
     * @throws DukeException Exception that occurs while executing the command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task todoTask = new TodoTask(this.todo);
        taskList.addTask(todoTask);
        return ui.showAdd(todoTask, taskList.getSize());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TodoCommand that = (TodoCommand) o;
        return Objects.equals(todo, that.todo);
    }
}
