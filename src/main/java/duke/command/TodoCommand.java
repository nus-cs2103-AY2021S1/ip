package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;

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
     * @param tasks   current task list
     * @param ui      text ui interface
     * @param storage storage file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.todo);
        storage.save(tasks);

        int size = tasks.size();
        ui.printAddConfirmation(tasks.show(size - 1), size);
    }
}
