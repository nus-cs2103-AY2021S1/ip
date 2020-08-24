package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Todo;
import duke.Ui;

/**
 * Insert a new todo into current task list
 */
public class TodoCommand extends Command {
    Todo todo;

    public TodoCommand(String description) {
        this.todo = new Todo(description);
    }

    /**
     * Insert a new todo into the current task list and save it to the storage file
     *
     * @param taskList current task list
     * @param ui       text ui interface
     * @param storage  storage file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this.todo);
        storage.save(taskList);

        int size = taskList.size();
        ui.printAddConfirmation(taskList.show(size - 1), size);
    }
}
